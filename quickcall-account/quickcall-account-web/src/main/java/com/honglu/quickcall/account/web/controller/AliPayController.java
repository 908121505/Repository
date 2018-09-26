package com.honglu.quickcall.account.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.exchange.request.AlipayNotifyRequest;
import com.honglu.quickcall.account.facade.exchange.request.BindAliaccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.RechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.WhthdrawRequest;
import com.honglu.quickcall.account.web.service.AccountCenterService;
import com.honglu.quickcall.common.api.exchange.BaseController;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;


@Controller
@RequestMapping("/alipay")
public class AliPayController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(AliPayController.class);
	
	@Autowired
	private AccountCenterService  accountCenterService;
	
	/**
     *充值
     */
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel recharge(HttpServletRequest request, RechargeRequest params) {
    	logger.info("accountWeb.pay.recharge.request.data : " + JSONObject.toJSONString(params));
    	 WebResponseModel response=new  WebResponseModel ();
    	if(params.getAmount()==null||params.getUserId()==null) {
    		response.setCode(AccountBizReturnCode.paramError.code());
            response.setMsg(AccountBizReturnCode.paramError.desc());
            return response;
    	}
    	params.setRemoteIp(this.getRemoteHost(request));
         response = accountCenterService.execute(params);
        
        logger.info("accountWeb.pay.recharge.resonse.data : " + JSONObject.toJSONString(response));
        return response;
    }
    
    /**
     *提现
     */
    @RequestMapping(value = "/whthdraw", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel whthdraw( WhthdrawRequest params) {
    	logger.info("accountWeb.pay.whthdraw.request.data : " + JSONObject.toJSONString(params));
     	 WebResponseModel response=new  WebResponseModel ();
     	 if(params.getAmount()==null||params.getUserId()==null) {
   	     		response.setCode(AccountBizReturnCode.paramError.code());
   	     		response.setMsg(AccountBizReturnCode.paramError.desc());
   	     		return response;
   	}
         response = accountCenterService.execute(params);
     	logger.info("accountWeb.pay.whthdraw.response.data : " + JSONObject.toJSONString(response));
        return response;
    }
    
    /**
     *查询账户
     */
    @RequestMapping(value = "/queryAccount", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryAccount( QueryAccountRequest params) {
    	logger.info("accountWeb.pay.queryAccount.request.data : " + JSONObject.toJSONString(params));
    	WebResponseModel response=new WebResponseModel();
    	if(params.getUserId()==null) {
    		 response.setCode(AccountBizReturnCode.paramError.code());
             response.setMsg(AccountBizReturnCode.paramError.desc());
             return response;
    	}
         response = accountCenterService.execute(params);
        logger.info("accountWeb.pay.queryAccount.response.data : " + JSONObject.toJSONString(response));
        return response;
    }
    
    
    /**
     *绑定支付宝
     */
    @RequestMapping(value = "/bindAliaccount", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel bindAliaccount( BindAliaccountRequest params) {
    	logger.info("accountWeb.pay.bindAliaccount.request.data : " + JSONObject.toJSONString(params));
    	WebResponseModel response=new WebResponseModel();
    	if(params.getUserId()==null) {
    		 response.setCode(AccountBizReturnCode.paramError.code());
             response.setMsg(AccountBizReturnCode.paramError.desc());
             return response;
    	}
         response = accountCenterService.execute(params);
        logger.info("accountWeb.pay.bindAliaccount.response.data : " + JSONObject.toJSONString(response));
        return response;
    }
    
    
  
    
    
    /**
     *支付回调
     */
    @RequestMapping(value = "/alipayNotify",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel alipayNotify( @RequestBody AlipayNotifyRequest params) {
    	logger.info("accountWeb.pay.alipayNotify.request.data : " + JSONObject.toJSONString(params));
    	WebResponseModel response=new WebResponseModel();
    	if(params.getAccountId()==null||StringUtils.isBlank(params.getOrderNo())||
    			params.getAmount()==null||params.getPayState()==null) {
    		 response.setCode(AccountBizReturnCode.paramError.code());
             response.setMsg(AccountBizReturnCode.paramError.desc());
             return response;
    	}
         response = accountCenterService.execute(params);
        logger.info("accountWeb.pay.alipayNotify.response.data : " + JSONObject.toJSONString(response));
        return response;
    }
	
	

}
