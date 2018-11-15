package com.calf.module.order.vo;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单信息展示使用
 * @Package: com.calf.module.order.vo 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午4:36:37
 */
public class OrderVO {
	//订单id
	private String orderId;
	//接单用户id
	private String receivedOrderId;
	//接单用户昵称
	private String receiveOrderUserNickName;
	//下单用户id
	private String placeOrderId;
	//下单用户昵称
	private String placeOrderUserNickName;
	//服务类型
	private String serviceType;
	//开始时间
	private String startTime;
	//结束时间
	private String endTime;
	//单价（次/音符、半小时/音符、小时/音符）
	private String unitPrice;
	//折扣类型
	private String discountType;
	//订单金额（单位：元）
	private String orderAmount;
	//订单创建时间
	private String orderCreateTime;
	//订单取消原因
	private String remarkReason;
	//订单数量
	private String orderNum;
	//订单状态
	private String orderStatus;
	//订单状态值
	private String orderStatusVal;
	/**服务方手机号码*/
	private Long   servicePhone;
	/**下单方手机号码*/
	private Long   customerPhone;
	/*取消或者完成原因**/
	private String compulsionReason;
	/*技能ID**/
	private String skillItemId;
	/*服务单位（小时/半小时/次）**/
    private String serviceUnit;
    
    

	public String getServiceUnit() {
		return serviceUnit;
	}

	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}

	public String getSkillItemId() {
		return skillItemId;
	}

	public void setSkillItemId(String skillItemId) {
		this.skillItemId = skillItemId;
	}

	public String getCompulsionReason() {
		return compulsionReason;
	}

	public void setCompulsionReason(String compulsionReason) {
		this.compulsionReason = compulsionReason;
	}

	public Long getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(Long servicePhone) {
		this.servicePhone = servicePhone;
	}

	public Long getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(Long customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getReceivedOrderId() {
		return receivedOrderId;
	}

	public void setReceivedOrderId(String receivedOrderId) {
		this.receivedOrderId = receivedOrderId;
	}

	public String getReceiveOrderUserNickName() {
		return receiveOrderUserNickName;
	}

	public void setReceiveOrderUserNickName(String receiveOrderUserNickName) {
		this.receiveOrderUserNickName = receiveOrderUserNickName;
	}

	public String getPlaceOrderId() {
		return placeOrderId;
	}

	public void setPlaceOrderId(String placeOrderId) {
		this.placeOrderId = placeOrderId;
	}

	public String getPlaceOrderUserNickName() {
		return placeOrderUserNickName;
	}

	public void setPlaceOrderUserNickName(String placeOrderUserNickName) {
		this.placeOrderUserNickName = placeOrderUserNickName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getRemarkReason() {
		return remarkReason;
	}

	public void setRemarkReason(String remarkReason) {
		this.remarkReason = remarkReason;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderStatusVal() {
		return orderStatusVal;
	}

	public void setOrderStatusVal(String orderStatusVal) {
		this.orderStatusVal = orderStatusVal;
	}
}