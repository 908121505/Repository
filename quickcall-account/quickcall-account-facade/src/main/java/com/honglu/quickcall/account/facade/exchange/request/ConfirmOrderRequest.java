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
    
	
	private static final long serialVersionUID = 4768919318889438929L;
	/**客户编号*/
	private Long  orderId;



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}






	@Override
	public String getBizCode() {
		return OrderRequestType.CUST_CONFIRM_ORDER;
	}



	@Override
	public String toString() {
		return "ConfirmOrderRequest [orderId=" + orderId + "]";
	}




	


}
