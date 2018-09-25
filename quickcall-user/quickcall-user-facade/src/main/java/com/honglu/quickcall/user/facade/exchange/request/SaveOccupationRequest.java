package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class SaveOccupationRequest extends UserCenterRequest{
	private Long customerId;
	private int occupationId;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public int getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(int occupationId) {
		this.occupationId = occupationId;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.SaveOccupation;
	}

}
