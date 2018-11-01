package com.honglu.quickcall.activity.service.service;

import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;

import java.util.List;
import java.util.Map;

public interface CouponDubboService {
	Coupon getCouponByCustomerCouponId(int id);
	
	int updateCustomerCouponById(CustomerCoupon customerCoupon);

    CustomerCoupon queryCustomerCouponByCustomerIdAndOrderId(Long customerId, Long orderId);

	int cancelUpdateCustomerCoupon(Integer id);

	/**
	 * 根据订单ID查询客户优惠券
	 * @return
	 */
	Map<String,String> getCustomerCouponByOrderId(Long orderId);

	String getCouponIdBySkillItemId(List<String> skillItemIdList);

	int getShowTip(String couponId,String customerId);

}
