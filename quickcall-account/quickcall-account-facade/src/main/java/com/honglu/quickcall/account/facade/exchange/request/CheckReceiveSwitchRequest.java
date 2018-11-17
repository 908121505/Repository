package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：检查声优接单开关是否开启
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年10月18日 下午2:17:51
 */
public class CheckReceiveSwitchRequest  extends  AbstractRequest{
    
	private static final long serialVersionUID = 3192019957455091204L;
	/**客户编号*/
	private Long  customerId;
	

	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String getBizCode() {
		return  OrderRequestType.CHECK_RECEIVE_SWITCH;
	}
	@Override
	public String toString() {
		return "CheckReceiveSwitchRequest [customerId=" + customerId + "]";
	}
	
}
