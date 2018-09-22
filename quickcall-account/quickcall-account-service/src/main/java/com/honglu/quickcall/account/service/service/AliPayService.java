package com.honglu.quickcall.account.service.service;

import com.honglu.quickcall.account.facade.exchange.request.RechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.WhthdrawRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

public interface AliPayService {

	CommonResponse recharge( RechargeRequest params);
	
	
	CommonResponse whthdraw( WhthdrawRequest params);
	
}
