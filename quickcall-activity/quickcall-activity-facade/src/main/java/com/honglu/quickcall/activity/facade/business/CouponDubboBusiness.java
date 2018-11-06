package com.honglu.quickcall.activity.facade.business;

import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CouponDubboBusiness {
	//根据用户优惠券ID查到优惠券信息
	Coupon getCouponByCustomerCouponId(int id);
	//根据ID来修改优惠券状态和订单
	int updateCustomerCouponById(CustomerCoupon customerCoupon);

	/**
	 * 根据订单ID查询客户优惠券
	 * @return
	 */
	Map<String,String> getCustomerCouponByOrderId(Long orderId);

	/**
	 * 查询是否显示活动优惠券提示
	 * @param skillItemId
	 * @param customerId
	 * @return
	 */
	int getShowTipForActivity(String skillItemId,String customerId);



	/**
	 * 查询用户此订单是否使用优惠券
	 * @param customerId
	 * @param orderId
	 * @return
	 */
	CustomerCoupon queryCustomerCouponByCustomerIdAndOrderId(Long customerId, Long orderId);

	/**
	 * 退回优惠券
	 * @param id
	 * @return
	 */
	int cancelUpdateCustomerCoupon(Integer id);


}
