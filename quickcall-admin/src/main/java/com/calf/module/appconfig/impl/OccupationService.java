package com.calf.module.appconfig.impl;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.appconfig.entity.Interest;
import com.calf.module.appconfig.entity.Occupation;
import com.calf.module.common.impl.CommonUtilService;
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
 * Description: 职业管理
 *
 * @author chenpeng
 * @date 2018/10/16 17:19
 */
@Service
public class OccupationService {

    @Autowired
    private BaseManager baseManager;
    @Autowired
    private CommonUtilService commonUtilService ;

    @SuppressWarnings("unchecked")
    public DataTables<Occupation> getOcupationPageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
        List<Occupation> occupationList = baseManager.query("Occupation.selectPageList", paramMap);
        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("Occupation.selectCount", paramMap);
        return new DataTables<Occupation>(sEcho, occupationList, occupationList.size(), total);
    }

    public void getOcupationDetail(Model model, String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        Occupation  occupation = baseManager.get("Occupation.selectByPrimaryKey",  paramMap);
        model.addAttribute("entity", occupation);
    }

    public int saveAdd(Occupation entity) {
        Occupation  occupation =  new Occupation();
        BeanUtils.copyProperties(entity, occupation);
        occupation.setCreateMan(commonUtilService.getCurrUser());
        occupation.setCreateTime(new Date());
        occupation.setType(0);
        baseManager.insert(occupation);
        return 0;
    }

    public int saveUpdate(Occupation entity) {
        Occupation  occupation =  new Occupation();
        BeanUtils.copyProperties(entity, occupation);
        occupation.setModifyMan(commonUtilService.getCurrUser());
        occupation.setModifyTime(new Date());
        baseManager.update(occupation);
        return 0;
    }

}
