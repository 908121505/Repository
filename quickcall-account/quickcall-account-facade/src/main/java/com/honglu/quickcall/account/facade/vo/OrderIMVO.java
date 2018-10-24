package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单详情IM使用
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月24日 下午1:17:55 
 */
public class OrderIMVO implements  Serializable{

	private static final long serialVersionUID = -4879817505879509695L;
	/**主播ID*/
	private Long  serviceId;
	/**下单人iD*/
	private Long  customerId;
	/**产品价格*/
	private BigDecimal  servicePrice;
	/**产品单位*/
	private String  serviceUnit;
	/**技能名称*/
	private String skillItemName ;
	/**技能ICON*/
	private String  icon;
	/**订单状态*/
	private Integer  orderStatus;
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}
	public String getServiceUnit() {
		return serviceUnit;
	}
	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}
	public String getSkillItemName() {
		return skillItemName;
	}
	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
	
	
	
}
