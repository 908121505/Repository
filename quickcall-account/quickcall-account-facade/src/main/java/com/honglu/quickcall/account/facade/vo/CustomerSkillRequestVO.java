package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能扩展信息
 * 
 * @Package: com.honglu.quickcall.account.facade.resp
 * @author: chenliuguang
 * @date: 2018年10月19日 下午3:44:13
 */
public class CustomerSkillRequestVO {

	/**客户技能ID*/
	private Long customerSkillId;
	/**技能项扩展信息ID*/
	private Long skillItemExtId;
	/**技能项信息ID*/
	private Long skillItemId;
	/**开启状态*/
	private Integer switchStatus;
	/**选中折扣率10：无折扣   7.5  8 等*/
	private BigDecimal  discountRate;
	
	
	
	public Long getCustomerSkillId() {
		return customerSkillId;
	}
	public void setCustomerSkillId(Long customerSkillId) {
		this.customerSkillId = customerSkillId;
	}
	
	public Long getSkillItemId() {
		return skillItemId;
	}
	public void setSkillItemId(Long skillItemId) {
		this.skillItemId = skillItemId;
	}
	public Integer getSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(Integer switchStatus) {
		this.switchStatus = switchStatus;
	}
	public Long getSkillItemExtId() {
		return skillItemExtId;
	}
	public void setSkillItemExtId(Long skillItemExtId) {
		this.skillItemExtId = skillItemExtId;
	}
	public BigDecimal getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}
	
	
	

	

}