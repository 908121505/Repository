package com.calf.module.order.vo;

import java.math.BigDecimal;
import java.util.Date;
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
   
	private String orderId;
	private String servNickName ;
	
	private String  custNickName;
	
	private String  orderType;
	
	private BigDecimal  price;
	
    private Integer orderNum;
    private BigDecimal orderAmounts;
    
    private Date confirmTime;
    private Date startTime;
    private Date endTime;
    private Date orderTime;
    private Date finishTime;
    private Byte orderStatus;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getServNickName() {
		return servNickName;
	}
	public void setServNickName(String servNickName) {
		this.servNickName = servNickName;
	}
	public String getCustNickName() {
		return custNickName;
	}
	public void setCustNickName(String custNickName) {
		this.custNickName = custNickName;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Byte getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Byte orderStatus) {
		this.orderStatus = orderStatus;
	}
    

    
    
   
}