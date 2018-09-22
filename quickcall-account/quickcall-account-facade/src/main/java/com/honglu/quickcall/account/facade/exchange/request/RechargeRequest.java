package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class RechargeRequest extends AbstractRequest {

	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return AccountFunctionType.AlipayRecharge;
	}

}
