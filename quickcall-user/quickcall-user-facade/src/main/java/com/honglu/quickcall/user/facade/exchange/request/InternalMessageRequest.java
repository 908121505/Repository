package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 查看我的消息
 * @author xiangxianjin
 *
 */
public class InternalMessageRequest extends UserCenterRequest{
	private static final long serialVersionUID = -7167561630995863410L;

	private Long customerId;
	
	private Integer messageType;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.INTERNAL_MESSAGE;
	}

}
