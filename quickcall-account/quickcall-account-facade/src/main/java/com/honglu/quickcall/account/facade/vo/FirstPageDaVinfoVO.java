package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：首页大V列表
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午3:39:33 
 */
public class FirstPageDaVinfoVO implements  Serializable{

	private static final long serialVersionUID = 1801251381604100871L;
	/**客户Id*/
	private Long  customerId;
	/**产品ID*/
	private Long  productId; 
	/**主播昵称*/
	private String nikeName ;
    /**主播性别：性别(0=女,1=男)*/
    private Integer  sex;
    /**年龄*/
    private Integer  age;
    /**单价*/
    private BigDecimal   price;
    /**服务时长*/
    private Integer  serviceTime;
    /**产品名称*/
    private String  productName;
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
	public String getNikeName() {
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
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
	public Integer getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
    
}
