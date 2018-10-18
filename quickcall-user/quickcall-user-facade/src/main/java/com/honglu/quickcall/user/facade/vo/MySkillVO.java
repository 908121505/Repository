package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;

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

	
	
}
