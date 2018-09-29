package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单详情
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月24日 下午1:17:55 
 */
public class OrderDetailVO implements  Serializable{

	private static final long serialVersionUID = -4211103935474073578L;
	/**主播ID*/
	private Long  orderId;
	/**对方客户编号*/
	private Long  customerId;
	/**对方昵称*/
	private String  anotherNickName;
	/**对方头像*/
	private String  anotherHeadPortraitUrl;
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
	/**下单时间*/
	private Date  orderTime;
	/**年龄*/
	private int   age;
	/**性别*/
	private int   sex;
	/**大V状态*/
	private int   dvStatus;
	/**退款原因*/
	private String  refundReason ;

	/**支付时间*/
	private Date  paymentTime;
	
	
	

	public Long getCustomerId() {
		return customerId;
	}




	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}




	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}




	public Date getPaymentTime() {
		return paymentTime;
	}

	
	

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getDvStatus() {
		return dvStatus;
	}

	public void setDvStatus(int dvStatus) {
		this.dvStatus = dvStatus;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getAnotherNickName() {
		return anotherNickName;
	}

	public void setAnotherNickName(String anotherNickName) {
		this.anotherNickName = anotherNickName;
	}

	public String getAnotherHeadPortraitUrl() {
		return anotherHeadPortraitUrl;
	}

	public void setAnotherHeadPortraitUrl(String anotherHeadPortraitUrl) {
		this.anotherHeadPortraitUrl = anotherHeadPortraitUrl;
	}

	/**
	 * 订单状态：
	 * 1.下单未支付（已下单，未支付）;  2.下单未支付系统取消（已下单，30分钟内未支付）;
	 * 3.已支付（已下单，支付完成）;4.超时未接（已支付，大V30分钟内未接单）;
	 * 5.大V接单前用户自主取消;6.大V拒绝订单（支付完成，大V拒绝，客户退款）;
	 * 7.大V接受订单;8.接单后用户取消订单（用户退款）;9.大V确认开始订单;
	 * 10.用户同意（响应大V立即服务请求）;11.用户拒绝（响应大V立即服务请求）;
	 * 12.进行中（用户同意或者到约定的订单开始时间）;13.用户申请退款;
	 * 14.订单作废（大V同意退款，客户退款）;15.订单完成（大V拒绝退款）;16.订单完成（正常完成）;
	 * 
	 */
	private Integer  orderStatus;
	
	private String  startTimeStr;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
