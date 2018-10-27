package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单详情IM使用
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月24日 下午1:17:55 
 */
public class OrderDetailForIMVO implements  Serializable{

	
	
	private static final long serialVersionUID = 6828868505604634647L;

	/**返回状态1：订单不存在，可以下单    2：订单不存在  大V在忙    3：订单存在   4：大V不可下单*/
	private Integer  retCode;
	
	/**主播ID*/
	private Long  serviceId;
	/**下单人iD*/
	private Long  customerId;
	
	
	/**返回订单信息*/
	private OrderIMVO  orderIMVO ;
	/**用户可下单时展示下单信息*/
	private CustomerSkillIMVO  skillIMVO;
	public Integer getRetCode() {
		return retCode;
	}
	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}
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
	public OrderIMVO getOrderIMVO() {
		return orderIMVO;
	}
	public void setOrderIMVO(OrderIMVO orderIMVO) {
		this.orderIMVO = orderIMVO;
	}
	public CustomerSkillIMVO getSkillIMVO() {
		return skillIMVO;
	}
	public void setSkillIMVO(CustomerSkillIMVO skillIMVO) {
		this.skillIMVO = skillIMVO;
	}  
	
	
}
