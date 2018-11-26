package com.calf.module.customer.service;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.customer.vo.CustomerDeviceWhitelistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户设备白名单服务
 */
@Service
public class CustomerDeviceWhitelistService {

    @Autowired
    private BaseManager baseManager;
    @Autowired
    private CommonUtilService commonUtilService ;

    public DataTables<CustomerDeviceWhitelistVo> getCustomerDeviceWhitelistPageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchStr", parameters.get("searchStr"));
        paramMap.put("status", 1);
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));

        List<CustomerDeviceWhitelistVo> list = baseManager.query("CustomerDeviceWhitelist.selectPageList", paramMap);

        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("CustomerDeviceWhitelist.selectCount", paramMap);

        return new DataTables<CustomerDeviceWhitelistVo>(sEcho, list, list.size(), total);
    }

    public void getCustomerDeviceWhitelistDetail(Model model, String id){

    }

    public int saveAdd(CustomerDeviceWhitelistVo entity) {
        return 0;
    }

    public int saveUpdate(CustomerDeviceWhitelistVo entity) {
        return 0;
    }

    public int delete(String id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("modifyMan",commonUtilService.getCurrUser());
        map.put("modifyTime",new Date());
        map.put("id", id);
        map.put("status", 0);
        baseManager.update("CustomerDeviceWhitelist.updateStatusDel",map);
        return 0;
    }

    public int delete(Long id){//here
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("modifyMan",commonUtilService.getCurrUser());
        map.put("modifyTime",new Date());
        map.put("id", id);
        map.put("status", 0);
        baseManager.update("CustomerDeviceWhitelist.updateStatusDel",map);
        return 0;
    }

}
