package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单详情IM查询
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:22:05 
 */
public class DetailOrderForIMRequest extends AbstractRequest {
    
	private static final long serialVersionUID = 3433241419376482614L;
	/**用户编号*/
	private Long  customerId ;
	/**大V编号*/
	private Long  serviceId;
	


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String getBizCode() {
		return OrderRequestType.DETAIL_ORDER_FOR_IM;
	}

	@Override
	public String toString() {
		return "DetailOrderForIMRequest [customerId=" + customerId + ", serviceId=" + serviceId + "]";
	}

	



}
