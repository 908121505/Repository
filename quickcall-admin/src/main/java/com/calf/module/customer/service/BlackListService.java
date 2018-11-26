package com.calf.module.customer.service;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.customer.vo.BlackListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拉黑名单服务
 */
@Service
public class BlackListService {

    @Autowired
    private BaseManager baseManager;
    @Autowired
    private CommonUtilService commonUtilService ;

    public DataTables<BlackListVo> getBlackListPageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("phone", parameters.get("phone"));
        paramMap.put("blackCustomerId", parameters.get("blackCustomerId"));
        paramMap.put("customerId", parameters.get("customerId"));
        paramMap.put("status", 1);
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));

        List<BlackListVo> list = baseManager.query("BlackList.selectPageList", paramMap);

        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("BlackList.selectCount", paramMap);

        return new DataTables<BlackListVo>(sEcho, list, list.size(), total);
    }

    public void getBlackListDetail(Model model, String id){

    }

    public int saveAdd(BlackListVo entity) {
        return 0;
    }

    public int saveUpdate(BlackListVo entity) {
        return 0;
    }

    public int delete(String id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("modifyMan",commonUtilService.getCurrUser());
        map.put("modifyTime",new Date());
        map.put("id", id);
        map.put("status", 0);
        baseManager.update("BlackList.updateStatusDel",map);
        return 0;
    }

    public int delete(Long id){//here
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("modifyMan",commonUtilService.getCurrUser());
        map.put("modifyTime",new Date());
        map.put("id", id);
        map.put("status", 0);
        baseManager.update("BlackList.updateStatusDel",map);
        return 0;
    }

}
