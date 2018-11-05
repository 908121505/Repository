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
	void  updateCustomerSkill(@Param("updateStatus")Integer  updateStatus,@Param("list")List<Long>  skillIdList);
	/**
	 * 查询需要打开开关的技能列表
	 * @param queryStatus
	 * @param queryEndTime
	 * @return
	 */
	List<Long>  queryCustomerSkill(@Param("queryStatus")Integer queryStatus ,@Param("queryEndTime")Date  queryEndTime);
	

}