package com.honglu.quickcall.account.service.bussService;

import com.honglu.quickcall.account.facade.exchange.request.AlipayNotifyRequest;
import com.honglu.quickcall.account.facade.exchange.request.BindAliaccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.IsFirstechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.RechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.WhthdrawRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

public interface AliPayService {

	CommonResponse recharge( RechargeRequest params);
	
	
	CommonResponse whthdraw( WhthdrawRequest params);
	
	
	CommonResponse bindAliaccount( BindAliaccountRequest params);
	
	
	CommonResponse alipayNotify( AlipayNotifyRequest params);


	CommonResponse isFirstecharge(IsFirstechargeRequest request);
	
}
