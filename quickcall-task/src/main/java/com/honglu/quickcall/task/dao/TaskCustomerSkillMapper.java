package com.honglu.quickcall.task.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskCustomerSkillMapper {
   
	/**
	 * 关闭技能开关
	 * @param updateStatus
	 * @param orderIdList
	 */
	void  updateCustomerSkill(@Param("updateStatus")Integer  updateStatus,@Param("list")List<Long>  skillIdList,@Param("modifyTime") Date  modifyTime);
	/**
	 * 查询需要打开开关的技能列表
	 * @param queryStatus
	 * @param queryEndTime
	 * @return
	 */
	List<Long>  queryCustomerSkill(@Param("queryStatus")Integer queryStatus ,@Param("queryEndTime")Date  queryEndTime);
	
	
	/**
	 * 根据周索引开启接单开关
	 * @param queryStatus
	 * @param recevieStatus
	 * @param updateStatus
	 * @param weekIndex
	 * @param currTimeStr
	 * @return
	 */
//	void  openReceiveByWeek(@Param("autoReceiveStatus")Integer autoReceiveStatus ,@Param("recevieStatus")Integer receiveStatus ,@Param("updateStatus")Integer  updateStatus,@Param("weekIndex")Integer  weekIndex,@Param("currTimeStr")String  currTimeStr);
	List<Long>  queryOpenReceiveByWeek(@Param("autoReceiveStatus")Integer autoReceiveStatus ,@Param("recevieStatus")Integer receiveStatus ,@Param("updateStatus")Integer  updateStatus,@Param("weekIndex")Integer  weekIndex,@Param("currTimeStr")String  currTimeStr);
	/**
	 * 根据周索引关闭接单开关
	 * @param queryStatus
	 * @param recevieStatus
	 * @param updateStatus
	 * @param weekIndex
	 * @param currTimeStr
	 * @return
	 */
//	void  closeReceiveByWeek(@Param("autoReceiveStatus")Integer autoReceiveStatus ,@Param("recevieStatus")Integer receiveStatus ,@Param("updateStatus")Integer  updateStatus,@Param("weekIndex")Integer  weekIndex,@Param("currTimeStr")String  currTimeStr);
	List<Long>    queryCloseReceiveByWeek(@Param("autoReceiveStatus")Integer autoReceiveStatus ,@Param("recevieStatus")Integer receiveStatus ,@Param("updateStatus")Integer  updateStatus,@Param("weekIndex")Integer  weekIndex,@Param("currTimeStr")String  currTimeStr);
	/**
	 * 根据当前时间开启接单开关
	 * @param queryStatus
	 * @param recevieStatus
	 * @param updateStatus
	 * @param currTime
	 */
//	void  openReceiveByCurrentTime(@Param("autoReceiveStatus")Integer autoReceiveStatus ,@Param("recevieStatus")Integer receiveStatus ,@Param("updateStatus")Integer  updateStatus,@Param("currTimeStr")Date  currTime);
	List<Long>  queryOpenReceiveByCurrentTime(@Param("autoReceiveStatus")Integer autoReceiveStatus ,@Param("recevieStatus")Integer receiveStatus ,@Param("updateStatus")Integer  updateStatus,@Param("currTimeStr")Date  currTime);
	/**
	 * 根据当前时间关闭接单开关
	 * @param queryStatus
	 * @param recevieStatus
	 * @param updateStatus
	 * @param currTime
	 */
//	void  closeReceiveByCurrentTime(@Param("autoReceiveStatus")Integer autoReceiveStatus ,@Param("recevieStatus")Integer receiveStatus ,@Param("updateStatus")Integer  updateStatus,@Param("currTimeStr")Date  currTime);
	List<Long>  queryCloseReceiveByCurrentTime(@Param("autoReceiveStatus")Integer autoReceiveStatus ,@Param("recevieStatus")Integer receiveStatus ,@Param("updateStatus")Integer  updateStatus,@Param("currTimeStr")Date  currTime);
	

}