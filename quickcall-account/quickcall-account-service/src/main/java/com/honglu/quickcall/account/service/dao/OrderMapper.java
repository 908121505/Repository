package com.honglu.quickcall.account.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.account.facade.entity.EvaluationLabel;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.vo.OrderDaVSkillVO;
import com.honglu.quickcall.account.facade.vo.OrderDetailVO;
import com.honglu.quickcall.account.facade.vo.OrderMsgOrderListVO;
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
	List<OrderSendOrderListVO> querySendOrderList(@Param("customerId")  Long customerId,@Param("statusList")List<Integer>  orderStatusList);

	/**查询收到的订单*/
	List<OrderReceiveOrderListVO> queryReceiveOrderList(@Param("customerId")  Long customerId,@Param("statusList")List<Integer>  orderStatusList);


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


	/**
	 * 根据客户编号查询用户可能在进行中的订单列表
	 * 具体规则如下：
	 *   skillType :未不可重复接单状态
	 *   orderStatus:已接单 进行中  已完成  
	 * @param customerId
	 * @return
	 */
	List<Order> selectGongIngOrderListByCustomerId(@Param("customerId")Long customerId,@Param("skillType")Integer  skillType,@Param("list")List<Integer>  statusList);
	
	/**
	 * 根据客户编号查询用户可能在进行中的订单列表
	 * 具体规则如下：
	 *   skillType :未不可重复接单状态
	 *   orderStatus:已接单 进行中  已完成  
	 * @param customerId
	 * @return
	 */
	Integer selectGongIngOrderListByCustomerSkillId(@Param("customerSkillId")Long customerSkillId,@Param("skillType")Integer  skillType,@Param("list")List<Integer>  statusList);


	/**
	 * 查询双方是否有订单关系
	 * @param customerId
	 * @param serviceId
	 * @param statusList
	 * @return
	 */
	Order queryOrderByCustomerIdAndServiceId(@Param("customerId")Long customerId, @Param("serviceId")Long serviceId, @Param("list")List<Integer> statusList);


	/***
	 * 取消其它订单
	 * @param orderId
	 * @param queryStatus
	 * @param updateStatus
	 * @param skillType
	 */
	void updateOrderReceiveOrder(@Param("list")List<Long>  orderIdList,@Param("orderStatus")Integer orderStatus);

	

	/**
	 * 查询用户是否接多单
	 * @param serviceId
	 * @param orderId
	 * @param orderStatus
	 * @param skillType
	 * @return
	 */
	List<Order> selectOrderReceiveOrder(@Param("serviceId")Long  serviceId ,@Param("orderId")Long orderId, @Param("orderStatus")Integer orderStatus, @Param("skillType")Integer skillType);


	/**
	 * 查询声优是否被客户关注
	 * @param serviceId -- 服务者ID
	 * @param customerId -- 客户ID
	 * @return
	 */
    Integer findServicerIsAttentioned(@Param("serviceId") Long serviceId, @Param("customerId") Long customerId);

	/**
	 * 插入订单服务者的粉丝
	 *
	 * @param id
	 * @param serviceId
	 * @param customerId
	 * @return
	 */
	int insertOrderServicerFans(@Param("id") Long id,
								@Param("serviceId") Long serviceId,
								@Param("customerId") Long customerId);


	/**
	 * 根据用户ID获取订单消息列表
	 * @param customerId
	 * @return
	 */
	List<OrderMsgOrderListVO> queryMsgOrderList(@Param("customerId")Long customerId);




}