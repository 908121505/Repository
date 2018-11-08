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

    /**
     * 判断现在时间是否有活动
     * @return
     */
    int getActivityNum();

    /**
     * 根据技能ID查询券ID
     * @return
     */
	String getCouponIdBySkillItemId(String skillItemId);

    /**
     * 根据客户ID和券ID查询用户券关系数量
     * @return
     */
	int getCountByCustomerIdAndCouponId(Map<String,String> map);

}
