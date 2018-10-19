package com.honglu.quickcall.account.facade.vo;

import java.util.Date;
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
    /**服务结束时间*/
    private Date endServiceTime;
    /**每周数据*/
    private  HashMap<String, Integer>  weekDataMap; 
    /**接单开关1：开启  0 ：关闭*/
    private Integer  receiveStatus;
    
	public Integer getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
    
	
	public Date getEndServiceTime() {
		return endServiceTime;
	}
	public void setEndServiceTime(Date endServiceTime) {
		this.endServiceTime = endServiceTime;
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