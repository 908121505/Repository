package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class PersonInfoRequest extends UserCenterRequest{
	/*
	 * 被查看者id
	 */
	private String id;
	/*
	 * 登录用户id
	 */
	private String persionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersionId() {
		return persionId;
	}

	public void setPersionId(String persionId) {
		this.persionId = persionId;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.PersonInfo;
	}

}
