package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：大V接受/拒绝
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:22:05 
 */
public class DvReceiveOrderRequest extends AbstractRequest {
	private static final long serialVersionUID = -937169256010787361L;
	/**客户编号*/
	private Long  orderId;
	/**1:同意 2：拒绝*/
	private Integer  type ;

	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String getBizCode() {
		return OrderRequestType.DV_RECEIVE_ORDER;
	}


	@Override
	public String toString() {
		return "DvReceiveOrderRequest [orderId=" + orderId + ", type=" + type + "]";
	}


	

}
