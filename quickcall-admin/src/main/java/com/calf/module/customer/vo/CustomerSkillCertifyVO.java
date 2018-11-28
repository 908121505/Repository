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
	private String skillVoiceUrl;
	private String skillVoiceTime;
	private String skillVoiceUrlTmp;
	private String skillVoiceTimeTmp;
	private String isAudited;

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


	public String getCertifyId() {
		return certifyId;
	}

	public void setCertifyId(String certifyId) {
		this.certifyId = certifyId;
	}

	public String getSkillVoiceUrl() {
		return skillVoiceUrl;
	}

	public void setSkillVoiceUrl(String skillVoiceUrl) {
		this.skillVoiceUrl = skillVoiceUrl;
	}

	public String getSkillVoiceTime() {
		return skillVoiceTime;
	}

	public void setSkillVoiceTime(String skillVoiceTime) {
		this.skillVoiceTime = skillVoiceTime;
	}

	/**
	 * @return the isAudited
	 */
	public String getIsAudited() {
		return isAudited;
	}

	/**
	 * @param isAudited the isAudited to set
	 */
	public void setIsAudited(String isAudited) {
		this.isAudited = isAudited;
	}

	@Override
	public String toString() {
		return "CustomerSkillCertifyVO [certifyId=" + certifyId + ", customerId=" + customerId + ", realName="
				+ realName + ", nickName=" + nickName + ", skillItemId=" + skillItemId + ", skillItemName="
				+ skillItemName + ", auditStatus=" + auditStatus + ", skillVoiceUrl=" + skillVoiceUrl
				+ ", skillVoiceTime=" + skillVoiceTime + ", skillVoiceUrlTmp=" + skillVoiceUrlTmp
				+ ", skillVoiceTimeTmp=" + skillVoiceTimeTmp + ", isAudited=" + isAudited + "]";
	}



	
}
