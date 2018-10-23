package com.calf.module.order.vo;

import java.math.BigDecimal;

public class SkillPriceLevelVO {
	//技能Id
	private String skillItemId;
	//技能名称
	private String skillItemName;
	//价格等级
	private Integer priceLevel;
	//半小时价格
	private BigDecimal halfPrice;
	//一小时价格
	private BigDecimal onePrice;
	//一次价格
	private BigDecimal timePrice;
	//等级阈值
	private Integer levelThreshold;
	//价格状态
	private Integer levelStatus;
	
	public String getSkillItemName() {
		return skillItemName;
	}
	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}
	public Integer getPriceLevel() {
		return priceLevel;
	}
	public void setPriceLevel(Integer priceLevel) {
		this.priceLevel = priceLevel;
	}
	public BigDecimal getHalfPrice() {
		return halfPrice;
	}
	public void setHalfPrice(BigDecimal halfPrice) {
		this.halfPrice = halfPrice;
	}
	public BigDecimal getOnePrice() {
		return onePrice;
	}
	public void setOnePrice(BigDecimal onePrice) {
		this.onePrice = onePrice;
	}
	public BigDecimal getTimePrice() {
		return timePrice;
	}
	public void setTimePrice(BigDecimal timePrice) {
		this.timePrice = timePrice;
	}
	public Integer getLevelThreshold() {
		return levelThreshold;
	}
	public void setLevelThreshold(Integer levelThreshold) {
		this.levelThreshold = levelThreshold;
	}
	public Integer getLevelStatus() {
		return levelStatus;
	}
	public void setLevelStatus(Integer levelStatus) {
		this.levelStatus = levelStatus;
	}
	public String getSkillItemId() {
		return skillItemId;
	}
	public void setSkillItemId(String skillItemId) {
		this.skillItemId = skillItemId;
	}
	
	
}
