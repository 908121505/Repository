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

	/** 序号 **/
	private Integer serialNum;

	/** 状态 1：开启 0：关闭 **/
	private Integer state;

	/** 结束时间 **/
	private String endTime;

	/** 开始时间 **/
	private String startTime;

	/** 创建时间 **/
	private String createTime;

	/** 创建人 **/
	private String createMan;

	/** 修改时间 **/
	private String modifyTime;

	/** 修改人 **/
	private String modifyMan;

	/** 备注 **/
	private String remark;
	
	/** banner类型 **/
    private String bannerType;
    /** banner背景色 **/
    private String bannerColor;
    /** 专区编号 **/
    private String zoneId;

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
	public Banner() {
		super();
	}

	public Banner(String bannerId, String title, String image, String url,
                  Integer serialNum, Integer state, String endTime, String startTime,
                  String createTime, String createMan, String modifyTime,
                  String modifyMan, String remark) {
		super();
		this.bannerId = bannerId;
		this.title = title;
		this.image = image;
		this.url = url;
		this.serialNum = serialNum;
		this.state = state;
		this.endTime = endTime;
		this.startTime = startTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
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

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerId() {
		return this.bannerId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

	public Integer getSerialNum() {
		return this.serialNum;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return this.state;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getCreateMan() {
		return this.createMan;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getModifyMan() {
		return this.modifyMan;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}
	
	public String getBannerColor() {
		return bannerColor;
	}

	public void setBannerColor(String bannerColor) {
		this.bannerColor = bannerColor;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	

}
