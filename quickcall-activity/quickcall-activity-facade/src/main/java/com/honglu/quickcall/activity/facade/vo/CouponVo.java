package com.honglu.quickcall.activity.facade.vo;

/**
 * 券VO
 *
 * @author wq
 * @date 2018-10-30
 **/
public class CouponVo {
	private String couponId;//券的ID
	private String couponName;//券的名称
	private Integer couponPrice;//券的价值
	private String isUsed;//券是否使用（0=未使用，1=已使用,2=未领取）
	private String skillItemId;//品类编号
	private String skillItemName;//技能项名称

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(Integer couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
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
}
