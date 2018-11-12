package com.honglu.quickcall.activity.facade.exchange.request;

import com.honglu.quickcall.activity.facade.code.ActivityFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 活动优惠券查询接口请求对象
 *
 * @author wq
 * @date 2018-10-30
 */
public class ActivityCouponQueryRequest extends AbstractRequest {

    /**
     * 活动编码（起始页面定位活动）
     */
    private String activityCode;
    /**
     * 用户编号
     */
    private String customerId;

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String getBizCode() {
        return ActivityFunctionType.ACTIVITY_COUPON_QUERY;
    }
}
