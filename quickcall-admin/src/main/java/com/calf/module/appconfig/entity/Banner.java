package com.calf.module.appconfig.entity;

/**
 * 标题bean
 * 
 * @author cx
 * @date Thu Mar 09 15:09:43 CST 2017
 **/
public class Banner {

	/** 系统参数编号 **/
	private String bannerId;

	/** 标题/说明 **/
	private String title;

	/** 图片 **/
	private String image;

	/** 跳转URL连接 **/
	private String url;

	/** banner类型 **/
	private String bannerType;

	/** 开始时间 **/
	private String startTime;

	/** 结束时间 **/
	private String endTime;

	/**
	 * 设备类型 0-所有,1-ios,2-andriod
	 */
	private Integer deviceType;

	/**
	 * app版本
	 */
	private String appVersion;

	/**
	 * 版本号匹配规则 0-所有,1-大于,2-小于,3-等于,4-大于等于,5-小于等于
	 */
	private Integer appVersionRule;

	/** 状态 1：开启 0：关闭 **/
	private Integer state;

	/** 排序字段 **/
	private Integer sort;

	/** 备注 **/
	private String remark;

	/** 创建时间 **/
	private String createTime;

	/** 创建人 **/
	private String createMan;

	/** 修改时间 **/
	private String modifyTime;

	/** 修改人 **/
	private String modifyMan;

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
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

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}
}
