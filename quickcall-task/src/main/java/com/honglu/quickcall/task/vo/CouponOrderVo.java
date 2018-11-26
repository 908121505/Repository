package com.honglu.quickcall.task.vo;

/**
 * 券订单VO
 *
 * @author wq
 * @date 2018-11-08
 **/
public class CouponOrderVo {

	private Integer showTip;//0=不展示，1=展示

	private Integer couponPrice;//券的价值
	/** 显示的提示文案 */
	//private String tipHtml;

	private String couponId;//券的ID
	private String couponName;//券的名称
	private Integer couponDeductPrice;//券的抵扣价值

	private Integer customerCouponId;//customer_coupon的ID

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

	public Integer getCustomerCouponId() {
		return customerCouponId;
	}

	public void setCustomerCouponId(Integer customerCouponId) {
		this.customerCouponId = customerCouponId;
	}
}
