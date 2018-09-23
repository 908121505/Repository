package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class SaveNickNameRequest extends UserCenterRequest{
	private Long customerId;
	private String nickName;
	private String head_portrait_url;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHead_portrait_url() {
		return head_portrait_url;
	}

	public void setHead_portrait_url(String head_portrait_url) {
		this.head_portrait_url = head_portrait_url;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.SaveNicknameImage;
	}

}
