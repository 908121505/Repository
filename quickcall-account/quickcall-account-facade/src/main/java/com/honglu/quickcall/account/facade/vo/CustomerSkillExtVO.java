package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能扩展信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月19日 下午3:44:13
 */
public class CustomerSkillExtVO {
	
	/**技能项扩展ID*/
	private Long  skillItemExtId;
    /**折扣*/
    private BigDecimal  discontRateValue;
    /**技能价格*/
    private BigDecimal  skillPriceValue;
    /**服务单位*/
    private String  serviceUnitValue;
    /**技能等级*/
    private Integer  skillRangeValue;
    
    
	public Long getSkillItemExtId() {
		return skillItemExtId;
	}
	public void setSkillItemExtId(Long skillItemExtId) {
		this.skillItemExtId = skillItemExtId;
	}
	public BigDecimal getDiscontRateValue() {
		return discontRateValue;
	}
	public void setDiscontRateValue(BigDecimal discontRateValue) {
		this.discontRateValue = discontRateValue;
	}
	public BigDecimal getSkillPriceValue() {
		return skillPriceValue;
	}
	public void setSkillPriceValue(BigDecimal skillPriceValue) {
		this.skillPriceValue = skillPriceValue;
	}
	public String getServiceUnitValue() {
		return serviceUnitValue;
	}
	public void setServiceUnitValue(String serviceUnitValue) {
		this.serviceUnitValue = serviceUnitValue;
	}
	public Integer getSkillRangeValue() {
		return skillRangeValue;
	}
	public void setSkillRangeValue(Integer skillRangeValue) {
		this.skillRangeValue = skillRangeValue;
	}

	
	
    
	
    
}