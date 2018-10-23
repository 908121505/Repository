package com.honglu.quickcall.task.dao;

import java.util.Date;
import java.util.List;

import com.honglu.quickcall.task.entity.TaskOrder;

public interface TaskOrderMapper {
   
	/**
	 * 获取特定状态符合条件的记录
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	void  waittingReceiveOrderOverTime(Date  currTime,Date  endTime);

}