package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：用户发起订单详情
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月24日 下午1:17:55 
 */
public class OrderSendOrderListVO implements  Serializable{

	private static final long serialVersionUID = 7888760975722377952L;
	/**主播ID*/
	private Long  orderId;
	/**主播昵称*/
	private String  nickName;
	/**服务人iD*/
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
	
	/**倒计时秒数*/
	private Long  countDownSeconds; 
	
	/**下单时间*/
	private Date  orderTime ;
	/**接单时间*/
	private Date  receiveOrderTime ;
	
	/**客户技能ID*/
	private Long  customerSkillId;
	
	/**技能开关：1=开启,0=关闭*/
	private Integer  switchStatus;
	/**声优开启立即服务时间*/
	private Date  startServiceTime;
	/**预计结束时间*/
	private Date expectEndTime;
	
	private Date  appointTime;
	
	private Integer  skillType;
	
	
	
	
	
	
	
	
	
	
	
	
	public Integer getSkillType() {
		return skillType;
	}
	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}
	public Date getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}
	public Date getExpectEndTime() {
		return expectEndTime;
	}
	public void setExpectEndTime(Date expectEndTime) {
		this.expectEndTime = expectEndTime;
	}
	public Date getStartServiceTime() {
		return startServiceTime;
	}
	public void setStartServiceTime(Date startServiceTime) {
		this.startServiceTime = startServiceTime;
	}
	public Integer getSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(Integer switchStatus) {
		this.switchStatus = switchStatus;
	}
	public Long getCustomerSkillId() {
		return customerSkillId;
	}
	public void setCustomerSkillId(Long customerSkillId) {
		this.customerSkillId = customerSkillId;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getReceiveOrderTime() {
		return receiveOrderTime;
	}
	public void setReceiveOrderTime(Date receiveOrderTime) {
		this.receiveOrderTime = receiveOrderTime;
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
	
	
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
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
	public Long getCountDownSeconds() {
		return countDownSeconds;
	}
	public void setCountDownSeconds(Long countDownSeconds) {
		this.countDownSeconds = countDownSeconds;
	}
	

	


}
