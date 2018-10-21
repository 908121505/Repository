package com.honglu.quickcall.account.facade.vo;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能扩展信息
 * 
 * @Package: com.honglu.quickcall.account.facade.vo
 * @author: chenliuguang
 * @date: 2018年10月19日 下午3:44:13
 */
public class CustomerSkillRequestVO {

	private Long customerSkillId;
	private Long skillItemExtId;
	private Long skillItemId;
	private Integer switchStatus;
	
	
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
	
	

	

}