package com.honglu.quickcall.task.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface TaskOrderMapper {
   
	/**
	 * 获取特定状态符合条件的记录
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	void  waittingReceiveOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);
	
	/**
	 * 获取特定状态符合条件的记录
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	void  startOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);

	/**
	 * 12小时未响应超时
	 * @param currTime
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	void updateOrderStatusAfter12Hour(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);

}