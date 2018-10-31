package com.honglu.quickcall.account.service.bussService;

import com.honglu.quickcall.account.facade.exchange.request.*;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单相关
 * @Package: com.honglu.quickcall.account.web.core
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:16:34
 */
public interface IOrderService {
    /**
     * 获取主播开启产品
     * @param request
     */
	public CommonResponse  queryDaVSkill(OrderDaVSkillRequest  request);
	/**
	 * 用户下单
	 * @param request
	 */
	public CommonResponse  saveOrder(OrderSaveRequest  request);
	
	
	/**
	 * 收到的订单
	 * @param request
	 */
	public CommonResponse  queryReceiveOrderList(OrderReceiveOrderListRequest  request);
	
	
	/**
	 * 发起的订单
	 * @param request
	 */
	public CommonResponse  querySendOrderList(OrderSendOrderListRequest  request);
	
	
	//==================================发起的订单页相关开始==================================
	/**
	 * 发起的订单页--取消订单
	 * @param request
	 */
	public CommonResponse  cancelOrder(CancelOrderRequest  request);
	
	
	/**
	 * 订单详情
	 * @param request
	 */
	public CommonResponse  detailOrder(DetailOrderRequest  request);
	
	
	
//	/**
//	 * 发起的订单页--去支付
//	 * @param req
//	 */
//	public CommonResponse  payOrder(PayOrderRequest  req);
	
	
//	/**
//	 * 发起的订单页--再来一单
//	 * @param req
//	 */
//	public CommonResponse  copyOrder(CopyOrderRequest  req);
	
	
	/**
	 * 用户同意大V服务完成
	 * @param request
	 */
	public CommonResponse  custConfirmFinish(CustConfirmFinishRequest  request);
	
	
	/**
	 * 用户同意大V立即服务
	 * @param request
	 */
	public CommonResponse  confirmOrder(ConfirmOrderRequest  request);
	
	//================================发起的订单页相关结束==================================
	
	
	
	//##################################大V收到的订单页相关开始##################################
	/**
	 * 收到的订单页--大V接受/拒绝
	 * @param request
	 */
	public CommonResponse  dvReceiveOrder(DvReceiveOrderRequest  request);
	
	/**
	 * 收到的订单页--大V立即服务
	 * @param request
	 */
	public CommonResponse  dvStartService(DvStartServiceRequest  request);
	
	
	/**
	 *用户/大V完成服务
	 * @param request
	 */
	public CommonResponse  finishOrder(FinishOrderRequest  request);
	
	//##################################大V收到的订单页相关结束##################################
	
	/**
	 * 查询是否有进行中订单
	 * @param request
	 */
	public CommonResponse  queryIngOrderCount(QueryIngOrderCountRequest  request);
	
	
	
	/**
	 * 查询退款理由
	 * @param request
	 */
	public CommonResponse  queryRefundReason(QueryRefundReasonRequest  request);

	/**
	 * 订单评价页面
	 * @param request
	 * @return
	 */
	CommonResponse orderEvaluation(OrderEvaluationRequest request);

	/**
	 * 提交订单评价
	 * @param request
	 * @return
	 */
	CommonResponse submitOrderEvaluation(OrderEvaluationSubmitRequest request);
	
	/***
	 * IM订单详情
	 * @param request
	 * @return
	 */
	public CommonResponse detailOrderForIM(DetailOrderForIMRequest request);

}
