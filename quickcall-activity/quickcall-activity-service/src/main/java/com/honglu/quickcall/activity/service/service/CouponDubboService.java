package com.honglu.quickcall.activity.service.service;

import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.facade.vo.CouponOrderVo;

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
	CustomerCoupon getCountByCustomerIdAndCouponId(Map<String,String> map);

	/**
	 * 下单页数据展示优惠券接口用
	 * @param skillItemId 技能ID
	 * @param customerId 客户ID
	 */
	CouponOrderVo showActivityCouponForOrder(String skillItemId, String customerId);

	/**
	 * 获取可抵扣的优惠券
	 * @param skillItemId 技能ID
	 * @param customerId 客户ID
	 */
	CouponOrderVo getDeductCoupon(String skillItemId, String customerId);

	/**
	 * 下单获取券接口用
	 * @param customerCoupo 客户券
	 * @return0=不成功，1=成功
	 *
	 */
	int insertCustomerCoupon(CustomerCoupon customerCoupo);

	/**
	 * 发送消息
	 * @param couponId
	 * @param customerId
	 */
	void sendActivityMessage(String couponId, String customerId);

}
