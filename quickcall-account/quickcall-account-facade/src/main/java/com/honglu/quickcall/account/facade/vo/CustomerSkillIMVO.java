package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：用户技能展示for IM
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月26日 下午3:43:26
 */
public class CustomerSkillIMVO implements  Serializable{

	private static final long serialVersionUID = 5268284255984360674L;
	/**产品价格*/
	private BigDecimal  servicePrice;
	/**产品单位*/
	private String  serviceUnit;
	/**技能名称*/
	private String skillItemName ;
	/**技能ICON*/
	private String  icon;
	/**用户技能ID*/
	private Long  customerSkillId;
	
	
	
	public Long getCustomerSkillId() {
		return customerSkillId;
	}
	public void setCustomerSkillId(Long customerSkillId) {
		this.customerSkillId = customerSkillId;
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
