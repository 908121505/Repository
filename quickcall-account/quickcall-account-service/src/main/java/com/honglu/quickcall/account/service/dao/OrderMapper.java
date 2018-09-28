package com.honglu.quickcall.account.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.vo.OrderDetailVO;
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
	List<OrderSendOrderListVO> querySendOrderList(@Param("customerId")  Long customerId);

	/**查询收到的订单*/
	List<OrderReceiveOrderListVO> queryReceiveOrderList(@Param("customerId")  Long customerId);


	/**查询大V订单详情*/
	OrderDetailVO queryDvOrderDetail(@Param("orderId")Long orderId);
	/**查询客户订单详情*/
	OrderDetailVO queryCustOrderDetail(@Param("orderId")Long orderId);
	/**查询进行中订单数量*/
	Integer queryIngOrderCount(@Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId, @Param("orderStatus")Integer orderStatus, @Param("orderStatusTwo")Integer orderStatusTwo);
}