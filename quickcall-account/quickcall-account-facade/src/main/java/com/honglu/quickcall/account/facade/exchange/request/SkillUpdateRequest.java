package com.honglu.quickcall.account.facade.exchange.request;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.account.facade.vo.CustomerSkillUpdateVO;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;


/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能更新入参
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午2:22:07 
 */
public class SkillUpdateRequest extends AbstractRequest {
	
	
	private static final long serialVersionUID = 7188782708444616090L;
	private List<CustomerSkillUpdateVO>   customerSkillList;
    /**服务结束时间*/
    private String endServiceTime;
    /**每周数据*/
    private  HashMap<String, Integer>  weekDataMap; 
    
	
	
	public String getEndServiceTime() {
		return endServiceTime;
	}
	public void setEndServiceTime(String endServiceTime) {
		this.endServiceTime = endServiceTime;
	}
	public HashMap<String, Integer> getWeekDataMap() {
		return weekDataMap;
	}
	public void setWeekDataMap(HashMap<String, Integer> weekDataMap) {
		this.weekDataMap = weekDataMap;
	}
	public List<CustomerSkillUpdateVO> getCustomerSkillList() {
		return customerSkillList;
	}
	public void setCustomerSkillList(List<CustomerSkillUpdateVO> customerSkillList) {
		this.customerSkillList = customerSkillList;
	}
	
	




	@Override
	public String getBizCode() {
		return OrderRequestType.UPDATE_SKILL_INFO;
	}
	@Override
	public String toString() {
		return "SkillUpdateRequest [customerSkillList=" + customerSkillList + ", endServiceTime=" + endServiceTime
				+ ", weekDataMap=" + weekDataMap + "]";
	}
	
	
	



}
