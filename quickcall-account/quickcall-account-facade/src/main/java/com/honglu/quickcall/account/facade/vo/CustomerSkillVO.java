package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;
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
    /**服务单位（小时/半小时/次）*/
    private String serviceUnit;
    /**已选中的价格*/
    private BigDecimal skillPrice;
    /**技能开关 1：开启  0：关闭*/
    private Integer switchStatus;
    /**已选中的折扣*/
    private BigDecimal discountRate;
    /**折扣列表*/
    private List<BigDecimal>  discontRateList;
    /**技能价格列表*/
    private List<BigDecimal>  skillPriceList;
    /**服务单位列表*/
    private List<String>  serviceUnitList;
    
    
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
	public String getServiceUnit() {
		return serviceUnit;
	}
	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
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
	public List<String> getServiceUnitList() {
		return serviceUnitList;
	}
	public void setServiceUnitList(List<String> serviceUnitList) {
		this.serviceUnitList = serviceUnitList;
	}
	
}