package com.honglu.quickcall.account.service.dao;

import java.util.List;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.vo.OrderReceiveOrderListVO;
import com.honglu.quickcall.account.facade.vo.OrderSendOrderListVO;

public interface OrderMapper {
   
    int deleteByPrimaryKey(Long orderId);

    
    int insert(Order record);

   
    int insertSelective(Order record);

   
    Order selectByPrimaryKey(Long orderId);

    
    int updateByPrimaryKeySelective(Order record);

   
    int updateByPrimaryKey(Order record);
    
	/**查询发出的订单*/
	List<OrderSendOrderListVO> querySendOrderList(Long customerId);

	/**查询收到的订单*/
	List<OrderReceiveOrderListVO> queryReceiveOrderList(Long customerId);
}