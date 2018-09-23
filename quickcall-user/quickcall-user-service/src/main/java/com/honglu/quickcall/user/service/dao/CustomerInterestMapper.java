package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.CustomerInterest;

public interface CustomerInterestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerInterest record);

    int insertSelective(CustomerInterest record);

    CustomerInterest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerInterest record);

    int updateByPrimaryKey(CustomerInterest record);
    
    //根据customerId，interestID 插入表
    
    
}