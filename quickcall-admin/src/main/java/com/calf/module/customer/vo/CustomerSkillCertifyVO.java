package com.calf.module.customer.vo;

/**
 * 用户技能审核输出
 * 
 * @author zhaozheyi
 *
 */
public class CustomerSkillCertifyVO {

	private String certifyId;
	private String customerId;
	private String realName;
	private String nickName;
	private String skillItemId;
	private String skillItemName;
	private Integer auditStatus;
	private String skillVoiceUrlTmp;
	private String skillVoiceTimeTmp;
	private String isAudit;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSkillItemId() {
		return skillItemId;
	}

	public void setSkillItemId(String skillItemId) {
		this.skillItemId = skillItemId;
	}

	public String getSkillItemName() {
		return skillItemName;
	}

	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getSkillVoiceUrlTmp() {
		return skillVoiceUrlTmp;
	}

	public void setSkillVoiceUrlTmp(String skillVoiceUrlTmp) {
		this.skillVoiceUrlTmp = skillVoiceUrlTmp;
	}

	public String getSkillVoiceTimeTmp() {
		return skillVoiceTimeTmp;
	}

	public void setSkillVoiceTimeTmp(String skillVoiceTimeTmp) {
		this.skillVoiceTimeTmp = skillVoiceTimeTmp;
	}

	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public String getCertifyId() {
		return certifyId;
	}

	public void setCertifyId(String certifyId) {
		this.certifyId = certifyId;
	}


	
}
