package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 设置来访为已读
 * @author zhaozheyi
 *
 */
public class SetVisitReadRequest extends UserCenterRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7976795354318402514L;
	
	/**
	 * 起始时间
	 */
	private Long startTime;
	
	/**
	 * 用户编号
	 */
	private Long CustomerId;
	

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.Set_Visit_Read;
	}

}
