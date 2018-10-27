package com.calf.module.customerservice.service.impl;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.customerservice.controller.AppearanceController;
import com.calf.module.customerservice.entity.CustomerAppearance;
import com.calf.module.customerservice.service.AppearanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 照片审核
 *
 * @author chenpeng
 * @date 2018/10/22 15:32
 */
@Service
public class AppearanceServiceImpl implements AppearanceService{
    private static final Logger logger = LoggerFactory.getLogger(AppearanceServiceImpl.class);

    @Autowired
    private BaseManager baseManager;
    @Autowired
    private CommonUtilService commonUtilService ;

    @Override
    public DataTables<CustomerAppearance> getAppearancePageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));

        paramMap.put("customerId", parameters.get("customerId"));
        paramMap.put("nickName", parameters.get("nickName"));
        paramMap.put("auditStatus", parameters.get("auditStatus"));

        paramMap.put("type", request.getAttribute("type"));

        List<CustomerAppearance> appearanceList = baseManager.query("Appearance.selectAppearancePageList", paramMap);
        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("Appearance.selectAppearanceCount", paramMap);
        return new DataTables<CustomerAppearance>(sEcho, appearanceList, appearanceList.size(), total);
    }

    @Override
    public void getAppearanceDetail(Model model, String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        CustomerAppearance  customerAppearance = baseManager.get("Appearance.getEntityById",  paramMap);
        model.addAttribute("entity", customerAppearance);
    }

    @Override
    public int saveUpdate(CustomerAppearance entity) {
        CustomerAppearance  customerAppearance =  new CustomerAppearance();
        BeanUtils.copyProperties(entity, customerAppearance);
        customerAppearance.setModifyMan(commonUtilService.getCurrUser());
        customerAppearance.setModifyTime(new Date());
        baseManager.update(customerAppearance);
        return 0;
    }

    @Override
    public int approve(String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        int count = baseManager.update("Appearance.approve", paramMap);
        if(count > 0){
            return 0;
        }
        return -1;
    }

    @Override
    public int reject(String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        int count = baseManager.update("Appearance.reject", paramMap);
        if(count > 0){
            return 0;
        }
        return -1;
    }
}
