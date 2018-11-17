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

	
	private static final long serialVersionUID = 6871393819620091576L;
	private Long  orderId;
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
	
	private Long  countDownSeconds;
	
	
	
	public Long getCountDownSeconds() {
		return countDownSeconds;
	}
	public void setCountDownSeconds(Long countDownSeconds) {
		this.countDownSeconds = countDownSeconds;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
