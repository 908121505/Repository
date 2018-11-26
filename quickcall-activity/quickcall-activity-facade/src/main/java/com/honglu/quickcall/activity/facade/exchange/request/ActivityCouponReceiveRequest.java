package com.honglu.quickcall.activity.facade.exchange.request;

import com.honglu.quickcall.activity.facade.code.ActivityFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 领券接口请求对象
 *
 * @author wq
 * @date 2018-10-30
 */
public class ActivityCouponReceiveRequest extends AbstractRequest {
    /**
     * 券ID
     */
    private String couponId;
    /**
     * 用户编号
     */
    private String customerId;


    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String getBizCode() {
        return ActivityFunctionType.ACTIVITY_COUPON_RECEIVE;
    }
}
