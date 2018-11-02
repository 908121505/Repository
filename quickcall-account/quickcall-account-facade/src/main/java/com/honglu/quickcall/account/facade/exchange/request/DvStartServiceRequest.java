package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：大V开始服务
 * @Package: com.honglu.quickcall.account.facade.exchange.req
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:22:05 
 */
public class DvStartServiceRequest extends AbstractRequest {
    
	private static final long serialVersionUID = 4399342468232815453L;
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
		return OrderRequestType.DV_START_SERVICE;
	}

	@Override
	public String toString() {
		return "DvStartServiceRequest [orderId=" + orderId + "]";
	}

	


}
