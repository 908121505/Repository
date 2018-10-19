package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MySkillVO implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3942927476321556314L;
	
	//技能名字
	private String name;
	//技能图片
	private String imageUrl;
	//技能状态
	private Integer auditStatus = 0;
	//技能编号
	private Long skillId;
	//技能录制声音url
	private String skillVoiceUrl;
	//技能录制声音时间
	private BigDecimal skillVoiceTime;
	//技能支持申请状态
	private Integer skillStatus;
	
	public MySkillVO (String name,String imageUrl,Integer auditStatus,Long skillId,Integer skillStatus ){
		super();
		this.name = name;
		this.imageUrl = imageUrl;
		this.auditStatus = auditStatus;
		this.skillId = skillId;
		this.skillStatus = skillStatus;
	}
	
	public MySkillVO(String name,String imageUrl,Integer auditStatus,Long skillId,String skillVoiceUrl,BigDecimal skillVoiceTime,Integer skillStatus){
		this(name,imageUrl,auditStatus,skillId,skillStatus);
		this.skillVoiceUrl = skillVoiceUrl;
		this.skillVoiceTime = skillVoiceTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getSkillVoiceUrl() {
		return skillVoiceUrl;
	}

	public void setSkillVoiceUrl(String skillVoiceUrl) {
		this.skillVoiceUrl = skillVoiceUrl;
	}

	public Integer getSkillStatus() {
		return skillStatus;
	}

	public void setSkillStatus(Integer skillStatus) {
		this.skillStatus = skillStatus;
	}

	public BigDecimal getSkillVoiceTime() {
		return skillVoiceTime;
	}

	public void setSkillVoiceTime(BigDecimal skillVoiceTime) {
		this.skillVoiceTime = skillVoiceTime;
	}


	
	
}
