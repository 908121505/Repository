package com.honglu.quickcall.account.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.exchange.request.ApplePayRequest;
import com.honglu.quickcall.account.facade.exchange.request.ApplePurchaseRequest;
import com.honglu.quickcall.account.web.service.AccountCenterService;
import com.honglu.quickcall.common.api.exchange.BaseController;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiangxianjin
 * @date: 2018/10/20 15:16
 * @description: 苹果支付服务
 */
@Controller
@RequestMapping("/apple-pay")
public class ApplePayController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(ApplePayController.class);

	@Autowired
	private AccountCenterService accountCenterService;

	/**
	 * 充值
	 */
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel recharge(HttpServletRequest request, ApplePayRequest params) {
		LOGGER.info("accountWeb.apply-pay.recharge.req.data : {}", JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (params.getAmount() == null || params.getCustomerId() == null) {
			response.setCode(AccountBizReturnCode.paramError.code());
			response.setMsg(AccountBizReturnCode.paramError.desc());
			return response;
		}
		params.setRemoteIp(this.getRemoteHost(request));
		response = accountCenterService.execute(params);

		LOGGER.info("accountWeb.apply-pay.recharge.response.data : {}", JSONObject.toJSONString(response));
		return response;
	}

	/**
	 * 回调验证充值结果
	 */
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel notify(HttpServletRequest request, ApplePurchaseRequest params) {
		LOGGER.info("accountWeb.apply-pay.notify.req.data : {}", JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (StringUtils.isBlank(params.getOrderId())
				|| StringUtils.isBlank(params.getTradeNo())
				|| StringUtils.isBlank(params.getAppReceipt()) ) {
			response.setCode(AccountBizReturnCode.paramError.code());
			response.setMsg(AccountBizReturnCode.paramError.desc());
			return response;
		}
		response = accountCenterService.execute(params);
		LOGGER.info("accountWeb.apply-pay.notify.response.data : {}",JSONObject.toJSONString(response));
		return response;
	}


}
