package com.honglu.quickcall.account.facade.entity;

import java.util.Date;

public class SkillItem {
	private Long id;

	private String skillItemName;

	private String skillDescribe;

	private String imageUrl;

	private Integer sort;

	private Integer skillStatus;

	private Date createTime;

	private Date modifyTime;

	private String createMan;

	private String modifyMan;

	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkillItemName() {
		return skillItemName;
	}

	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}

	public String getSkillDescribe() {
		return skillDescribe;
	}

	public void setSkillDescribe(String skillDescribe) {
		this.skillDescribe = skillDescribe == null ? null : skillDescribe.trim();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl == null ? null : imageUrl.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSkillStatus() {
		return skillStatus;
	}

	public void setSkillStatus(Integer skillStatus) {
		this.skillStatus = skillStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan == null ? null : createMan.trim();
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan == null ? null : modifyMan.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}