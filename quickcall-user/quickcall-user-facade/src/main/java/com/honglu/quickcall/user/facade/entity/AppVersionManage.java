package com.honglu.quickcall.user.facade.entity;


/**
 * APP版本表bean
 * @author cx
 * @date Thu Mar 09 19:07:16 CST 2017
 **/
public class AppVersionManage {

	/**版本ID**/
	private String appVersionId;

	private String appType;
	
	private String appTypeName;

	/**弹窗**/
	private String popup;

	/**版本号**/
	private String versionNumber;

	/**下载地址**/
	private String downloadUrl;

	/**更新描述**/
	private String changeDesc;

	/**更新日志**/
	private String changeLog;

	/**更新属性**/
	private String changeProperties;

	/**开始更新时间**/
	private String beginTime;

	/**结束更新时间**/
	private String endTime;

	/****/
	private Integer state;

	/****/
	private Integer iosVersion;

	/****/
	private Integer androidVersion;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;

	private String tag;

	private String encryption;

	private String channel;

	public void setAppVersionId(String appVersionId){
		this.appVersionId = appVersionId;
	}

	public String getAppVersionId(){
		return this.appVersionId;
	}

	public void setAppType(String appType){
		this.appType = appType;
	}

	public String getAppType(){
		return this.appType;
	}

	public void setPopup(String popup){
		this.popup = popup;
	}

	public String getPopup(){
		return this.popup;
	}

	public void setVersionNumber(String versionNumber){
		this.versionNumber = versionNumber;
	}

	public String getVersionNumber(){
		return this.versionNumber;
	}

	public void setDownloadUrl(String downloadUrl){
		this.downloadUrl = downloadUrl;
	}

	public String getDownloadUrl(){
		return this.downloadUrl;
	}

	public void setChangeDesc(String changeDesc){
		this.changeDesc = changeDesc;
	}

	public String getChangeDesc(){
		return this.changeDesc;
	}

	public void setChangeLog(String changeLog){
		this.changeLog = changeLog;
	}
	
	
	
	public String getAppTypeName() {
		return appTypeName;
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}

	public String getChangeLog(){
		return this.changeLog;
	}

	public void setChangeProperties(String changeProperties){
		this.changeProperties = changeProperties;
	}

	public String getChangeProperties(){
		return this.changeProperties;
	}

	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}

	public String getBeginTime(){
		return this.beginTime;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	public String getEndTime(){
		return this.endTime;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

	public void setIosVersion(Integer iosVersion){
		this.iosVersion = iosVersion;
	}

	public Integer getIosVersion(){
		return this.iosVersion;
	}

	public void setAndroidVersion(Integer androidVersion){
		this.androidVersion = androidVersion;
	}

	public Integer getAndroidVersion(){
		return this.androidVersion;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getEncryption() {
		return encryption;
	}

	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
