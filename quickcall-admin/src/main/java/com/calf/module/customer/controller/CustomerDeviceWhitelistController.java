package com.calf.module.customer.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.customer.service.CustomerDeviceWhitelistService;
import com.calf.module.customer.vo.CustomerDeviceWhitelistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customerDeviceWhitelist")
public class CustomerDeviceWhitelistController implements BaseController<CustomerDeviceWhitelistVo>{

	@Autowired
	private CustomerDeviceWhitelistService customerDeviceWhitelistService;

	private static final String JSP_PATH = "customer/deviceWhitelist/%s";

	@Override
	public String home() {
		return String.format(JSP_PATH, "deviceWhitelistList");
	}

	@Override
	public DataTables<CustomerDeviceWhitelistVo> initTable(HttpServletRequest request) {
		return customerDeviceWhitelistService.getCustomerDeviceWhitelistPageList(request);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		/*if(StringUtils.isNotBlank(id) && !"0".equals(id)){
            customerDeviceWhitelistService.getDeviceWhitelistDetail(model,id);
		}
		return  String.format(JSP_PATH, "whitelistAdd");*/
		return null;
	}

	@Override
	public int saveAdd(CustomerDeviceWhitelistVo entity) {
	    //return customerDeviceWhitelistService.saveAdd(entity);
        return 0;
	}

	@Override
	public int saveUpdate(CustomerDeviceWhitelistVo entity) {
	    //return customerDeviceWhitelistService.saveUpdate(entity);
        return 0;
	}

	@Override
	public int delete(Long id) {
		return customerDeviceWhitelistService.delete(id);
	}

	@Override
	public int delete(String id) {
		return customerDeviceWhitelistService.delete(id);
	}


}