package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：取消订单入参
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月26日 下午3:14:59 
 */
public class CancelOrderRequest extends AbstractRequest {
    
	private static final long serialVersionUID = 8966058865636160692L;

	/**订单编号*/
	private Long  orderId;
	
	/**选中原因*/
	private  String   selectReason;
	/**备注原因*/
	private  String   remarkReason;

	public String getSelectReason() {
		return selectReason;
	}

	public void setSelectReason(String selectReason) {
		this.selectReason = selectReason;
	}

	public String getRemarkReason() {
		return remarkReason;
	}

	public void setRemarkReason(String remarkReason) {
		this.remarkReason = remarkReason;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	@Override
	public String getBizCode() {
		return OrderRequestType.CANCEL_ORDER;
	}

	@Override
	public String toString() {
		return "CancelOrderRequest [orderId=" + orderId + "]";
	}

	


}
