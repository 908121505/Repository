package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 修改性别
 *
 * @author chenpeng
 * @date 2018/10/18 11:31
 */
public class UpdateGenderReq extends UserCenterRequest{
	private Long customerId;
	private Integer gender;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.updateGender;
	}

}
