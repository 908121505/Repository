package com.calf.module.activity.vo;

/**
 * 活动券
 * @author wq
 * @date 2018-11-15
 */
public class ActivityCouponAdminVo {

    //活动ID
    private Long activityId;
    //活动名字
    private String activityName;
    //券ID
    private Long couponId;
    //券名字
    private String couponName;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}