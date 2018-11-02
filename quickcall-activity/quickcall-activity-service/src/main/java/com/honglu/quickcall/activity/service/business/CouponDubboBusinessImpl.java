package com.honglu.quickcall.activity.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.activity.facade.business.CouponDubboBusiness;
import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.service.service.CouponDubboService;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * 根据订单ID查询客户优惠券
	 * @return
	 */
	@Override
	public Map<String,String> getCustomerCouponByOrderId(Long orderId){
		return couponDubboService.getCustomerCouponByOrderId(orderId);
	}

    /**
     * 查询是否显示活动优惠券提示
     * @param skillItemId
     * @param customerId
     * @return
     */
    @Override
    public int getShowTipForActivity(String skillItemId, String customerId){
		int showTip = 0;//0=不展示，1=展示
		int activityNum = couponDubboService.getActivityNum();
		if(activityNum > 0){//现在存在活动
			String couponId = couponDubboService.getCouponIdBySkillItemId(skillItemId);
			if(StringUtils.isBlank(couponId)){//没查到，显示
				showTip = 1;
			}else{
				Map<String,String> map = new HashMap<String,String>();
				map.put("couponId",couponId);
				map.put("customerId",customerId);
				int num = couponDubboService.getCountByCustomerIdAndCouponId(map);
				if(num == 0){//customer_coupon没查到,显示
					showTip = 1;
				}
			}
		}
        return showTip;
    }


}
