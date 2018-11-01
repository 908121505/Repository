package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.util.List;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户订单优惠券信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: wq
 * @date: 2018-10-31
 */
public class CustomerCouponVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String orderId;//使用该券的订单编号
	private String couponId;//券编号
	private String couponName;//券的名称
	private int isPermanent;//是否永久（0=不是永久，1=永久）
	private int couponType;//券类型 （0=抵扣券，1=打折券）
	private String couponCode;//券码，前端使用
	private String startTime;//券生效的起始时间
	private String endTime;//券的结束时间
	private String activityId;//活动编号
	private int couponPrice;//券的价值

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public int getIsPermanent() {
		return isPermanent;
	}

	public void setIsPermanent(int isPermanent) {
		this.isPermanent = isPermanent;
	}

	public int getCouponType() {
		return couponType;
	}

	public void setCouponType(int couponType) {
		this.couponType = couponType;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
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

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public int getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(int couponPrice) {
		this.couponPrice = couponPrice;
	}
}
