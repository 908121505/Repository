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
public class ReceiveOrderVO {
	//用户id
	private String customerId;
	//接单用户id
	private String customerNickName;
	//服务类型
	private String serviceType;
	//接单状态
	private String receiveStatus;
	//结束时间
	private String endTime;
	//单价（次/音符、半小时/音符、小时/音符）
	private String serviceUnit;
	//价格等级
	private String skillRange;
	//折扣类型
	private String discountType;
	//创建时间
	private String createTime;
	//修改时间
	private String modifyTime;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerNickName() {
		return customerNickName;
	}

	public void setCustomerNickName(String customerNickName) {
		this.customerNickName = customerNickName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getServiceUnit() {
		return serviceUnit;
	}

	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}

	public String getSkillRange() {
		return skillRange;
	}

	public void setSkillRange(String skillRange) {
		this.skillRange = skillRange;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
}