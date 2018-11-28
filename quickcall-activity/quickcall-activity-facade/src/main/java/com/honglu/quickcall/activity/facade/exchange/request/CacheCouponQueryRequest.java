package com.honglu.quickcall.activity.facade.exchange.request;


public class CacheCouponQueryRequest {
	private String customerId;
	private String couponId;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
}
