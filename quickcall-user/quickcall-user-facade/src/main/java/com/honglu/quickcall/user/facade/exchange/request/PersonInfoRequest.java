package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class PersonInfoRequest extends UserCenterRequest{
	/*
	 * 登录用户id
	 */
	private Long customerId;
	/*
	 * 其他用户id
	 */
	private Long otherId;

	
	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Long getOtherId() {
		return otherId;
	}


	public void setOtherId(Long otherId) {
		this.otherId = otherId;
	}


	@Override
	public String getBizCode() {
		return UserFunctionType.PersonInfo;
	}

}
