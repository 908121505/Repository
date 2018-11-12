package com.honglu.quickcall.task.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataBalanceMapper {
	//查询前一天的用户注册量
	int queryRegistrationNum();
	//查询当前付费用户数量
	int queryPaidUsersNum();
	//查询付费1个品类的付费用户数量
	int queryPaidSkillOneNum();
	//查询付费2个品类的付费用户数量
	int queryPaidSkillTwoNum();
	//查询付费3个品类的付费用户数量
	int queryPaidSkillThreeNum();
	
	int insertCustomerData(Map<String,Integer> params);
}
