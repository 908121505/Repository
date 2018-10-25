package com.calf.module.order.vo;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单信息展示使用
 * @Package: com.calf.module.order.vo 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午4:36:37
 */
public class OrderStatusVO {
	private String value;
	private String desc;
	private String subset;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSubset() {
		return subset;
	}

	public void setSubset(String subset) {
		this.subset = subset;
	}
}