package com.honglu.quickcall.activity.service.service;

import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponQueryRequest;
import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponReceiveRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 活动优惠券查询接口
 *
 * @author wq
 * @date 2018-10-30
 */
public interface ActivityCouponService {

    /**
     * 活动优惠券查询
     *
     * @param request
     * @return
     */
    CommonResponse queryActivityCoupon(ActivityCouponQueryRequest request);

    /**
     * 领券查询接口
     * @param request
     * @return
     */
    CommonResponse receiveCoupon(ActivityCouponReceiveRequest request);

}
