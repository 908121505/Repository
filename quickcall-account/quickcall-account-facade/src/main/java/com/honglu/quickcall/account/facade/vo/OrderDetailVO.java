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

	private static final long serialVersionUID = 2191262960016676469L;
	/**订单ID*/
	private Long  orderId;
	/**服务方编号*/
	private Long  serviceId;
	private Long  customerId;
	/**对方头像*/
	private String  headPortraitUrl;
	/**对方靓号*/
	private Long  appId;
	/**对方昵称*/
	private String  nickName;
	
	/**年龄*/
	private Date   birthday;
	/**年龄*/
	private int   age;
	/**性别*/
	private int   sex;
	/**实名认证标识 0=未认证,1=待审核,2=已通过,3=拒绝*/
	private int   identityStatus;
	/**技能名称*/
	private String skillName ;
	/**服务单价价格*/
	private BigDecimal  servicePrice;
	
	/**服务单位*/
	private String serviceUnit ;
	
	/**订单数量*/
	private Integer  orderNum;
	/**订单金额*/
	private BigDecimal  orderAmount;
	/**大V状态*/
	private int   orderStatus;
	/**对方备注*/
	private String  remark ;
	/**下单时间*/
	private Date  orderTime;
	/**开始时间*/
	private Date  startTime;
	/**结束时间*/
	private Date  endTime;
	
	
	private Long  skillItemId ;

	
	/**倒计时秒数*/
	private Long  countDownSeconds; 

	private Long  customerIsEvaluate ;
	/**大V接单时间*/
	private Date  receiveOrderTime ;
	
    private BigDecimal discountRate;

    
    /**服务开始时间*/
    private Date  startServiceTime;
    /**预计结束时间*/
    private Date  expectEndTime;
    
    /**技能类型*/
    private Integer  skillType;
    /**券金额*/
    private BigDecimal  couponPrice;
    /**预约开始时间*/
    private  Date   appointTime;

	private String couponName;//客户订单优惠券的名称'


	
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
	public Date getStartServiceTime() {
		return startServiceTime;
	}
	public void setStartServiceTime(Date startServiceTime) {
		this.startServiceTime = startServiceTime;
	}
	public Date getExpectEndTime() {
		return expectEndTime;
	}
	public void setExpectEndTime(Date expectEndTime) {
		this.expectEndTime = expectEndTime;
	}
	public BigDecimal getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}
	public Date getReceiveOrderTime() {
		return receiveOrderTime;
	}
	public void setReceiveOrderTime(Date receiveOrderTime) {
		this.receiveOrderTime = receiveOrderTime;
	}
	public Long getCountDownSeconds() {
		return countDownSeconds;
	}
	public void setCountDownSeconds(Long countDownSeconds) {
		this.countDownSeconds = countDownSeconds;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
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
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public int getIdentityStatus() {
		return identityStatus;
	}
	public void setIdentityStatus(int identityStatus) {
		this.identityStatus = identityStatus;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
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
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
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
	public Long getSkillItemId() {
		return skillItemId;
	}
	public void setSkillItemId(Long skillItemId) {
		this.skillItemId = skillItemId;
	}

	public Long getCustomerIsEvaluate() {
		return customerIsEvaluate;
	}

	public void setCustomerIsEvaluate(Long customerIsEvaluate) {
		this.customerIsEvaluate = customerIsEvaluate;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}
}
