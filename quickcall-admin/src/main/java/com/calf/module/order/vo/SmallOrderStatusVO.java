package com.calf.module.order.vo;

/**
 * 
 * 功能描述：小类订单状态
 * @Package: com.calf.module.order.vo 
 * @author: xiangping
 * @date: 2018年10月26日 下午4:36:37
 */
public class SmallOrderStatusVO {
	private String value;
	private String desc;
	private boolean isShow = false;

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

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean show) {
		isShow = show;
	}
}