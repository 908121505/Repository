package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;


/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：用户技能信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月22日 上午10:55:17 
 */
public class OrderSkillItemVO implements  Serializable{


	private static final long serialVersionUID = -5599818621776972036L;
	/**技能ICON*/
	private String  skillIcon;
	/**用户技能ID*/
	private Long  userSkillItemId;
	/**技能名称*/
	private String  skillItemName;
	/**技能单价*/
	private BigDecimal  price ;
	/**折扣价格*/
	private BigDecimal  discontPrice;
	/**服务单位（小时/半小时/次）*/
	private String  serviceUnit;
	/**技能类型1：按次收费 有预约时间，2：按时间段收费 没有预约时间*/
	private Integer  skillType;
	/**是否展示活动优惠券提示,0=不展示，1=展示*/
	private Integer showTip;
 	/**券的价值*/
	private Integer couponPrice;
	/** 显示的提示文案 */
	//private String tipHtml;

	/**券的ID*/
	private String couponId;
	/**券的名称*/
	private String couponName;
	/**券的抵扣价值*/
	private Integer couponDeductPrice;
	/**customer_coupon的ID*/
	private String customerCouponId;

	public Long getUserSkillItemId() {
		return userSkillItemId;
	}
	public void setUserSkillItemId(Long userSkillItemId) {
		this.userSkillItemId = userSkillItemId;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getServiceUnit() {
		return serviceUnit;
	}
	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}
	public String getSkillItemName() {
		return skillItemName;
	}
	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}
	public String getSkillIcon() {
		return skillIcon;
	}
	public void setSkillIcon(String skillIcon) {
		this.skillIcon = skillIcon;
	}
	public BigDecimal getDiscontPrice() {
		return discontPrice;
	}
	public void setDiscontPrice(BigDecimal discontPrice) {
		this.discontPrice = discontPrice;
	}
	public Integer getSkillType() {
		return skillType;
	}
	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}

	public Integer getShowTip() {
		return showTip;
	}

	public void setShowTip(Integer showTip) {
		this.showTip = showTip;
	}

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

	/*public String getTipHtml() {
		return tipHtml;
	}

	public void setTipHtml(String tipHtml) {
		this.tipHtml = tipHtml;
	}*/

	public Integer getCouponDeductPrice() {
		return couponDeductPrice;
	}

	public void setCouponDeductPrice(Integer couponDeductPrice) {
		this.couponDeductPrice = couponDeductPrice;
	}

	public String getCustomerCouponId() {
		return customerCouponId;
	}

	public void setCustomerCouponId(String customerCouponId) {
		this.customerCouponId = customerCouponId;
	}
}
