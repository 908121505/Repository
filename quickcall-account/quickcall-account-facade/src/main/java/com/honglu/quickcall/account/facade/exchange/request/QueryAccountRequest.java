package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class QueryAccountRequest extends AbstractRequest {
     
	private Long userId;
	
	
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return AccountFunctionType.QueryAccount;
	}

}
