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
 * @Package: com.honglu.quickcall.account.facade.exchange.req
 * @author: chenliuguang
 * @date: 2018年9月23日 下午2:22:07
 */
public class SkillUpdateRequest extends AbstractRequest {

	
	private static final long serialVersionUID = 7279476978703182695L;
	private List<CustomerSkillRequestVO> customerSkillList;
	private String endServiceTimeStr;
	private Integer receiveStatus;
	private Integer sunday;
	private Integer saturday;
	private Integer tuesday;
	private Integer wednesday;
	private Integer thursday;
	private Integer friday;
	private Integer monday;
	private Long  customerId;

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

	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public Integer getReceiveStatus() {
		return receiveStatus;
	}

	public void setSunday(Integer sunday) {
		this.sunday = sunday;
	}

	public Integer getSunday() {
		return sunday;
	}

	public void setSaturday(Integer saturday) {
		this.saturday = saturday;
	}

	public Integer getSaturday() {
		return saturday;
	}

	public void setTuesday(Integer tuesday) {
		this.tuesday = tuesday;
	}

	public Integer getTuesday() {
		return tuesday;
	}

	public void setWednesday(Integer wednesday) {
		this.wednesday = wednesday;
	}

	public Integer getWednesday() {
		return wednesday;
	}

	public void setThursday(Integer thursday) {
		this.thursday = thursday;
	}

	public Integer getThursday() {
		return thursday;
	}

	public void setFriday(Integer friday) {
		this.friday = friday;
	}

	public Integer getFriday() {
		return friday;
	}

	public void setMonday(Integer monday) {
		this.monday = monday;
	}

	public Integer getMonday() {
		return monday;
	}
	
	

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String getBizCode() {
		return OrderRequestType.UPDATE_SKILL_INFO;
	}


}
