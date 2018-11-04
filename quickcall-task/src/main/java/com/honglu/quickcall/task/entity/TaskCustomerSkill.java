package com.honglu.quickcall.task.entity;

import java.math.BigDecimal;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单信息
 * @Package: com.honglu.quickcall.task.entity 
 * @author: chenliuguang   
 * @date: 2018年10月23日 下午5:24:48
 */
public class TaskCustomerSkill {
   
    private  Long  orderId;
    
    private Long customerId;
    
    private Long  serviceId;

    private BigDecimal orderAmounts;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public BigDecimal getOrderAmounts() {
		return orderAmounts;
	}

	public void setOrderAmounts(BigDecimal orderAmounts) {
		this.orderAmounts = orderAmounts;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "TaskOrder [orderId=" + orderId + ", customerId=" + customerId + ", serviceId=" + serviceId
				+ ", orderAmounts=" + orderAmounts + "]";
	}

	
	
   

}