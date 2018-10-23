package com.honglu.quickcall.task.dao;

import java.util.Date;

public interface TaskOrderMapper {
   
	/**
	 * 获取特定状态符合条件的记录
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	void  waittingReceiveOrderOverTime(Date  currTime,Date  endTime,Integer queryStatus ,Integer  updateStatus,Integer  skillT);
	
	/**
	 * 获取特定状态符合条件的记录
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	void  startOrderOverTime(Date  currTime,Date  endTime,Integer queryStatus ,Integer  updateStatus,Integer  skillT);

}