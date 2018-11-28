package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：查询发起的订单入参
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:22:05 
 */
public class OrderSendOrderListRequest extends AbstractRequest {
    
	private static final long serialVersionUID = 1084801564437387476L;
	/**客户编号*/
	private Long  customerId;
	/**订单大状态*/
	private Integer  orderStatus;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	


	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String getBizCode() {
		return OrderRequestType.ORDER_SEND_ORDER_LIST;
	}

	@Override
	public String toString() {
		return "OrderSendOrderListRequest [customerId=" + customerId + "]";
	}



}
