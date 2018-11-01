package com.honglu.quickcall.activity.service.business;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.activity.facade.business.CouponDubboBusiness;
import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.service.service.CouponDubboService;

@Service("Activity.CouponDubboBusiness")
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

	/**
	 * 查询用户此订单是否使用优惠券
	 * @param customerId
	 * @param orderId
	 * @return
	 */
	@Override
	public CustomerCoupon queryCustomerCouponByCustomerIdAndOrderId(Long customerId, Long orderId){
		return couponDubboService.queryCustomerCouponByCustomerIdAndOrderId(customerId, orderId);
	}

	/**
	 * 退回优惠券
	 * @param id
	 * @return
	 */
	@Override
	public int cancelUpdateCustomerCoupon(Integer id){
		return couponDubboService.cancelUpdateCustomerCoupon(id);
	}
	
}
