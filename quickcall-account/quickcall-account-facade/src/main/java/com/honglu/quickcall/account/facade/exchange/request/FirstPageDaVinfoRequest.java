package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：首页大V技能展示
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:39:53
 */
public class FirstPageDaVinfoRequest  extends  AbstractRequest{
    
	
	private static final long serialVersionUID = 8504198163263776145L;
	/**客户编号（预留暂时不用）*/
	private Long  customerId;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String getBizCode() {
		return  OrderRequestType.QUERY_ORDER_FOR_FIRST_PAGE;
	}
	@Override
	public String toString() {
		return "FirstPageDaVinfoRequest [customerId=" + customerId + "]";
	}
	
	
	
}
