package com.calf.module.marketData.vo;

/**
 * 时长数据展示
 * @author zhaozheyi
 *
 */
public class MarketDataVO {
	/**
	 * 时间
	 */
	private String date;
	/**
	 * 渠道名
	 */
	private String appChannelName;
	/**
	 * 激活数
	 */
	private String activeNum;
	/**
	 * 注册数
	 */
	private String registerNum;
	/**
	 * 使用券数
	 */
	private String couponNum;
	/**
	 * 充值人数
	 */
	private String rechargeNum;
	/**
	 * 充值次数
	 */
	private String rechargeTime;
	/**
	 * 充值金额
	 */
	private String rechargeTotal;
	/**
	 * 下单人数
	 */
	private String orderNum;
	/**
	 * 下单次数
	 */
	private String orderTime;
	/**
	 * 下单金额总数
	 */
	private String orderTotal;
	/**
	 * 叫醒订单
	 */
	private String wakeNum;
	/**
	 * 哄睡订单
	 */
	private String sleepNum;
	/**
	 * 咨询订单
	 */
	private String consultNum;
	
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getAppChannelName() {
		return appChannelName;
	}


	public void setAppChannelName(String appChannelName) {
		this.appChannelName = appChannelName;
	}


	public String getActiveNum() {
		return activeNum;
	}


	public void setActiveNum(String activeNum) {
		this.activeNum = activeNum;
	}


	public String getRegisterNum() {
		return registerNum;
	}


	public void setRegisterNum(String registerNum) {
		this.registerNum = registerNum;
	}


	public String getCouponNum() {
		return couponNum;
	}


	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}


	public String getRechargeNum() {
		return rechargeNum;
	}


	public void setRechargeNum(String rechargeNum) {
		this.rechargeNum = rechargeNum;
	}


	public String getRechargeTime() {
		return rechargeTime;
	}


	public void setRechargeTime(String rechargeTime) {
		this.rechargeTime = rechargeTime;
	}


	public String getRechargeTotal() {
		return rechargeTotal;
	}


	public void setRechargeTotal(String rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}


	public String getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}


	public String getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}


	public String getOrderTotal() {
		return orderTotal;
	}


	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}


	public String getWakeNum() {
		return wakeNum;
	}


	public void setWakeNum(String wakeNum) {
		this.wakeNum = wakeNum;
	}


	public String getSleepNum() {
		return sleepNum;
	}


	public void setSleepNum(String sleepNum) {
		this.sleepNum = sleepNum;
	}


	public String getConsultNum() {
		return consultNum;
	}


	public void setConsultNum(String consultNum) {
		this.consultNum = consultNum;
	}


	@Override
	public String toString() {
		return "MarketDataVO [date=" + date + ", appChannelName=" + appChannelName + ", activeNum=" + activeNum
				+ ", registerNum=" + registerNum + ", couponNum=" + couponNum + ", rechargeNum=" + rechargeNum
				+ ", rechargeTime=" + rechargeTime + ", rechargeTotal=" + rechargeTotal + ", orderNum=" + orderNum
				+ ", orderTime=" + orderTime + ", orderTotal=" + orderTotal + ", wakeNum=" + wakeNum + ", sleepNum="
				+ sleepNum + ", consultNum=" + consultNum + "]";
	}
	
}
