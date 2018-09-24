package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户同意/拒绝订单
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:22:05 
 */
public class ConfirmOrderRequest extends AbstractRequest {
    
	
	private static final long serialVersionUID = -8404503202994720484L;
	/**客户编号*/
	private Long  orderId;
	/**请求类型1：同意  2：拒绝*/
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
		return OrderRequestType.CUST_CONFIRM_ORDER;
	}



}
