package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：用户/大V完成服务
 * @Package: com.honglu.quickcall.account.facade.exchange.req
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:22:05 
 */
public class FinishOrderRequest extends AbstractRequest {
    
	private static final long serialVersionUID = -2275579186626656793L;
	/**客户编号*/
	private Long  orderId;
	/**请求类型1：大V发起   2：用户发起  */
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
		return OrderRequestType.DV_CONFRIM_ORDER;
	}



	@Override
	public String toString() {
		return "DvConfirmRefundRequest [orderId=" + orderId + ", type=" + type + "]";
	}



}
