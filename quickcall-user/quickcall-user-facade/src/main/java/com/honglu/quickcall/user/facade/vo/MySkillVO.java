package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 我的技能输出
 * @author zhaozheyi
 *
 */
public class MySkillVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3942927476321556314L;

	// 技能名字
	private String name;
	// 技能图片
	private String imageUrl;
	// 技能状态
	private Integer auditStatus = 0;
	// 技能编号
	private Long skillId;
	// 技能录制声音url
	private String skillVoiceUrl;
	// 技能录制声音时间
	private Integer skillVoiceTime;
	// 技能支持申请状态
	private Integer skillStatus;
	//技能背景颜色
	private String backColor;
	//技能价格
	private BigDecimal price;
	//技能单位
	private String unit;
	//技能字体颜色
	private String skillFontColor;
	//技能声量
	private String skillVolume;

	

	public MySkillVO() {
		super();
	}

	public MySkillVO(String name, String imageUrl, Integer auditStatus, Long skillId, Integer skillStatus) {
		this();
		this.name = name;
		this.imageUrl = imageUrl;
		this.auditStatus = auditStatus;
		this.skillId = skillId;
		this.skillStatus = skillStatus;
	}

	public MySkillVO(String name, String imageUrl, Integer auditStatus, Long skillId, String skillVoiceUrl,
			Integer skillVoiceTime, Integer skillStatus) {
		this(name, imageUrl, auditStatus, skillId, skillStatus);
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

	public Integer getSkillVoiceTime() {
		return skillVoiceTime;
	}

	public void setSkillVoiceTime(Integer skillVoiceTime) {
		this.skillVoiceTime = skillVoiceTime;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public String getSkillFontColor() {
		return skillFontColor;
	}

	public void setSkillFontColor(String skillFontColor) {
		this.skillFontColor = skillFontColor;
	}

	public String getSkillVolume() {
		return skillVolume;
	}

	public void setSkillVolume(String skillVolume) {
		this.skillVolume = skillVolume;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "MySkillVO [name=" + name + ", imageUrl=" + imageUrl + ", auditStatus=" + auditStatus + ", skillId="
				+ skillId + ", skillVoiceUrl=" + skillVoiceUrl + ", skillVoiceTime=" + skillVoiceTime + ", skillStatus="
				+ skillStatus + ", backColor=" + backColor + ", price=" + price + ", unit=" + unit + ", skillFontColor="
				+ skillFontColor + ", skillVolume=" + skillVolume + "]";
	}
}
