package com.honglu.quickcall.user.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.AppVersionManageRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;

@Controller
@RequestMapping("/appVersion")
public class AppVersionController {
	private static Logger logger = LoggerFactory.getLogger(AppVersionController.class);

	@Autowired
	private UserCenterService userCenterService;
	private static final String REQUEST_HEADER_UA = "User-Agent";

	/**
	 * 检查用户是否存在
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel query(AppVersionManageRequest params, HttpServletRequest request) {
		logger.info("userWeb.appVersion.query.request.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (StringUtils.isNotEmpty(request.getHeader(REQUEST_HEADER_UA))) {
			params.setHeadVersionUrl(request.getHeader(REQUEST_HEADER_UA));
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.appVersion.query.response.data : " + JSONObject.toJSONString(response));
		return response;
	}
}
