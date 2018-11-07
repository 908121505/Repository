package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;

public class SearchPersonByPhoneVO implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7673620786561283349L;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 主播编号
	 */
	private Long customerId;
	
	/**
	 * 头像地址
	 */
	private String headPortraitUrl;
	
	private Long phone;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}

	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	
}
