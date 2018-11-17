package com.honglu.quickcall.account.service.dao;

import java.util.List;

import com.honglu.quickcall.account.facade.entity.Recharge;

public interface RechargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);
    
    int updateByOrderNo(Recharge record);

    Recharge selectByOrderNo(String orderNo);

	List<Recharge> selectByCustomerIdAndState(Long customerId);

}