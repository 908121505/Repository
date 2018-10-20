package com.honglu.quickcall.user.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 大V技能审核上传
 * @author zhaozheyi
 *
 */
public class SaveSkillAuditRequest extends UserCenterRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7695840296785678017L;
	//技能编号
	private Long skillItemId;
	//主播编号
	private Long customerId;
	//声音Url
	private String skillVoiceUrl;
	//声音时长
	private BigDecimal skillVoiceTime;
	
	public Long getSkillItemId() {
		return skillItemId;
	}

	public void setSkillItemId(Long skillItemId) {
		this.skillItemId = skillItemId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public String getSkillVoiceUrl() {
		return skillVoiceUrl;
	}

	public void setSkillVoiceUrl(String skillVoiceUrl) {
		this.skillVoiceUrl = skillVoiceUrl;
	}

	public BigDecimal getSkillVoiceTime() {
		return skillVoiceTime;
	}

	public void setSkillVoiceTime(BigDecimal skillVoiceTime) {
		this.skillVoiceTime = skillVoiceTime;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.SAVE_DV_SKILL_AUDIT;
	}
	
}
