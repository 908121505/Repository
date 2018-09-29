package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：用户接收到的订单详情
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月24日 下午1:18:42 
 */
public class OrderReceiveOrderListVO implements  Serializable{

	private static final long serialVersionUID = -6622980617460273069L;
	/**主播ID*/
	private Long  orderId;
	/**主播昵称*/
	private String  nickName;
	/**下单人iD*/
	private Long  buyerId;
	/**主播头像*/
	private String  headPortraitUrl;
	/**产品ID*/
	private Long  productId; 
	/**主播昵称*/
	private String productName ;
	/**产品价格*/
	private BigDecimal  price;
	/**订单数量*/
	private Integer  orderNum;
	/**订单金额*/
	private BigDecimal  orderAmounts;
	/**
	 * 订单状态：
	 * 1.下单未支付（已下单，未支付）;2.下单未支付用户取消（已下单，30分钟内未支付）3.已支付（已下单，支付完成）;
	 * 4.超时未接（已支付，大V30分钟内未接单）;5.接单前用户自主取消;6.大v拒接（支付完成，大V拒绝，需要考虑账户退款）;
	 * 7.大V接单;8.接单后用户取消（注意退款给用户）;9.大V确认开始;10.用户同意（大V立即开始请求）;
	 * 11.用户拒绝（大V立即开始请求）12.进行中（用户同意或者到约定的订单开始时间）;13.用户退款;
	 * 14.同意退款订单作废（退款到客户）;15.订单完成（大V拒绝）;16.订单完成（正常完成）;
	 */
	private Integer  orderStatus;
	
	private String  startTimeStr;
	
	/**支付时间*/
	private Date  paymentTime;
	
	
	

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}

	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getOrderAmounts() {
		return orderAmounts;
	}

	public void setOrderAmounts(BigDecimal orderAmounts) {
		this.orderAmounts = orderAmounts;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	
}
