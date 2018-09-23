package com.honglu.quickcall.account.service.dao;

import com.honglu.quickcall.account.facade.entity.Order;

public interface OrderMapper {
   
    int deleteByPrimaryKey(Long orderId);

    
    int insert(Order record);

   
    int insertSelective(Order record);

   
    Order selectByPrimaryKey(Long orderId);

    
    int updateByPrimaryKeySelective(Order record);

   
    int updateByPrimaryKey(Order record);
}