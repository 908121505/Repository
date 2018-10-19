package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月19日 下午3:44:13
 */
public class CustomerSkillVO {
	
	/**技能ID*/
	private Long  customerSkillId;
	/**技能项（分类名称）*/
	private String  skillItemName;
	/**技能项ID(分类ID)*/
    private Long skillItemId;
    /**已选中的价格*/
    private BigDecimal skillPrice;
    /**技能开关 1：开启  0：关闭*/
    private Integer switchStatus;
    /**接单开关 1：开启  0：关闭*/
    private Integer receiveStatus;
    /**已选中的折扣*/
    private BigDecimal discountRate;
    /**折扣列表*/
    private List<BigDecimal>  discontRateList;
    /**服务结束时间*/
    private Date endServiceTime;
    /**技能价格列表*/
    private List<BigDecimal>  skillPriceList;
    /**周一状态 1：选中  0：未选中*/
    private Integer monday;

    /**周一状态 1：选中  0：未选中*/
    private Integer tuesday;

    /**周二状态 1：选中  0：未选中*/
    private Integer wednesday;

    /**周三状态 1：选中  0：未选中*/
    private Integer thursday;

    /**周四状态 1：选中  0：未选中*/
    private Integer friday;

    /**周五状态 1：选中  0：未选中*/
    private Integer saturday;

    /**周六状态 1：选中  0：未选中*/
    private Integer sunday;
	public Long getCustomerSkillId() {
		return customerSkillId;
	}
	public void setCustomerSkillId(Long customerSkillId) {
		this.customerSkillId = customerSkillId;
	}
	public String getSkillItemName() {
		return skillItemName;
	}
	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}
	public Long getSkillItemId() {
		return skillItemId;
	}
	public void setSkillItemId(Long skillItemId) {
		this.skillItemId = skillItemId;
	}
	public BigDecimal getSkillPrice() {
		return skillPrice;
	}
	public void setSkillPrice(BigDecimal skillPrice) {
		this.skillPrice = skillPrice;
	}
	public Integer getSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(Integer switchStatus) {
		this.switchStatus = switchStatus;
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
	public List<BigDecimal> getDiscontRateList() {
		return discontRateList;
	}
	public void setDiscontRateList(List<BigDecimal> discontRateList) {
		this.discontRateList = discontRateList;
	}
	public List<BigDecimal> getSkillPriceList() {
		return skillPriceList;
	}
	public void setSkillPriceList(List<BigDecimal> skillPriceList) {
		this.skillPriceList = skillPriceList;
	}
	public Date getEndServiceTime() {
		return endServiceTime;
	}
	public void setEndServiceTime(Date endServiceTime) {
		this.endServiceTime = endServiceTime;
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
	
	
	
	
    
}