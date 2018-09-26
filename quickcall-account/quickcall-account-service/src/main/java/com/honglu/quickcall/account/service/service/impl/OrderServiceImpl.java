package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.exchange.request.ApplayRefundRequest;
import com.honglu.quickcall.account.facade.exchange.request.CancelOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.ConfirmOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DetailOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvConfirmRefundRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvReceiveOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvStartServiceRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderDaVProductRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderReceiveOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSaveRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSendOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.PayOrderRequest;
import com.honglu.quickcall.account.facade.vo.OrderDaVProductVO;
import com.honglu.quickcall.account.facade.vo.OrderDetailVO;
import com.honglu.quickcall.account.facade.vo.OrderReceiveOrderListVO;
import com.honglu.quickcall.account.facade.vo.OrderSendOrderListVO;
import com.honglu.quickcall.account.service.dao.AccountMapper;
import com.honglu.quickcall.account.service.dao.OrderMapper;
import com.honglu.quickcall.account.service.dao.ProductMapper;
import com.honglu.quickcall.account.service.service.CommonService;
import com.honglu.quickcall.account.service.service.IOrderService;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.user.facade.enums.PushAppMsgTypeEnum;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单相关逻辑
 * 
 * @Package: com.honglu.quickcall.account.web.service.impl
 * @author: chenliuguang
 * @date: 2018年9月22日 下午3:17:04
 */
@Service
public class OrderServiceImpl implements IOrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private CommonService  commonService;
	@Autowired
	private ProductMapper  productMapper;
	@Autowired
	private OrderMapper  orderMapper;
	@Autowired
	private AccountMapper  accountMapper;

	
	
	
	
	
	@Override
	public CommonResponse queryDaVProduct(OrderDaVProductRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询技能信息参数异常");
		}
		Long  customerId =  request.getCustomerId();
		List<OrderDaVProductVO>  resultList =  productMapper.selectDaVPersonalProduct(customerId);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}
	
	
	
	
	@Override
	public CommonResponse saveOrder(OrderSaveRequest request) {
		if (request == null || request.getCustomerId() == null || request.getProductId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询技能信息参数异常");
		}
		//创建订单
		Long  customerId =  request.getCustomerId();
		Order record = new Order();
		Long  orderId =  UUIDUtils.getId();
		
		
		
		Long  sellerId =  request.getSellerId() ;
		record.setOrderId(orderId);
		record.setBuyerId(customerId);
		record.setCreateTime(new Date());
		Integer  orderNum =  request.getOrderNum();
		BigDecimal  price =  request.getPrice();
		BigDecimal orderAmounts = new BigDecimal(orderNum).multiply(price);
		record.setProductId(request.getProductId());
		record.setOrderAmounts(orderAmounts);
		record.setSellerId(sellerId);
		record.setOrderNum(orderNum);
		record.setOrderStatus(OrderSkillConstants.ORDER_STATUS_NOT_PAY);
		
		String  startTimeStr =  request.getStartTimeStr();
		Date  startTime = DateUtils.formatDateExt(startTimeStr);
		record.setStartTime(startTime);
		orderMapper.insert(record);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderId);
		LOGGER.info("用户编号为：" + request.getCustomerId() + "下单成功");
		return commonResponse;
	}




	/**
	 * 已收到的订单只能是已支付状态
	 */
	@Override
	public CommonResponse queryReceiveOrderList(OrderReceiveOrderListRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询接收订单参数异常");
		}
		Long  customerId =  request.getCustomerId();
		List<OrderReceiveOrderListVO>  resultList =  orderMapper.queryReceiveOrderList(customerId);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("查询收到的订单，用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}




	/**
	 * 发起的订单是全状态
	 */
	@Override
	public CommonResponse querySendOrderList(OrderSendOrderListRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询发起订单参数异常");
		}
		Long  customerId =  request.getCustomerId();
		List<OrderSendOrderListVO>  resultList =  orderMapper.querySendOrderList(customerId);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("查询发送的订单，用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}



	//==================================发起的订单页相关开始==================================
	
	@Override
	public CommonResponse cancelOrder(CancelOrderRequest request) {
		if (request == null || request.getOrderId() == null ) {
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝订单参数异常");
		}
		
		Long  orderId =  request.getOrderId();
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		if(order != null ){
			Integer   oldOrderStatus =  order.getOrderStatus();
			Integer   orderStatus =  null ;
			//根据不同状态进行取消
			//下单未支付
			if(OrderSkillConstants.ORDER_STATUS_NOT_PAY  == oldOrderStatus){
				//支付之前取消
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_PAY;
			}else if(OrderSkillConstants.ORDER_STATUS_PAYED  == oldOrderStatus){
				//订单状态5.大V接单前用户自主取消
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_PAYED_USER_SELE_CANCEL;
			}else if(OrderSkillConstants.ORDER_STATUS_PAYED_DV_ACCEPT_ORDER  == oldOrderStatus){
				//订单状态5.大V接单前用户自主取消
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCLE_DV_ACCEPT_USER_SELF_CANCLE;
			}
			commonService.updateOrder(orderId, orderStatus,null);
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		LOGGER.info("订单支付，订单编号：" + orderId + "，大V同意/拒绝订单订单完成");
		return commonResponse;
	}
	
	
	@Override
	public CommonResponse detailOrder(DetailOrderRequest request) {
		if (request == null || request.getOrderId() == null  || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询订单详情参数异常");
		}
		Integer  type =  request.getType();
		if(type != OrderSkillConstants.REQUEST_TYPE_CUST  &&  type != OrderSkillConstants.REQUEST_TYPE_DV){
			throw new BizException(AccountBizReturnCode.paramError, "查询订单详情参数异常");
		}
		
		
		Long  orderId =  request.getOrderId();
		OrderDetailVO  orderDetail =  null ;
		if(type == OrderSkillConstants.REQUEST_TYPE_CUST ){
			orderDetail =  orderMapper.queryCustOrderDetail(orderId);
		}else{
			orderDetail =  orderMapper.queryDvOrderDetail(orderId);
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderDetail);
		LOGGER.info("查询发送的订单，用户编号为：" + orderId + "查询成功");
		return commonResponse;
	}
	
	
	
	@Override
	public CommonResponse payOrder(PayOrderRequest request) {
		if (request == null || request.getOrderId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询发起订单参数异常");
		}
		Long  orderId =  request.getOrderId();
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		if(order != null){
			BigDecimal  payAmount =  order.getOrderAmounts();
			Long  userId =  order.getBuyerId();
			Long  sellerId =  order.getSellerId();
			//判断余额是否充足
			Account account=accountMapper.queryAccount(userId);
			BigDecimal  remainderAmount =  account.getRemainderAmounts();
			if(remainderAmount != null){
				if(payAmount.compareTo(remainderAmount) < 0){
					commonService.updateOrder(orderId, OrderSkillConstants.ORDER_STATUS_PAYED,null);
					//修改账户余额
					accountMapper.outAccount(userId, payAmount);
					//发送消息 
					commonService.pushMessage(PushAppMsgTypeEnum.NEW_ORDER, sellerId, userId);
				}else{
					//TODO  返回余额不足状态
				}
			}else{
				//TOD 余额不足提醒
			}
			
		}
		
		CommonResponse commonResponse = commonService.getCommonResponse();
		LOGGER.info("订单支付，订单编号：" + orderId + "，支付完成");
		return commonResponse;
	}




//	@Override
//	public CommonResponse copyOrder(CopyOrderRequest request) {
//		if (request == null || request.getOrderId() == null) {
//			throw new BizException(AccountBizReturnCode.paramError, "查询发起订单参数异常");
//		}
//		Long  orderId =  request.getOrderId();
//		//查询订单详情
//		Order  order = orderMapper.selectByPrimaryKey(orderId);
//		
//		
//		Date  currTime =  new Date();
//		Order  orderInsert =  new Order();
//		BeanUtils.copyProperties(order, orderInsert);
//		orderInsert.setCreateTime(currTime);
//		
//		
//		
//		CommonResponse commonResponse = commonService.getCommonResponse();
//		LOGGER.info("订单支付，订单编号：" + orderId + "，支付完成");
//		return commonResponse;
//	}




	@Override
	public CommonResponse applayRefund(ApplayRefundRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "申请退款/完成订单参数异常");
		}
		Long  orderId =  request.getOrderId();
		Integer  type =  request.getType();
		
		if(type != OrderSkillConstants.REQUEST_REFUND_TYPE_REFUND  && type != OrderSkillConstants.REQUEST_REFUND_TYPE_FINISH){
			throw new BizException(AccountBizReturnCode.paramError, "申请退款/完成订单参数异常");
		}
		
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		if(order != null ){
			Long  sellerId =  order.getSellerId();//主播ID
			Long  userId =  order.getBuyerId();
			if(OrderSkillConstants.REQUEST_REFUND_TYPE_REFUND == type ){
				
				//退款理由
				String  refundReason = request.getRefundReason();
				
				//BigDecimal  payAmount = order.getOrderAmounts();
				//不能对客户入账，只能是主播端同意之后才行
				//accountMapper.inAccount(userId, payAmount);
				//修改订单状态为：申请退款
				commonService.updateOrder(orderId, OrderSkillConstants.ORDER_STATUS_USER_APPLAY_REFUND,refundReason);
				commonService.pushMessage(PushAppMsgTypeEnum.REFUND_TIP, sellerId, userId);
			}
			if(OrderSkillConstants.REQUEST_REFUND_TYPE_FINISH == type ){
				//修改订单状态为：15.订单完成（正常完成）
				commonService.updateOrder(orderId, OrderSkillConstants.ORDER_STATUS_END,null);
			}
		}
		
		CommonResponse commonResponse = commonService.getCommonResponse();
		LOGGER.info("订单支付，订单编号：" + orderId + "，申请退款/完成订单完成");
		return commonResponse;
	}




	@Override
	public CommonResponse confirmOrder(ConfirmOrderRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "同意/拒绝订单参数异常");
		}
		
		Long  orderId =  request.getOrderId();
		Integer  type =  request.getType();
		if(type != OrderSkillConstants.REQUEST_CONFIRM_TYPE_YES  &&  type != OrderSkillConstants.REQUEST_CONFIRM_TYPE_NO){
			throw new BizException(AccountBizReturnCode.paramError, "同意/拒绝订单参数异常");
		}
		
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		if(order != null ){
			Integer   orderStatus = null ;
			//用户同意，修改状态，用户不同意，状态不变
			if(OrderSkillConstants.REQUEST_CONFIRM_TYPE_YES == type ){
				orderStatus = OrderSkillConstants.ORDER_STATUS_CUST_AGREE_DV_START_SERVICE;
			}else{
				orderStatus = OrderSkillConstants.ORDER_STATUS_CUST_REFUSE_DV_START_SERVICE;
			}
			commonService.updateOrder(orderId, orderStatus,null);
		}
		
		CommonResponse commonResponse = commonService.getCommonResponse();
		LOGGER.info("订单支付，订单编号：" + orderId + "，同意/拒绝订单完成");
		return commonResponse;
	}



	

	@Override
	public CommonResponse dvReceiveOrder(DvReceiveOrderRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝订单参数异常");
		}
		
		Long  orderId =  request.getOrderId();
		Integer  type =  request.getType();
		if(type != OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_YES   && type != OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_NO){
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝订单参数异常");
		}
		
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		if(order != null ){
			Integer   orderStatus =  null ;
			//大V同意，状态为大V接受
			if(OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_YES == type ){
				orderStatus = OrderSkillConstants.ORDER_STATUS_PAYED_DV_ACCEPT_ORDER;
			//大V不同意，状态为大V拒绝
			}else {
				orderStatus = OrderSkillConstants.ORDER_STATUS_PAYED_DV_REFUSE;
			}
			commonService.updateOrder(orderId, orderStatus,null);
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		LOGGER.info("订单支付，订单编号：" + orderId + "，大V同意/拒绝订单订单完成");
		return commonResponse;
	}




	@Override
	public CommonResponse dvStartService(DvStartServiceRequest request) {
		if (request == null || request.getOrderId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "大V立即开始参数异常");
		}
		
		Long  orderId =  request.getOrderId();
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		if(order != null ){
			Integer   orderStatus =  OrderSkillConstants.ORDER_STATUS_PAYED_DV_CONFIRM_START ;
			commonService.updateOrder(orderId, orderStatus,null);
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		LOGGER.info("订单支付，订单编号：" + orderId + "，大V立即开始完成");
		return commonResponse;
	}




	@Override
	public CommonResponse dvConfirmRefund(DvConfirmRefundRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝退款参数异常");
		}
		Long  orderId =  request.getOrderId();
		Integer  type =  request.getType();
		
		if(type != OrderSkillConstants.REQUEST_DV_REFUND_TYPE_YES   && type != OrderSkillConstants.REQUEST_DV_REFUND_TYPE_NO){
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝退款参数异常");
		}
		
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		if(order != null ){
			Integer   orderStatus =  null ;
			//大V同意，状态为大V同意退款，给用户退款
			if(OrderSkillConstants.REQUEST_DV_REFUND_TYPE_YES == type ){
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_DV_AGREE_REFUND;
			//大V不同意，状态为大V拒绝
			}else {
				orderStatus = OrderSkillConstants.ORDER_STATUS_END_DV_REFUSE;
			}
			commonService.updateOrder(orderId, orderStatus,null);
			//大V同意退款
			if(OrderSkillConstants.REQUEST_DV_REFUND_TYPE_YES == type ){
				Long  customerId =  order.getBuyerId();
				BigDecimal  payAmount = order.getOrderAmounts();
				//大V同意退款，对消费客户入账
				accountMapper.inAccount(customerId, payAmount);
			}
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		LOGGER.info("订单支付，订单编号：" + orderId + "，大V同意/拒绝退款订单完成");
		return commonResponse;
	}










	
	//================================发起的订单页相关结束==================================

}
