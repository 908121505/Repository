package com.honglu.quickcall.account.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：下单入参
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:39:53
 */
public class OrderSaveRequest extends AbstractRequest {
    
	private static final long serialVersionUID = 2391674663886831167L;
	/**客户编号*/
	private Long  customerId;
	/**大V编号*/
	private Long  sellerId;
	/**产品ID*/
	private Long  productId;
	/**技能Id*/
	private Long  skillId;
	/**单价*/
	private BigDecimal  price;
	/**项目名称*/
	private String  productName ;
	/**订单数量*/
	private Integer  orderNum;
	/**服务开始时间*/
	private String  startTimeStr;
	
	
	
	public String getStartTimeStr() {
		return startTimeStr;
	}


	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}


	public Long getSellerId() {
		return sellerId;
	}


	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}


	public Long getSkillId() {
		return skillId;
	}


	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Integer getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}


	@Override
	public String getBizCode() {
		return OrderRequestType.ORDER_SAVE;
	}



}
