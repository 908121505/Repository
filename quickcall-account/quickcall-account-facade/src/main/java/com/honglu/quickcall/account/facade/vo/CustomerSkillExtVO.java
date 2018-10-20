package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;
import java.util.List;

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
    /**折扣列表*/
    private List<BigDecimal>  discontRateList;
    /**技能价格列表*/
    private List<BigDecimal>  skillPriceList;
    /**服务单位列表*/
    private List<String>  serviceUnitList;
    
    
	public Long getSkillItemExtId() {
		return skillItemExtId;
	}
	public void setSkillItemExtId(Long skillItemExtId) {
		this.skillItemExtId = skillItemExtId;
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