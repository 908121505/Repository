package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class SaveSignNameRequest extends UserCenterRequest{
	private Long customerId;
	private String signName;
	
	
	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getSignName() {
		return signName;
	}


	public void setSignName(String signName) {
		this.signName = signName;
	}


	@Override
	public String getBizCode() {
		return UserFunctionType.SaveSignName;
	}

}
