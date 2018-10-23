package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：用户同意大V服务完成
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年10月23日 下午1:22:38 
 */
public class CustConfirmFinishRequest extends AbstractRequest {
    
	private static final long serialVersionUID = 4495455375616928799L;
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
		return OrderRequestType.CUST_CONFIRM_FINISH_REFUND;
	}



}
