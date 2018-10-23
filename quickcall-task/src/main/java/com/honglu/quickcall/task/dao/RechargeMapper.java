package com.honglu.quickcall.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.honglu.quickcall.task.entity.Recharge;

@Mapper
public interface RechargeMapper {

	List<Recharge> queryAbnormityOfRecharge();

	int updateByOrderNo(Recharge record);

}