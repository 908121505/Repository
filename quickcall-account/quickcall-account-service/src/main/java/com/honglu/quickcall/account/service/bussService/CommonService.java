package com.honglu.quickcall.account.service.bussService;

import java.util.Date;
import java.util.List;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.vo.OrderTempResponseVO;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.entity.Customer;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：公用方法
 * @Package: com.honglu.quickcall.account.web.service 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:16:34
 */
public interface CommonService {
	
	
	/**
	 * 根据客户ID获取客户手机号码
	 * @param customerId
	 */
	public Customer  getPhoneByCustomerId(Long  customerId);
	
	public CommonResponse  getCommonResponse();
	
	/**
	 * 根据订单ID更新订单状态
	 * @param orderId
	 * @param orderStatus
	 */
	public void updateOrder(Long orderId, Integer orderStatus);
	/**
	 * 根据订单ID更新订单状态
	 * @param orderId
	 * @param orderStatus
	 */
	public void custConfirmFinishUpdateOrder(Long orderId, Integer orderStatus);
	/**
	 * 根据订单ID更新订单状态
	 * @param orderId
	 * @param orderStatus
	 */
	public void cancelUpdateOrder(Long orderId, Integer orderStatus,Date cancelTime,String  selectReason,String   remarkReason,Integer  couponFlag);
	
	/**
	 * 根据订单ID更新订单状态
	 * @param orderId
	 * @param orderStatus
	 * @param startServiceTime
	 */
	public void dvStartServiceUpdateOrder(Long orderId, Integer orderStatus,Date  startServiceTime);
	/**
	 * 根据订单ID更新订单状态
	 * @param orderId
	 * @param orderStatus
	 * @param startTime
	 */
	public void confirmOrderUpdateOrder(Long orderId, Integer orderStatus,Date  startTime,Date  endTime);
	/**
	 * 大V接单修改订单信息
	 * @param orderId
	 * @param orderStatus
	 * @param receiveOrderTime
	 */
	public void dvReciveOrderUpdateOrder(Long orderId, Integer orderStatus,Date  receiveOrderTime);
	/**
	 * 支付修改订单状态
	 * @param orderId
	 * @param orderStatus
	 * @param refundReason
	 * @param payTime
	 */
	public void updateOrderForPay(Long orderId, Integer orderStatus,Date  payTime);
	
	
	/***
	 * 更新订单状态
	 * @param order
	 * @return
	 */
	public void  getNewOrder(Order  order);
	
	
	/**
	 * 获取新的数据，获取实时数据
	 * @param oldOrderStatus
	 * @param orderTime
	 * @param receiveOrderTime
	 * @param startServiceTime
	 * @param expectEndTime
	 * @param appointTime
	 * @return
	 */
	public OrderTempResponseVO  getCountDownSeconds(Integer   oldOrderStatus ,Date  orderTime,Date  receiveOrderTime,Date startServiceTime,Date expectEndTime,Date  appointTime);
	
	
	
	/**
	 * 大V接单取消已接收的订单
	 * @param orderIdList
	 */
	public void updateOrderReceiveOrder(List<Long>  orderIdList,Integer  orderStatus);
	/**
	 * 大V接单查询是否有其他待接单订单
	 * @param serviceId
	 * @param orderId
	 * @param queryStatus
	 * @param skillType
	 * @return
	 */
	public List<Order> selectOrderReceiveOrder(Long  serviceId ,Long orderId, Integer queryStatus, Integer skillType);
	
	
	/**
	 * 根据订单大类获取订单子类列表(用户方)
	 * @param orderStatusParam
	 * @return
	 */
	public  List<Integer>  getSendOrderStatusList(Integer orderStatusParam);
	/**
	 * 根据订单大类获取订单子类列表(大V方)
	 * @param orderStatusParam
	 * @return
	 */
	public  List<Integer>  getReceiveOrderStatusList(Integer orderStatusParam);



	public void finishUpdateOrder(Long orderId, Integer orderStatus, Date cancelTime,Integer  sendMsgIndex);
	
	/**
	 * 根据用户标识+订单状态获取消息提醒
	 * @param customerFlag
	 * @param orderStatus
	 * @return
	 */
	public String   getMsgContent(String  customerFlag ,Integer  orderStatus);
   
}
