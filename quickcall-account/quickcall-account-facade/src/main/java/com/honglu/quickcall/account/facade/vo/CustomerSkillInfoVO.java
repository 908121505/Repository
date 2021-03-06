package com.honglu.quickcall.account.facade.vo;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能综合信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月19日 下午3:44:13
 */
public class CustomerSkillInfoVO {
	
	
	private List<CustomerSkillVO>   customerSkillList;
    /**服务结束时间字符串，格式10:00*/
    private String endServiceTimeStr;
    /**每周数据*/
    private  HashMap<String, Integer>  weekDataMap; 
    /**服务开始时间字符串，格式 10:00*/
    private String startServiceTimeStr;
    /**接单开关1：开启  0 ：关闭*/
    private Integer  receiveStatus;
    /**自动接单1：开启  0 ：关闭*/
    private Integer  autoReceiveStatus;
    
	public String getStartServiceTimeStr() {
		return startServiceTimeStr;
	}
	public void setStartServiceTimeStr(String startServiceTimeStr) {
		this.startServiceTimeStr = startServiceTimeStr;
	}
	public Integer getAutoReceiveStatus() {
		return autoReceiveStatus;
	}
	public void setAutoReceiveStatus(Integer autoReceiveStatus) {
		this.autoReceiveStatus = autoReceiveStatus;
	}
	public Integer getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public String getEndServiceTimeStr() {
		return endServiceTimeStr;
	}
	public void setEndServiceTimeStr(String endServiceTimeStr) {
		this.endServiceTimeStr = endServiceTimeStr;
	}
	public HashMap<String, Integer> getWeekDataMap() {
		return weekDataMap;
	}
	public void setWeekDataMap(HashMap<String, Integer> weekDataMap) {
		this.weekDataMap = weekDataMap;
	}
	public List<CustomerSkillVO> getCustomerSkillList() {
		return customerSkillList;
	}
	public void setCustomerSkillList(List<CustomerSkillVO> customerSkillList) {
		this.customerSkillList = customerSkillList;
	}
	
	
	
	
}