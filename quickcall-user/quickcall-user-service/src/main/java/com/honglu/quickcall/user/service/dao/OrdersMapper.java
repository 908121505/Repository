package com.honglu.quickcall.user.service.dao;

import javax.persistence.criteria.Order;

import com.honglu.quickcall.user.facade.entity.Orders;
import com.honglu.quickcall.user.facade.entity.Product;

public interface OrdersMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    //根据主播id和产品ID查询该商品卖出的数量
    Long queryCompleteNumByCustomerIdProductId(Orders orders);
}