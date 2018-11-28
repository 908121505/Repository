package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：查询大V产品数据
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:39:53
 */
public class OrderDaVSkillRequest extends AbstractRequest {
    
	
	private static final long serialVersionUID = -8443725563523486638L;
	/**客户编号*/
	private Long  customerId;

	/**
	 * 请求头里的，包含设备信息，登录人ID等
	 */
	private String userAgent;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public String getBizCode() {
		return OrderRequestType.ORDER_DAV_PRODUCT_LIST;
	}

	@Override
	public String toString() {
		return "OrderDaVProductRequest [customerId=" + customerId + "]";
	}

	


}
