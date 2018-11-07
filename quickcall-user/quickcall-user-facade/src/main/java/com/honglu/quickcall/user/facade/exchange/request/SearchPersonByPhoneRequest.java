package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class SearchPersonByPhoneRequest extends UserCenterRequest {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3594901928688565400L;
	
	private Long phone;
	

	public Long getPhone() {
		return phone;
	}


	public void setPhone(Long phone) {
		this.phone = phone;
	}


	@Override
	public String getBizCode() {
		return UserFunctionType.searchPersonByPhone;
	}

}
