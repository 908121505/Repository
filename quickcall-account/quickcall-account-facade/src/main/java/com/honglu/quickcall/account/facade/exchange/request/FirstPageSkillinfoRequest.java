package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：首页技能类型展示
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:39:53
 */
public class FirstPageSkillinfoRequest  extends  AbstractRequest{
    
	
	private static final long serialVersionUID = 7379238487444675373L;
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
		return  OrderRequestType.QUERY_SKILL_NAME_FOR_FIRST_PAGE;
	}
	@Override
	public String toString() {
		return "FirstPageSkillinfoRequest [customerId=" + customerId + "]";
	}
	
	
}
