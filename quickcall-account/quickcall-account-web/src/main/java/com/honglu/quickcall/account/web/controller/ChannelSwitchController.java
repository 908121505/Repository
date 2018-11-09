package com.honglu.quickcall.account.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.account.facade.exchange.request.ChannelSwitchRequest;
import com.honglu.quickcall.account.web.service.IOrderInfoService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

@Controller
@RequestMapping("/voice")
public class ChannelSwitchController {
	 @SuppressWarnings("unused")
	private final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	 
	 @Autowired
	    private IOrderInfoService orderInfoService;
	 
	 /**
	     * 通过渠道和版本获取开关值
	     * @param params
	     * @return
	     */
	    @RequestMapping(value = "/get_talk_version", method = RequestMethod.POST)
	    @ResponseBody
	    public WebResponseModel queryChannelStatus(ChannelSwitchRequest params) {
	    	WebResponseModel response = orderInfoService.execute(params);
	        return response;
	    }
}
