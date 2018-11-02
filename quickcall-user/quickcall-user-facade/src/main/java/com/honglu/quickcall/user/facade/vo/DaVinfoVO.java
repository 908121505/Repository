package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：首页大V展示信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月18日 上午9:53:55 
 */
public class DaVinfoVO implements  Serializable{
	
	private static final long serialVersionUID = 8728649787886013092L;
	/**客户Id*/
	private Long  customerId;
	/**用户技能ID*/
	private Long  customerSkillId; 
	/**运营标签URL*/
	private String  bussTagUrl;
	/**品类标签URL*/
	private String  categoryTagUrl;
	/**品类背景颜色*/
	private String skillBackColor;
	/**主播昵称*/
	private String nickName ;
    /**主播性别：性别(0=女,1=男)*/
    private Integer  sex;
    /**年龄*/
    private Integer  age;
    /**单价*/
    private BigDecimal   price;
    /**币种名称*/
    private String  currencyName = "音符";
    /**服务单位名称*/
    private String unitName;
    /**主播封面URL*/
    private String  coverUrl;
    
    /**技能分类名称*/
    private  String  skillItemName;
    /**声音时长*/
    private  BigDecimal  voiceTime ;
    /**声音URL*/
    private  String  voiceUrl;
    
    
    
    
	public String getSkillItemName() {
		return skillItemName;
	}
	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}
	public BigDecimal getVoiceTime() {
		return voiceTime;
	}
	public void setVoiceTime(BigDecimal voiceTime) {
		this.voiceTime = voiceTime;
	}
	public String getVoiceUrl() {
		return voiceUrl;
	}
	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	

	public Long getCustomerSkillId() {
		return customerSkillId;
	}
	public void setCustomerSkillId(Long customerSkillId) {
		this.customerSkillId = customerSkillId;
	}
	public String getBussTagUrl() {
		return bussTagUrl;
	}
	public void setBussTagUrl(String bussTagUrl) {
		this.bussTagUrl = bussTagUrl;
	}
	public String getCategoryTagUrl() {
		return categoryTagUrl;
	}
	public void setCategoryTagUrl(String categoryTagUrl) {
		this.categoryTagUrl = categoryTagUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getSkillBackColor() {
		return skillBackColor;
	}
	public void setSkillBackColor(String skillBackColor) {
		this.skillBackColor = skillBackColor;
	}
	
}
