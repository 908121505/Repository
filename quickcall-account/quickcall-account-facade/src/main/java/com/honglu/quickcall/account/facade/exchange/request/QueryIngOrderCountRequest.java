package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：查询进行中订单数量
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月27日 下午5:25:19 
 */
public class QueryIngOrderCountRequest extends AbstractRequest {
    
	private static final long serialVersionUID = -2764746495237669198L;
	/**消费方客户编号*/
	private Long  buyerId;
	/**大V方客户编号*/
	private Long  sellerId;

	public Long getBuyerId() {
		return buyerId;
	}



	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}



	public Long getSellerId() {
		return sellerId;
	}



	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}



	@Override
	public String getBizCode() {
		return OrderRequestType.QUERY_ING_ORDER_COUNT;
	}



	@Override
	public String toString() {
		return "QueryIngOrderCountRequest [buyerId=" + buyerId + ", sellerId=" + sellerId + "]";
	}




}
