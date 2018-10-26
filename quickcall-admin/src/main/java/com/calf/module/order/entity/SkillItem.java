package com.calf.module.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SkillItem {
	private String id;

	private String skillItemName;

	private String skillDescribe;

	private Integer sort;

	private Integer skillStatus;

	private Date createTime;

	private Date modifyTime;

	private String createMan;

	private String modifyMan;

	private String remark;

	private String lockIcon;

	private String unlockIcon;

	private String backColor;

	private String fontColor;

	private Integer skillType;

	private String homeBlackColor;

	public String getHomeBlackColor() {
		return homeBlackColor;
	}

	public void setHomeBlackColor(String homeBlackColor) {
		this.homeBlackColor = homeBlackColor;
	}

	public Integer getSkillType() {
		return skillType;
	}

	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getLockIcon() {
		return lockIcon;
	}

	public void setLockIcon(String lockIcon) {
		this.lockIcon = lockIcon;
	}

	public String getUnlockIcon() {
		return unlockIcon;
	}

	public void setUnlockIcon(String unlockIcon) {
		this.unlockIcon = unlockIcon;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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