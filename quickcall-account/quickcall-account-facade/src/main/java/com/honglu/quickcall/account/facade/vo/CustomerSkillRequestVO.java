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

	private int customerSkillId;
	private int skillItemExtId;
	private int skillItemId;
	private int switchStatus;

	public void setCustomerSkillId(int customerSkillId) {
		this.customerSkillId = customerSkillId;
	}

	public int getCustomerSkillId() {
		return customerSkillId;
	}

	public void setSkillItemExtId(int skillItemExtId) {
		this.skillItemExtId = skillItemExtId;
	}

	public int getSkillItemExtId() {
		return skillItemExtId;
	}

	public void setSkillItemId(int skillItemId) {
		this.skillItemId = skillItemId;
	}

	public int getSkillItemId() {
		return skillItemId;
	}

	public void setSwitchStatus(int switchStatus) {
		this.switchStatus = switchStatus;
	}

	public int getSwitchStatus() {
		return switchStatus;
	}

}