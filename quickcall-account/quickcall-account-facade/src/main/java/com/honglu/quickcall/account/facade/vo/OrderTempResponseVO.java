package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：请求参数
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月23日 下午2:46:34 
 */
public class OrderTempResponseVO implements  Serializable{



	private static final long serialVersionUID = 2884973091002195547L;
	/**大V状态*/
	private int   orderStatus;
	/**倒计时*/
	private Long  countDownSeconds;
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Long getCountDownSeconds() {
		return countDownSeconds;
	}
	public void setCountDownSeconds(Long countDownSeconds) {
		this.countDownSeconds = countDownSeconds;
	}
	
}
