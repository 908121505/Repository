package com.honglu.quickcall.account.facade.vo;

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
	
	private static final long serialVersionUID = -4146002273762911512L;
	/**客户Id*/
	private Long  customerId;
	/**产品ID*/
	private Long  productId; 
	/**运营标签URL*/
	private String  bussTagUrl;
	/**品类标签URL*/
	private String  categoryTagUrl;
	/**主播昵称*/
	private String nickName ;
    /**主播性别：性别(0=女,1=男)*/
    private Integer  sex;
    /**年龄*/
    private Integer  age;
    /**单价*/
    private BigDecimal   price;
    /**服务单位名称*/
    private String unitName;
    /**主播封面URL*/
    private String  coverUrl;
    
    
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	
}
