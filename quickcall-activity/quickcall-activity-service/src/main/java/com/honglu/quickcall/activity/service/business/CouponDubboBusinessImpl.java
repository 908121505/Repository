package com.honglu.quickcall.activity.service.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.honglu.quickcall.activity.facade.business.CouponDubboBusiness;
import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.service.dao.CouponMapper;
import com.honglu.quickcall.activity.service.dao.CustomerCouponMapper;

public class CouponDubboBusinessImpl implements CouponDubboBusiness{
	
	@Autowired
	private CustomerCouponMapper customerCouponMapper;
	@Autowired
	private CouponMapper couponMapper;
	
	@Override
	public Coupon getCouponByCustomerCouponId(int id) {
		CustomerCoupon customerCoupon = customerCouponMapper.selectByPrimaryKey(id);
		Coupon coupon = couponMapper.selectByPrimaryKey(customerCoupon.getCouponId());
		return coupon;
	}

	@Override
	public int updateCustomerCouponById(CustomerCoupon customerCoupon) {
		return customerCouponMapper.updateByPrimaryKeySelective(customerCoupon);
	}

	
}
