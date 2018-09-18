package com.calf.module.system.entity;

import java.io.Serializable;

/**
 * 显示首页点击量实体
 * @author guixin
 *
 */
public class QueryHomeClick implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int clickNum;
	private int days;
	
	public int getClickNum() {
		return clickNum;
	}
	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	
	
}
