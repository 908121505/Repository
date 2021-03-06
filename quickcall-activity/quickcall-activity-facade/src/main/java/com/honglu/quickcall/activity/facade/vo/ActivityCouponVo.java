package com.honglu.quickcall.activity.facade.vo;


import java.util.List;

/**
 * 活动和券VO
 *
 * @author wq
 * @date 2018-10-30
 **/
public class ActivityCouponVo {

	/**
	 * 活动名
	 */
	private String activityName;
	/**
	 *活动的起始时间
	 */
	private String startTime;
	/**
	 *活动的结束时间
	 */
	private String endTime;
	/**
	 *券
	 */
	private List<CouponVo> coupons;
	/**
	 * 服务器当前时间
	 */
	private String nowTime;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<CouponVo> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponVo> coupons) {
		this.coupons = coupons;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}
}
