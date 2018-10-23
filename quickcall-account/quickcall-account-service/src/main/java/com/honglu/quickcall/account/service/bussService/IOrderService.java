package com.honglu.quickcall.account.service.bussService;

import com.honglu.quickcall.account.facade.exchange.request.*;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单相关
 * @Package: com.honglu.quickcall.account.web.service 
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
	 * 发起的订单页--去支付
	 * @param request
	 */
	public CommonResponse  detailOrder(DetailOrderRequest  request);
	
	
	
//	/**
//	 * 发起的订单页--去支付
//	 * @param request
//	 */
//	public CommonResponse  payOrder(PayOrderRequest  request);
	
	
//	/**
//	 * 发起的订单页--再来一单
//	 * @param request
//	 */
//	public CommonResponse  copyOrder(CopyOrderRequest  request);
	
	
	/**
	 * 发起的订单页--申请退款/完成
	 * @param request
	 */
	public CommonResponse  applayRefund(ApplayRefundRequest  request);
	
	
	/**
	 * 发起的订单页--同意/拒绝
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
	
	
//	/**
//	 * 收到的订单页--大V同意退款/拒绝
//	 * @param request
//	 */
//	public CommonResponse  dvConfirmRefund(DvConfirmRefundRequest  request);
	
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

}
