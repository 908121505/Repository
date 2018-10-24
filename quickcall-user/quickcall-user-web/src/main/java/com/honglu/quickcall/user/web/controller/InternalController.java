package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.*;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiangxianjin
 * @date 2018年10月24日 22点14分
 * @description: 站内信消息查询
 */
@Controller
@RequestMapping("/internal")
public class InternalController {
	private final static Logger LOGGER = LoggerFactory.getLogger(InternalController.class);

	@Autowired
	private UserCenterService userCenterService;

	/**
	 * 首页搜索用户列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel messages(InternalMessageRequest params) {
		return userCenterService.execute(params);
	}

}
