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

	
	
	private static final long serialVersionUID = 3332383859067578331L;
	/**返回状态1：订单不存在，可以下单    2：订单不存在  大V在忙    3：订单存在*/
	private Integer  retCode;
	/**返回订单信息*/
	private OrderIMVO  orderIMVO ;
	
	
	
	
	public Integer getRetCode() {
		return retCode;
	}
	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}
	public OrderIMVO getOrderIMVO() {
		return orderIMVO;
	}
	public void setOrderIMVO(OrderIMVO orderIMVO) {
		this.orderIMVO = orderIMVO;
	}
	
	
	
	
}
