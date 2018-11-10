package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单消息列表
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午8:20:18 
 */
public class MsgOrderListRequest extends AbstractRequest {
    
	private static final long serialVersionUID = 751855671487087996L;
	/**客户编号*/
	private Long  customerId;
	/**当前页*/
	private Integer  pageIndex;
	

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	



	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	@Override
	public String getBizCode() {
		return OrderRequestType.MSG_ORDER_LIST;
	}

	@Override
	public String toString() {
		return "MsgOrderListRequest [customerId=" + customerId + ", pageIndex=" + pageIndex + "]";
	}

	
	



}
