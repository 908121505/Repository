package com.honglu.quickcall.task.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单信息
 * @Package: com.honglu.quickcall.task.entity 
 * @author: chenliuguang   
 * @date: 2018年10月23日 下午5:24:48
 */
public class TaskOrder {
   
	private Long orderId;

    private Long customerId;

    private BigDecimal orderAmounts;

    private Date receiveOrderTime;

    private Date orderTime;

    private Integer orderStatus;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getOrderAmounts() {
		return orderAmounts;
	}

	public void setOrderAmounts(BigDecimal orderAmounts) {
		this.orderAmounts = orderAmounts;
	}

	public Date getReceiveOrderTime() {
		return receiveOrderTime;
	}

	public void setReceiveOrderTime(Date receiveOrderTime) {
		this.receiveOrderTime = receiveOrderTime;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
}