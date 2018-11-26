package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class IsFirstechargeRequest extends AbstractRequest{
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "IsFirstechargeRequest [customerId=" + customerId + "]";
	}

	@Override
	public String getBizCode() {
		return AccountFunctionType.ISFIRSTRECHARGE;
	}
	
	
}
