package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：支付订单
 * @Package: com.honglu.quickcall.account.facade.exchange.req
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:22:05 
 */
public class PayOrderRequest extends AbstractRequest {
    
	private static final long serialVersionUID = -6317628619555302051L;
	/**客户编号*/
	private Long  orderId;
	/**充值标识1：支付宝充值  2.微信支付  3：零钱支付*/
	private int  rechageType ;

	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public int getRechageType() {
		return rechageType;
	}


	public void setRechageType(int rechageType) {
		this.rechageType = rechageType;
	}


	@Override
	public String getBizCode() {
		return OrderRequestType.CUST_PAY_ORDER;
	}


	@Override
	public String toString() {
		return "PayOrderRequest [orderId=" + orderId + "]";
	}



}
