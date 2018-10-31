package com.honglu.quickcall.activity.facade.business;

import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;

public interface CouponDubboBusiness {
	//根据用户优惠券ID查到优惠券信息
	Coupon getCouponByCustomerCouponId(int id);
	//根据ID来修改优惠券状态和订单
	int updateCustomerCouponById(CustomerCoupon customerCoupon);
}
