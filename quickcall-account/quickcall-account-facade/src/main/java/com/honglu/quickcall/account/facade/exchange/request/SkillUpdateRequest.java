package com.honglu.quickcall.account.facade.exchange.request;

import java.util.List;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.account.facade.vo.CustomerSkillRequestVO;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能更新入参
 * 
 * @Package: com.honglu.quickcall.account.facade.exchange.request
 * @author: chenliuguang
 * @date: 2018年9月23日 下午2:22:07
 */
public class SkillUpdateRequest extends AbstractRequest {

	private static final long serialVersionUID = 3434120973694242155L;
	private List<CustomerSkillRequestVO> customerSkillList;
	private String endServiceTimeStr;
	private int receiveStatus;
	private int sunday;
	private int saturday;
	private int tuesday;
	private int wednesday;
	private int thursday;
	private int friday;
	private int monday;

	public List<CustomerSkillRequestVO> getCustomerSkillList() {
		return customerSkillList;
	}

	public void setCustomerSkillList(List<CustomerSkillRequestVO> customerSkillList) {
		this.customerSkillList = customerSkillList;
	}

	public void setEndServiceTimeStr(String endServiceTimeStr) {
		this.endServiceTimeStr = endServiceTimeStr;
	}

	public String getEndServiceTimeStr() {
		return endServiceTimeStr;
	}

	public void setReceiveStatus(int receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public int getReceiveStatus() {
		return receiveStatus;
	}

	public void setSunday(int sunday) {
		this.sunday = sunday;
	}

	public int getSunday() {
		return sunday;
	}

	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}

	public int getSaturday() {
		return saturday;
	}

	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}

	public int getTuesday() {
		return tuesday;
	}

	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}

	public int getWednesday() {
		return wednesday;
	}

	public void setThursday(int thursday) {
		this.thursday = thursday;
	}

	public int getThursday() {
		return thursday;
	}

	public void setFriday(int friday) {
		this.friday = friday;
	}

	public int getFriday() {
		return friday;
	}

	public void setMonday(int monday) {
		this.monday = monday;
	}

	public int getMonday() {
		return monday;
	}

	@Override
	public String getBizCode() {
		return OrderRequestType.UPDATE_SKILL_INFO;
	}


}
