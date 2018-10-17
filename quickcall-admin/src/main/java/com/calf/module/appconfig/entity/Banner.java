package com.calf.module.appconfig.entity;

import java.util.Date;

/**
 * Banner 表实体对象
 *
 * @author duanjun
 * @date 2018-09-21 16:12
 **/
public class Banner {

	/** 系统参数编号 **/
	private Integer bannerId;

	/** banner类型：1-首页顶部banner；2-首页中部banner；3-分类页banner；······ **/
	private Byte bannerType;

	/** 标题/说明 **/
	private String title;

	/** 图片 **/
	private String imageUrl;

	/** 点击跳转类型：0-不跳转；1-HTML页面；2-个人主页；3-分类页；····· **/
	private Byte clickType;

	/** 跳转URL连接 **/
	private String url;

	/** 排序字段 **/
	private Short sort;

	/** 状态 1：开启 0：关闭 **/
	private Byte bannerStatus;

	/** 开始时间 **/
	private String startTime;

	/** 结束时间 **/
	private String endTime;

	/**
	 * 设备类型 0-所有,1-ios,2-andriod
	 */
	private Byte deviceType;

	/**
	 * app版本
	 */
	private String appVersion;

	/**
	 * 版本号匹配规则 0-所有,1-大于,2-小于,3-等于,4-大于等于,5-小于等于
	 */
	private Integer appVersionRule;


	/** 备注 **/
	private String remark;

	/** 创建时间 **/
	private Date createTime;

	/** 修改时间 **/
	private Date modifyTime;

	/** 创建人 **/
	private String createMan;

	/** 修改人 **/
	private String modifyMan;

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public Byte getBannerType() {
		return bannerType;
	}

	public void setBannerType(Byte bannerType) {
		this.bannerType = bannerType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Byte getClickType() {
		return clickType;
	}

	public void setClickType(Byte clickType) {
		this.clickType = clickType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	public Byte getBannerStatus() {
		return bannerStatus;
	}

	public void setBannerStatus(Byte bannerStatus) {
		this.bannerStatus = bannerStatus;
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

	public Byte getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Byte deviceType) {
		this.deviceType = deviceType;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getAppVersionRule() {
		return appVersionRule;
	}

	public void setAppVersionRule(Integer appVersionRule) {
		this.appVersionRule = appVersionRule;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}
}
