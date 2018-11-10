package com.honglu.quickcall.task.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.task.entity.TaskOrder;

@Mapper
public interface TaskOrderMapper {
   
	/**
	 * 接单超时
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	void  waittingReceiveOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("modifyTime")Date  modifyTime);
	/**
	 * 接单超时查询
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	List<TaskOrder>  queryReceiveOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("queryEndTime")Date  queryEndTime);
	
	/**
	 * 大V未发起立即服务超时
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	void  startOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("modifyTime")Date  modifyTime);
	/**
	 * 大V未发起立即服务超时  查询
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	List<TaskOrder>  queryStartOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("queryEndTime")Date  queryEndTime);
	
	/**
	 * 用户5分钟未响应声优的立即服务申请
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	List<TaskOrder>  queryStartOrderOverTimeCust(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("queryEndTime")Date  queryEndTime);

	/**
	 *用户未接立即服务超时超时
	 * @param currTime
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	void updateOrderStatusAfter12HourCust(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("modifyTime")Date  modifyTime);
	/**
	 *用户12小时未响应大V结束服务
	 * @param currTime
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	List<TaskOrder>  queryOrderStatusAfter12HourCust(@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("queryEndTime")Date  queryEndTime,@Param("queryStatusExt")Integer  queryStatusExt);
	
	
	
	
	
	
	
	/**
	 *双方都没有主动结束：12小时未响应超时
	 * @param currTime
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	void updateOrderStatusAfter12HourBoth(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("queryEndTime")Date  queryEndTime,@Param("modifyTime")Date  modifyTime);
	/**
	 *双方都没有主动结束：12小时未响应超时查询
	 * @param currTime
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	List<TaskOrder>  queryOrderStatusAfter12HourBoth(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("queryEndTime")Date  queryEndTime);

	
	
	
	
	/**
	 * 叫醒自动转为进行中
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	void appointOrderGoing(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("queryEndTime")Date  queryEndTime,@Param("modifyTime")Date  modifyTime);
	/**
	 * 叫醒自动转为进行中
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	List<TaskOrder> queryAppointOrderGoing(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("queryEndTime")Date  queryEndTime);

	/**
	 * 叫醒自动结束
	 */
	void appointOrderFinish(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType,@Param("modifyTime")Date  modifyTime);
	/**
	 * 叫醒自动结束--查询
	 */
	List<TaskOrder>  queryAppointOrderFinish(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);




	/**
	 * 根据订单ID列表更新订单状态
	 * @param updateStatus
	 * @param statusList
	 */
	void  updateOrderStatus(@Param("updateStatus")Integer  updateStatus,@Param("list")List<Long>  orderIdList,@Param("cancelTime")Date  cancelTime,@Param("couponFlag")Integer  couponFlag,@Param("modifyTime")Date  modifyTime);
	/**
	 * 根据订单ID列表更新订单状态
	 * @param updateStatus
	 * @param statusList
	 */
	void  updateOrderStatusForFinish(@Param("updateStatus")Integer  updateStatus,@Param("list")List<Long>  orderIdList,@Param("finishTime")Date  finishTime,@Param("modifyTime")Date  modifyTime);
	
	
	/**
	 * 大V服务时间内发起结束服务，到预期结束时间，释放大V
	 * @param currTime
	 * @param queryStatus
	 * @return
	 */
	List<TaskOrder> queryReleaseDaV(@Param("currTime")Date  currTime,@Param("queryStatus")Integer queryStatus);

}