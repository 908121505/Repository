package com.honglu.quickcall.activity.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.service.dao.CouponMapper;
import com.honglu.quickcall.activity.service.dao.CustomerCouponMapper;
import com.honglu.quickcall.activity.service.service.CouponDubboService;

import java.util.List;
import java.util.Map;

@Service
public class CouponDubboServiceImpl implements CouponDubboService{
	
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

	@Override
	public CustomerCoupon queryCustomerCouponByCustomerIdAndOrderId(Long customerId, Long orderId) {
		return customerCouponMapper.queryCustomerCouponByCustomerIdAndOrderId(customerId,orderId);
	}

	@Override
	public int cancelUpdateCustomerCoupon(Integer id) {
		return customerCouponMapper.cancelUpdateCustomerCoupon(id);
	}

	@Override
	public Map<String,String> getCustomerCouponByOrderId(Long orderId){
		return customerCouponMapper.getCustomerCouponByOrderId(orderId);
	}

	@Override
	public String getCouponIdBySkillItemId(List<String> statusList){
		return customerCouponMapper.getCouponIdBySkillItemId(statusList);
	}

	@Override
	public int getShowTip(String couponId ,String customerId){
		return customerCouponMapper.getShowTip(couponId,customerId);
	}

}
