package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class queryMyskillRequest extends UserCenterRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 845235910727140895L;
	
	/**
	 * 用户编号
	 */
	private Long customerId;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String getBizCode() {
		return UserFunctionType.QUERY_MY_SKILL;
	}
}
