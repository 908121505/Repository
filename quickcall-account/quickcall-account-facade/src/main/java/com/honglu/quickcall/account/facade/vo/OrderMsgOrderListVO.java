package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月24日 下午1:18:42 
 */
public class OrderMsgOrderListVO implements  Serializable{

	
	private static final long serialVersionUID = 2165524818869032094L;
	/**主播ID*/
	private Long  orderId;
	/**更改时间*/
	private Date  modifyTime;
	/**消息提醒*/
	private String  msgContent;
	/**订单标识 C:用户是下单人  V：用户是接单人*/
	private String  customerFlag;
	/**主播昵称*/
	private String  nickName;
	/**下单人iD*/
	private Long  customerId;
	/**接单人iD*/
	private Long  serviceId;
	
	/**主播头像*/
	private String  headPortraitUrl;
	/**产品价格*/
	private BigDecimal  servicePrice;
	/**产品单位*/
	private String  serviceUnit;
	/**订单数量*/
	private Integer  orderNum;
	/**订单金额*/
	private BigDecimal  orderAmount;
	/**
	 * 2.待接单;4.取消（用户下单后自主取消）;6.取消（待接单-大V超时未接）;8.已拒绝（大V拒绝接单）;10.待开始（大V接单）;
	 * 12.取消（大V接单后用户自主取消）;14.取消（待开始5分钟大V未发起开始服务）;16.待开始(大V发起开始服务);18.取消（大V发起开始服务用户自主取消）;
	 * 20.取消（大V发起开始服务用户5分钟未接）;22.取消（大V接单，大V同一时间其它订单取消）;24.进行中（大V发起开始服务用户5分钟内同意）;
	 * 26.进行中（用户发起完成服务）;28.进行中（大V发起完成服务）;30.已完成（用户同意对方）;32.已完成（订单开始12小时系统自动完成）;
	 * 34.已完成（用户评价完成）;
	 */
	private Integer  orderStatus;
	/**技能名称*/
	private String skillItemName ;
	/**技能ICON*/
	private String  icon;
	/**声优标识*/
	private Integer  vStatus;
	
	public Integer getvStatus() {
		return vStatus;
	}
	public void setvStatus(Integer vStatus) {
		this.vStatus = vStatus;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getCustomerFlag() {
		return customerFlag;
	}
	public void setCustomerFlag(String customerFlag) {
		this.customerFlag = customerFlag;
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
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}
	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}
	public BigDecimal getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}
	public String getServiceUnit() {
		return serviceUnit;
	}
	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getSkillItemName() {
		return skillItemName;
	}
	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
