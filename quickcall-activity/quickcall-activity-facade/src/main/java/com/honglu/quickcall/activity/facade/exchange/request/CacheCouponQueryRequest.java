package com.honglu.quickcall.activity.facade.exchange.request;

import java.util.List;

public class CacheCouponQueryRequest {
	private String customerId;
	private List<String> couponId;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<String> getCouponId() {
		return couponId;
	}
	public void setCouponId(List<String> couponId) {
		this.couponId = couponId;
	}
}
