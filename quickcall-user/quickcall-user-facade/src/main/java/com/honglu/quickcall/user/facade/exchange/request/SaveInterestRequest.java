package com.honglu.quickcall.user.facade.exchange.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.entity.Interest;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class SaveInterestRequest extends UserCenterRequest{
	private Long customerId;
	private String interestId; 
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getInterestId() {
		return interestId;
	}

	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.SaveInterest;
	}

}
