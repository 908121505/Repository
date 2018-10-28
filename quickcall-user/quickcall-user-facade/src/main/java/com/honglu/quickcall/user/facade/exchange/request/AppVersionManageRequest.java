package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class AppVersionManageRequest extends UserCenterRequest {
	private String appType;
	private Integer versionType;

	private String headVersionUrl;

	public String getHeadVersionUrl() {
		return headVersionUrl;
	}

	public void setHeadVersionUrl(String headVersionUrl) {
		this.headVersionUrl = headVersionUrl;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public Integer getVersionType() {
		return versionType;
	}

	public void setVersionType(Integer versionType) {
		this.versionType = versionType;
	}

	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return UserFunctionType.appVersionManage;
	}

}
