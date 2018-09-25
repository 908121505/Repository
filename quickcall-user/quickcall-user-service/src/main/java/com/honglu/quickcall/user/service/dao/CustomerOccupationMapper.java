package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.CustomerOccupation;

public interface CustomerOccupationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerOccupation record);

    int insertSelective(CustomerOccupation record);

    CustomerOccupation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerOccupation record);

    int updateByPrimaryKey(CustomerOccupation record);
    //查询是否有重复数据
    int findRepetitveData(Long customerId);
    //根据customerId更新
    int updateByCustomerIdSelective(CustomerOccupation record);
}