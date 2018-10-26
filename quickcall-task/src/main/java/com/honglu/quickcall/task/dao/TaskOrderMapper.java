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
	void  waittingReceiveOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);
	/**
	 * 接单超时查询
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	List<TaskOrder>  queryReceiveOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);
	
	/**
	 * 大V未发起立即服务超时
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	void  startOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);
	/**
	 * 大V未发起立即服务超时  查询
	 * @param currTime
	 * @param statusList
	 * @return
	 */
	List<TaskOrder>  queryStartOrderOverTime(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);

	/**
	 *用户未接立即服务超时超时
	 * @param currTime
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	void updateOrderStatusAfter12HourCust(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);
	/**
	 *用户未接立即服务超时超时查询
	 * @param currTime
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	List<TaskOrder>  queryOrderStatusAfter12HourCust(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);
	
	
	
	
	
	
	
	/**
	 *双方都没有主动结束：12小时未响应超时
	 * @param currTime
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	void updateOrderStatusAfter12HourBoth(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);
	/**
	 *双方都没有主动结束：12小时未响应超时查询
	 * @param currTime
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	List<TaskOrder>  queryOrderStatusAfter12HourBoth(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);

	
	
	
	
	/**
	 * 叫醒自动转为进行中
	 * @param endTime
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	void appointOrderGoing(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);

	/**
	 * 叫醒自动结束
	 */
	void appointOrderFinish(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);
	/**
	 * 叫醒自动结束--查询
	 */
	List<TaskOrder>  queryAppointOrderFinish(@Param("currTime")Date  currTime,@Param("endTime")Date  endTime,@Param("queryStatus")Integer queryStatus ,@Param("updateStatus")Integer  updateStatus,@Param("skillType")Integer  skillType);




	/**
	 * 根据订单ID列表更新订单状态
	 * @param updateStatus
	 * @param statusList
	 */
	void  updateOrderStatus(@Param("updateStatus")Integer  updateStatus,@Param("list")List<Long>  orderIdList);

}