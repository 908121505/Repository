//package com.honglu.quickcall.account.facade.exchange.request;
//
//import com.honglu.quickcall.account.facade.code.OrderRequestType;
//import com.honglu.quickcall.common.api.exchange.AbstractRequest;
//
///**   
// * Copyright © 2018 www.xiaoniu.com All rights reserved.
// * 
// * 功能描述：再来一单入参
// * @Package: com.honglu.quickcall.account.facade.exchange.request 
// * @author: chenliuguang   
// * @date: 2018年9月23日 下午8:22:05 
// */
//public class CopyOrderRequest extends AbstractRequest {
//	private static final long serialVersionUID = -7696341780726644803L;
//	/**客户编号*/
//	private Long  orderId;
//
//
//
//	public Long getOrderId() {
//		return orderId;
//	}
//
//
//
//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}
//
//
//
//	@Override
//	public String getBizCode() {
//		return OrderRequestType.CUST_COPY_ORDER;
//	}
//
//
//
//	@Override
//	public String toString() {
//		return "CopyOrderRequest [orderId=" + orderId + "]";
//	}
//
//
//
//}
