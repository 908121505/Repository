package com.honglu.quickcall.user.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能信息
 * 
 * @Package: com.honglu.quickcall.user.facade.entity
 * @author: chenliuguang
 * @date: 2018年10月19日 上午11:55:32
 */
public class CustomerSkill {
	private Long customerSkillId;

	private Long certifyId;

	private Long customerId;

	private Long skillItemId;

	private String productName;

	private String serviceUnit;

	private BigDecimal productPrice;

	private Integer auditStatus;

	private Integer productStatus;

	private Integer receiveStatus;

	private BigDecimal discountRate;

	private BigDecimal discountPrice;

	private Integer monday;

	private Integer tuesday;

	private Integer wednesday;

	private Integer thursday;

	private Integer friday;

	private Integer saturday;

	private Integer sunday;

	private Date startServiceTime;

	private Date endServiceTime;

	private String productDescribe;

	private Date createTime;

	private Date modifyTime;

	private String createMan;

	private String modifyMan;

	private String remark;

	public Long getCustomerSkillId() {
		return customerSkillId;
	}

	public void setCustomerSkillId(Long customerSkillId) {
		this.customerSkillId = customerSkillId;
	}

	public Long getCertifyId() {
		return certifyId;
	}

	public void setCertifyId(Long certifyId) {
		this.certifyId = certifyId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSkillItemId() {
		return skillItemId;
	}

	public void setSkillItemId(Long skillItemId) {
		this.skillItemId = skillItemId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public String getServiceUnit() {
		return serviceUnit;
	}

	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit == null ? null : serviceUnit.trim();
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public Integer getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getMonday() {
		return monday;
	}

	public void setMonday(Integer monday) {
		this.monday = monday;
	}

	public Integer getTuesday() {
		return tuesday;
	}

	public void setTuesday(Integer tuesday) {
		this.tuesday = tuesday;
	}

	public Integer getWednesday() {
		return wednesday;
	}

	public void setWednesday(Integer wednesday) {
		this.wednesday = wednesday;
	}

	public Integer getThursday() {
		return thursday;
	}

	public void setThursday(Integer thursday) {
		this.thursday = thursday;
	}

	public Integer getFriday() {
		return friday;
	}

	public void setFriday(Integer friday) {
		this.friday = friday;
	}

	public Integer getSaturday() {
		return saturday;
	}

	public void setSaturday(Integer saturday) {
		this.saturday = saturday;
	}

	public Integer getSunday() {
		return sunday;
	}

	public void setSunday(Integer sunday) {
		this.sunday = sunday;
	}

	public Date getStartServiceTime() {
		return startServiceTime;
	}

	public void setStartServiceTime(Date startServiceTime) {
		this.startServiceTime = startServiceTime;
	}

	public Date getEndServiceTime() {
		return endServiceTime;
	}

	public void setEndServiceTime(Date endServiceTime) {
		this.endServiceTime = endServiceTime;
	}

	public String getProductDescribe() {
		return productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe == null ? null : productDescribe.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan == null ? null : createMan.trim();
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan == null ? null : modifyMan.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}