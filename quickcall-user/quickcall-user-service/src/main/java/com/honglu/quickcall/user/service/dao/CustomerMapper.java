package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}