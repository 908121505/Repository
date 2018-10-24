package com.honglu.quickcall.account.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.account.facade.entity.EvaluationLabel;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.vo.OrderDaVSkillVO;
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

	/**
	 * 查询弹幕显示需要的订单消息
	 * @param orderId
	 * @return
	 */
	OrderDetailVO queryBarrageOrderInfo(@Param("orderId") Long orderId);

	/**
	 * 查询评价页面需要的数据
	 * @param orderId
	 * @return
	 */
	OrderDetailVO queryEvaluationData(@Param("orderId") Long orderId);

	/**
	 * 保存订单表评价信息
	 * @param evaluationInfo
	 * @return
	 */
	int saveEvaluationInfo(Order evaluationInfo);

	/**
	 * 保存评价标签
	 * @param list
	 */
	int saveEvaluationLabels(List<EvaluationLabel> list);

	/**
	 * 删除旧的评价标签
	 * @param orderId
	 * @return
	 */
	int deleteEvaluationLabels(@Param("orderId") Long orderId);
	
	
	
	/**
	 * 根据客户编号获取客户信息
	 * @param customerId
	 * @return
	 */
	OrderDaVSkillVO  getCustomerByCustomerId(@Param("customerId") Long customerId);
}