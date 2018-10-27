package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class AppVersionManageRequest extends UserCenterRequest {
	private String appType;
	private Integer versionType;
	private String baseVersionName;
	private String channel;

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

	public String getBaseVersionName() {
		return baseVersionName;
	}

	public void setBaseVersionName(String baseVersionName) {
		this.baseVersionName = baseVersionName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return UserFunctionType.appVersionManage;
	}

}
