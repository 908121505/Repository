package com.honglu.quickcall.account.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.account.facade.exchange.request.RechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.WhthdrawRequest;
import com.honglu.quickcall.account.web.service.PaymentService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;


@Controller
@RequestMapping("/alipay")
public class AliPayController {
	
	private static Logger logger = LoggerFactory.getLogger(AliPayController.class);
	
	private PaymentService  paymentService;
	
	/**
     *充值
     */
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel recharge( RechargeRequest params) {
        WebResponseModel response = paymentService.execute(params);
        return response;
    }
    
    /**
     *提现
     */
    @RequestMapping(value = "/whthdraw", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel whthdraw( WhthdrawRequest params) {
        WebResponseModel response = paymentService.execute(params);
        return response;
    }
	
	

}
