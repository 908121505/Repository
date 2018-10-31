package com.honglu.quickcall.activity.service.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.honglu.quickcall.activity.facade.business.CouponDubboBusiness;
import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.service.service.CouponDubboService;

public class CouponDubboBusinessImpl implements CouponDubboBusiness{
	
	@Autowired
	private CouponDubboService couponDubboService;
	
	@Override
	public Coupon getCouponByCustomerCouponId(int id) {
		return couponDubboService.getCouponByCustomerCouponId(id);
	}

	@Override
	public int updateCustomerCouponById(CustomerCoupon customerCoupon) {
		return couponDubboService.updateCustomerCouponById(customerCoupon);
	}

	
}
