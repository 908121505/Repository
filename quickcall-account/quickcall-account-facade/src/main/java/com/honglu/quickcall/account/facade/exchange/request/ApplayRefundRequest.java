package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户申请退款/完成
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:22:05 
 */
public class ApplayRefundRequest extends AbstractRequest {
    
	private static final long serialVersionUID = -8484581694258195919L;
	/**客户编号*/
	private Long  orderId;
	/**请求类型1：退款 2：完成*/
	private Integer  type;
	/**退款理由*/
	private String  refundReason;

	


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
	


	public String getRefundReason() {
		return refundReason;
	}


	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}


	@Override
	public String getBizCode() {
		return OrderRequestType.CUST_APPLAY_REFUND;
	}



}
