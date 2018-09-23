package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.account.facade.exchange.PayRequest;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:39:53
 */
public class SkillInfoRequest extends PayRequest {
    
	private static final long serialVersionUID = -849088641917599587L;
	
	/**客户编号*/
	private Long  customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	@Override
	public String getBizCode() {
		return OrderRequestType.UPDATE_SKILL_INFO;
	}



}
