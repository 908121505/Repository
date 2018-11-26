package com.calf.module.appconfig.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.appconfig.entity.Interest;
import com.calf.module.appconfig.impl.InterestService;


/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：兴趣相关controller
 * @Package: com.calf.module.appconfig.controller 
 * @author: chenliuguang   
 * @date: 2018年10月16日 下午3:19:41
 */
@Controller
@RequestMapping(value = "/interest")
public class InterestController implements BaseController<Interest> {
	@Autowired
	private InterestService  interestService;
	
	@Override
	public String home() {
		return "app_config/interest/intereList";
	}

	@Override
	public DataTables<Interest> initTable(HttpServletRequest request) {
		return interestService.getInterestPageList(request);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		if(StringUtils.isNotBlank(id) && !"0".equals(id)){
			interestService.getInterestDetail(model,id);
		}
		return  "app_config/interest/addInterest";
	}

	@Override
	public int saveAdd(Interest entity) {
		return interestService.saveAdd(entity);
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public int saveUpdate(Interest entity) {
		return interestService.saveUpdate(entity);
	}

	@Override
	public int delete(String id) {
		return interestService.del(id);
	}


}
