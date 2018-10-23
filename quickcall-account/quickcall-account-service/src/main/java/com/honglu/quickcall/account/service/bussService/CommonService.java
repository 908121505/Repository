package com.honglu.quickcall.account.service.bussService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.entity.Skill;
import com.honglu.quickcall.account.facade.vo.OrderDetailVO;
import com.honglu.quickcall.account.facade.vo.OrderTempResponseVO;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.enums.PushAppMsgTypeEnum;

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
	
	public CommonResponse  getCommonResponse();
	/**
	 * 根据技能信息获取技能价格列表
	 * @param skill
	 * @return
	 */
	public List<BigDecimal>   getPriceList(Skill  skill);
	
	
	
	/**
	 * 向用户发送消息
	 * @param msgType
	 * @param sellerId
	 * @param customerId
	 */
	public  void  pushMessage(PushAppMsgTypeEnum msgType,Long  sellerId,Long  customerId);
	
	/**
	 * 根据订单ID更新订单状态
	 * @param orderId
	 * @param orderStatus
	 */
	public void updateOrder(Long orderId, Integer orderStatus);
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
	 * 获取新的数据
	 * @param oldOrderStatus  订单状态
	 * @param orderTime  下单时间
	 * @param receiveOrderTime  接单时间
	 * @return
	 */
	public OrderTempResponseVO  getCountDownSeconds(Integer   oldOrderStatus ,Date  orderTime,Date  receiveOrderTime);
	
   
}
