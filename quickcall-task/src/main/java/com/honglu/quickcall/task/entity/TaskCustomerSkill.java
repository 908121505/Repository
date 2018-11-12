package com.honglu.quickcall.task.entity;

import java.util.Date;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：接单设置相关
 * @Package: com.honglu.quickcall.task.entity 
 * @author: chenliuguang   
 * @date: 2018年10月23日 下午5:24:48
 */
public class TaskCustomerSkill {
   
    private  Long  customerSkillId;
    
    private String  startTimeStr  ;
    
    private String  endTimeStr;

    private Date  appointStartTime;
    
    private Date  appointEndTime;

	public Long getCustomerSkillId() {
		return customerSkillId;
	}

	public void setCustomerSkillId(Long customerSkillId) {
		this.customerSkillId = customerSkillId;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Date getAppointStartTime() {
		return appointStartTime;
	}

	public void setAppointStartTime(Date appointStartTime) {
		this.appointStartTime = appointStartTime;
	}

	public Date getAppointEndTime() {
		return appointEndTime;
	}

	public void setAppointEndTime(Date appointEndTime) {
		this.appointEndTime = appointEndTime;
	}

	@Override
	public String toString() {
		return "TaskCustomerSkill [customerSkillId=" + customerSkillId + ", startTimeStr=" + startTimeStr
				+ ", endTimeStr=" + endTimeStr + ", appointStartTime=" + appointStartTime + ", appointEndTime="
				+ appointEndTime + "]";
	}

}