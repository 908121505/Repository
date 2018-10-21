package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.user.facade.code.UserFunctionType;

public class BindVXorQQRequest extends AbstractRequest {
	private Long customerId; // 用户Id
	private String wechatOpenId;// 微信 唯一标识

	private String qqOpenId;// QQ唯一标识

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getWechatOpenId() {
		return wechatOpenId;
	}

	public void setWechatOpenId(String wechatOpenId) {
		this.wechatOpenId = wechatOpenId;
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return UserFunctionType.bindVXorQQ;
	}

}
