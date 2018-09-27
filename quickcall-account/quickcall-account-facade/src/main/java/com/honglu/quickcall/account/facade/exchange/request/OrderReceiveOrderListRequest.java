package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户收到订单查询入参
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:20:18 
 */
public class OrderReceiveOrderListRequest extends AbstractRequest {
    
	
	private static final long serialVersionUID = -6528841825278062555L;
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
		return OrderRequestType.ORDER_RECEIVE_ORDER_LIST;
	}

	@Override
	public String toString() {
		return "OrderReceiveOrderListRequest [customerId=" + customerId + "]";
	}



}
