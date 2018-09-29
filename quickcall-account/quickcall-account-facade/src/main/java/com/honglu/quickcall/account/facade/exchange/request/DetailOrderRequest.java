package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单详情
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:22:05 
 */
public class DetailOrderRequest extends AbstractRequest {
    
	private static final long serialVersionUID = 2805484436032150633L;
	/**订单编号*/
	private Long  orderId;
	/**查询类型1：消费端订单   2：服务端订单(大V)*/
	private Integer  type;
	

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
		return OrderRequestType.DETAIL_ORDER;
	}

	@Override
	public String toString() {
		return "DetailOrderRequest [orderId=" + orderId + ", type=" + type + "]";
	}



}
