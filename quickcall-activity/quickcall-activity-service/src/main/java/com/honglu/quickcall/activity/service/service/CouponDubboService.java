package com.honglu.quickcall.activity.service.service;

import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;

public interface CouponDubboService {
	Coupon getCouponByCustomerCouponId(int id);
	
	int updateCustomerCouponById(CustomerCoupon customerCoupon);

    CustomerCoupon queryCustomerCouponByCustomerIdAndOrderId(Long customerId, Long orderId);

	int cancelUpdateCustomerCoupon(Integer id);
}
