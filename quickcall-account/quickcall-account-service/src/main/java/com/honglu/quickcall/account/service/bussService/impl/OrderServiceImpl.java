package com.honglu.quickcall.account.service.bussService.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.entity.CustomerSkill;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.account.facade.exchange.request.ApplayRefundRequest;
import com.honglu.quickcall.account.facade.exchange.request.CancelOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.ConfirmOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DetailOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvConfirmRefundRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvReceiveOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvStartServiceRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderDaVSkillRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderReceiveOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSaveRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSendOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.PayOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryIngOrderCountRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryRefundReasonRequest;
import com.honglu.quickcall.account.facade.vo.OrderDaVSkillVO;
import com.honglu.quickcall.account.facade.vo.OrderDetailVO;
import com.honglu.quickcall.account.facade.vo.OrderReceiveOrderListVO;
import com.honglu.quickcall.account.facade.vo.OrderSendOrderListVO;
import com.honglu.quickcall.account.facade.vo.OrderSkillItemVO;
import com.honglu.quickcall.account.service.bussService.AccountService;
import com.honglu.quickcall.account.service.bussService.BarrageMessageService;
import com.honglu.quickcall.account.service.bussService.CommonService;
import com.honglu.quickcall.account.service.bussService.IOrderService;
import com.honglu.quickcall.account.service.dao.CustomerSkillMapper;
import com.honglu.quickcall.account.service.dao.OrderMapper;
import com.honglu.quickcall.account.service.dao.ProductMapper;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.core.util.UUIDUtils;

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
//	@Autowired
//	private AccountMapper  accountMapper;
	@Autowired
	private AccountService  accountService;
	@Autowired
	private CustomerSkillMapper  customerSkillMapper;
	@Autowired
	private BarrageMessageService barrageMessageService;

	
	
	
	
	
	@Override
	public CommonResponse queryDaVSkill(OrderDaVSkillRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询技能信息参数异常");
		}
		LOGGER.info("======>>>>>queryDaVSkill()入参："+request.toString());
//		List<OrderDaVSkillVO>  resultList =  new ArrayList<OrderDaVSkillVO>();
//		Long  customerId =  request.getCustomerId();
//		List<OrderDaVSkillVO>  resultList =  customerSkillMapper.selectDaVSkill(customerId);
		
		OrderDaVSkillVO  skill =  new OrderDaVSkillVO();
		skill.setHeadPortraitUrl("http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/c2bb9b11facd4ef98126d3a0ab495cd4.jpg");
		skill.setNickName("测试");
		skill.setServiceId(1000L);
		List<OrderSkillItemVO> custSkillList = new  ArrayList<>();
	
		for (int i = 0; i < 3; i++) {
			OrderSkillItemVO  vo =  new OrderSkillItemVO();
			vo.setPrice(new BigDecimal(10 *(i +1)));
			vo.setUserSkillItemId(1000L);
			String  serviceUnit  = null ;
			String  skillItemName  = "哄睡" ;
			if(i == 0){
				skillItemName  = "哄睡" ;
				serviceUnit = "半小时";
			}else if (i == 1){
				skillItemName  = "情感咨询" ;
				serviceUnit = "小时";
				
			}else{
				skillItemName  = "叫醒" ;
				serviceUnit = "次";
				
			}
			vo.setServiceUnit(serviceUnit);
			vo.setSkillItemName(skillItemName);
			custSkillList.add(vo);
		}
		skill.setCustSkillList(custSkillList );
		
		
		
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(skill);
		LOGGER.info("======>>>>>用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}
	
	
	
	
	@Override
	public CommonResponse saveOrder(OrderSaveRequest request) {
		if (request == null || request.getCustomerId() == null || request.getCustomerSkillId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "下单参数异常");
		}
		LOGGER.info("======>>>>>saveOrder()入参："+request.toString());
		
		Long  customerSkillId =  request.getCustomerSkillId();
		//根据技能ID 获取等级获取价格信息
		
		CustomerSkill   customerSkill = customerSkillMapper.selectByPrimaryKey(customerSkillId);
		
		//创建订单
		Long  customerId =  request.getCustomerId();
		Order record = new Order();
		record.setServiceUnit(customerSkill.getServiceUnit());
		record.setServicePrice(customerSkill.getSkillPrice());
		record.setDiscountRate(customerSkill.getDiscountRate());
		
		Long  orderId =  UUIDUtils.getId();
		Long  serviceId =  request.getServiceId();
		record.setCustomerId(customerId);
		record.setOrderId(orderId);
		record.setServiceId(serviceId);
		record.setCreateTime(new Date());
		Integer  orderNum =  request.getOrderNum();
		BigDecimal  price =  null;
		BigDecimal orderAmounts = new BigDecimal(orderNum).multiply(price);
		record.setOrderAmounts(orderAmounts);
		record.setOrderNum(orderNum);
		record.setOrderStatus(OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE);
		record.setOrderTime(new Date());
		orderMapper.insert(record);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderId);
		LOGGER.info("======>>>>>用户编号为：" + request.getCustomerId() + "下单成功");
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
		
		LOGGER.info("======>>>>>queryReceiveOrderList()入参："+request.toString());
		Long  customerId =  request.getCustomerId();
		List<OrderReceiveOrderListVO>  resultList =  orderMapper.queryReceiveOrderList(customerId);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("======>>>>>查询收到的订单，用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}



	
	private static final  Integer   DIFF_MINUTES  = 30;

	/**
	 * 发起的订单是全状态
	 */
	@Override
	public CommonResponse querySendOrderList(OrderSendOrderListRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询发起订单参数异常");
		}
		
		LOGGER.info("======>>>>>querySendOrderList()入参："+request.toString());
		Long  customerId =  request.getCustomerId();
		List<OrderSendOrderListVO>  resultList =  orderMapper.querySendOrderList(customerId);
		if(!CollectionUtils.isEmpty(resultList)){
			Date  currDateTime =  new Date();
//			for (OrderSendOrderListVO info : resultList) {
//				Integer   orderStatus  = info.getOrderStatus();
//				if(OrderSkillConstants.ORDER_STATUS_NOT_PAY ==  orderStatus){
//					Date  orderTime =  info.getOrderTime();
//					//截止下单时间
//					Date  endPayDate = DateUtils.getAddDate(orderTime,DIFF_MINUTES);
//					//结束时间在当前时间之后，订单未取消
//					if(endPayDate.after(currDateTime)){
//						info.setCurrTime(currDateTime.getTime());
//						info.setEndTime(endPayDate.getTime());
//					}else{
//						info.setOrderStatus(OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_PAY);
//					}
//					
//				}else  if(OrderSkillConstants.ORDER_STATUS_PAYED ==  orderStatus){
//				//判断订单支付后，大V是否接单超时
//					Date  paymentTime =  info.getPaymentTime();
//					//截止下单时间
//					Date  endReceiveDate = DateUtils.getAddDate(paymentTime,DIFF_MINUTES);
//					//接受截止时间在当前时间之前，订单取消
//					if(endReceiveDate.before(currDateTime)){
//						info.setOrderStatus(OrderSkillConstants.ORDER_STATUS_CANCEL_PAYED_DV_RECEIVE_OVER_TIME);
//					}
//					
//				}
//			}
		}
		
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("======>>>>>查询发送的订单，用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}



	//==================================发起的订单页相关开始==================================
	@Override
	public CommonResponse cancelOrder(CancelOrderRequest request) {
		if (request == null || request.getOrderId() == null ) {
			throw new BizException(AccountBizReturnCode.paramError, "取消订单参数异常");
		}
		LOGGER.info("======>>>>>cancelOrder()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		Integer   orderStatus =  null ;
		if(order != null ){
			Integer   oldOrderStatus =  order.getOrderStatus();
			//订单金额
			BigDecimal   payAmount =  null;
//			//根据不同状态进行取消
//			//下单未支付
//			if(OrderSkillConstants.ORDER_STATUS_NOT_PAY  == oldOrderStatus){
//				//支付之前取消
//				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_PAY;
//			}else if(OrderSkillConstants.ORDER_STATUS_PAYED  == oldOrderStatus){
//				//订单状态5.大V接单前用户自主取消
//				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_PAYED_USER_SELE_CANCEL;
//				payAmount =  order.getOrderAmounts();
//			//大V接受订单
//			}else if(OrderSkillConstants.ORDER_STATUS_PAYED_DV_ACCEPT_ORDER  == oldOrderStatus){
//				//订单状态8.大V接受订单之后开始之前用户自主取消
//				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCLE_DV_ACCEPT_USER_SELF_CANCLE;
//				payAmount =  order.getOrderAmounts();
//			}else if(OrderSkillConstants.ORDER_STATUS_PAYED_DV_CONFIRM_START  == oldOrderStatus){
//				//订单状态12.大V接受订单之后开始之前用户自主取消
//				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_ING;
//				payAmount =  order.getOrderAmounts();
//			}
			if(orderStatus == null){
				throw new BizException(AccountBizReturnCode.paramError, "取消订单参数异常");
			}else{
				commonService.updateOrder(orderId, orderStatus,null);
				//金额不为空，说明需要退款给用户
				if(payAmount != null){
//					Long  buyerId =  order.getBuyerId();
//					accountMapper.inAccount(buyerId, payAmount,TransferTypeEnum.RECHARGE.getType());
//					accountService.inAccount(buyerId, payAmount,TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.OrderRefund);
				}
			}
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderStatus);
		LOGGER.info("======>>>>>订单编号：" + orderId + "，取消订单完成");
		return commonResponse;
	}
	
	
	@Override
	public CommonResponse detailOrder(DetailOrderRequest request) {
		if (request == null || request.getOrderId() == null  || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询订单详情参数异常");
		}
		
		LOGGER.info("======>>>>>detailOrder()入参："+request.toString());
		Integer  type =  request.getType();
		if(type != OrderSkillConstants.REQUEST_TYPE_CUST  &&  type != OrderSkillConstants.REQUEST_TYPE_DV){
			throw new BizException(AccountBizReturnCode.paramError, "查询订单详情参数异常");
		}
		
		
		Long  orderId =  request.getOrderId();
		OrderDetailVO  orderDetail =  null ;
		Date  currDateTime = new Date();
		if(type == OrderSkillConstants.REQUEST_TYPE_CUST ){
			orderDetail =  orderMapper.queryCustOrderDetail(orderId);
			//需要判断是否过了支付时间
			Integer   orderStatus  = orderDetail.getOrderStatus();
//			if(OrderSkillConstants.ORDER_STATUS_NOT_PAY ==  orderStatus){
//				Date  orderTime =  orderDetail.getOrderTime();
//				//截止下单时间
//				Date  endPayDate = DateUtils.getAddDate(orderTime,DIFF_MINUTES);
//				//结束时间在当前时间之后，订单未取消
//				if(endPayDate.before(currDateTime)){
//					orderDetail.setOrderStatus(OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_PAY);
//				}
//			}else  if(OrderSkillConstants.ORDER_STATUS_PAYED ==  orderStatus){
//			//判断订单支付后，大V是否接单超时
//				Date  paymentTime =  orderDetail.getPaymentTime();
//				//截止下单时间
//				Date  endReceiveDate = DateUtils.getAddDate(paymentTime,DIFF_MINUTES);
//				//接受截止时间在当前时间之前，订单取消
//				if(endReceiveDate.before(currDateTime)){
//					orderDetail.setOrderStatus(OrderSkillConstants.ORDER_STATUS_CANCEL_PAYED_DV_RECEIVE_OVER_TIME);
//				}
//			}
			
		}else{
			orderDetail =  orderMapper.queryDvOrderDetail(orderId);
			//需要判断是否过了支付时间
			Integer   orderStatus  = orderDetail.getOrderStatus();
//			if(OrderSkillConstants.ORDER_STATUS_PAYED ==  orderStatus){
//			//判断订单支付后，大V是否接单超时
//				Date  paymentTime =  orderDetail.getPaymentTime();
//				//截止下单时间
//				Date  endReceiveDate = DateUtils.getAddDate(paymentTime,DIFF_MINUTES);
//				//接受截止时间在当前时间之前，订单取消
//				if(endReceiveDate.before(currDateTime)){
//					orderDetail.setOrderStatus(OrderSkillConstants.ORDER_STATUS_CANCEL_PAYED_DV_RECEIVE_OVER_TIME);
//				}
//			}
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderDetail);
		LOGGER.info("======>>>>>查询发送的订单，用户编号为：" + orderId + "查询成功");
		return commonResponse;
	}
	
	
	
	@Override
	public CommonResponse payOrder(PayOrderRequest request) {
		if (request == null || request.getOrderId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询发起订单参数异常");
		}
		
		LOGGER.info("======>>>>>payOrder()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		if(order != null){
			BigDecimal  payAmount =  order.getOrderAmounts();
			Long  userId =  order.getOrderId();
			Long  sellerId =  order.getOrderId();
			//判断余额是否充足
			Account account=accountService.queryAccount(userId);
			//消费用户的充值金额
			BigDecimal  rechargeAmounts =  account.getRechargeAmounts();
			if(rechargeAmounts != null){
//				if(payAmount.compareTo(rechargeAmounts) <=  0){
//					commonService.updateOrderForPay(orderId, OrderSkillConstants.ORDER_STATUS_PAYED,new Date());
//					//修改账户余额
//					accountService.outAccount(userId, payAmount,TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.PlaceOrder);
//					//发送消息 
//					commonService.pushMessage(PushAppMsgTypeEnum.NEW_ORDER, sellerId, userId);
//
//					// ADUAN 下单支付成功后 -- 发送弹幕消息
//					barrageMessageService.lpushMessage(orderId);
//				}else{
//					//返回余额不足状态  
//					throw new BizException(AccountBizReturnCode.ORDER_PAY_BALANCE_NOT_ENOUGH, "余额不足，无法支付");
//				}
			}else{
				//余额不足提醒
				throw new BizException(AccountBizReturnCode.ORDER_PAY_ACCOUNT_NOT_EXIST, "账户不存在，无法支付");
			}
		}else{
			//订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，支付完成");
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
//		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，支付完成");
//		return commonResponse;
//	}




	@Override
	public CommonResponse applayRefund(ApplayRefundRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "申请退款/完成订单参数异常");
		}
		
		LOGGER.info("======>>>>>applayRefund()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		Integer  type =  request.getType();
		
		if(type != OrderSkillConstants.REQUEST_REFUND_TYPE_REFUND  && type != OrderSkillConstants.REQUEST_REFUND_TYPE_FINISH){
			throw new BizException(AccountBizReturnCode.paramError, "申请退款/完成订单参数异常");
		}
		
		Integer  newOrderStatus = null ;
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId); 
		if(order != null ){
			Integer  orderStatus =  order.getOrderStatus();
//			if(OrderSkillConstants.ORDER_STATUS_GOING_ING != orderStatus && OrderSkillConstants.ORDER_STATUS_END_DV_REFUSE  != orderStatus && OrderSkillConstants.ORDER_STATUS_CUST_AGREE_DV_START_SERVICE  != orderStatus   ){
//				//只有进行中才能进行退款
//				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
//			}
			Long  sellerId =  order.getOrderId();//主播ID
			Long  userId =  order.getOrderId();
//			if(OrderSkillConstants.REQUEST_REFUND_TYPE_REFUND == type ){
//				//退款理由
//				String  refundReason = request.getRefundReason();
//				newOrderStatus = OrderSkillConstants.ORDER_STATUS_USER_APPLAY_REFUND;
//				//修改订单状态为：申请退款
//				commonService.updateOrder(orderId, newOrderStatus,refundReason);
//				commonService.pushMessage(PushAppMsgTypeEnum.REFUND_TIP, sellerId, userId);
//			}else if(OrderSkillConstants.REQUEST_REFUND_TYPE_FINISH == type ){
//				newOrderStatus = OrderSkillConstants.ORDER_STATUS_END;
//				//修改订单状态为：18.订单完成（正常完成）
//				commonService.updateOrder(orderId, newOrderStatus,null);
//				//大V入账 
//				BigDecimal  orderAmount =  order.getOrderAmounts();
//				accountService.inAccount(sellerId, orderAmount, TransferTypeEnum.REMAINDER,AccountBusinessTypeEnum.CompleteOrder);
//			}
		}else{
			//订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，申请退款/完成订单完成");
		return commonResponse;
	}




	@Override
	public CommonResponse confirmOrder(ConfirmOrderRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "同意/拒绝订单参数异常");
		}
		LOGGER.info("======>>>>>confirmOrder()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		Integer  type =  request.getType();
		if(type != OrderSkillConstants.REQUEST_CONFIRM_TYPE_YES  &&  type != OrderSkillConstants.REQUEST_CONFIRM_TYPE_NO){
			throw new BizException(AccountBizReturnCode.paramError, "同意/拒绝订单参数异常");
		}
		
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		Integer   newOrderStatus = null ;
		if(order != null ){
			Integer orderStatus =  order.getOrderStatus();
//			if(OrderSkillConstants.ORDER_STATUS_PAYED_DV_CONFIRM_START != orderStatus){
//				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
//			}
//			
//			//用户同意，修改状态，用户不同意，状态不变
//			if(OrderSkillConstants.REQUEST_CONFIRM_TYPE_YES == type ){
//				newOrderStatus = OrderSkillConstants.ORDER_STATUS_CUST_AGREE_DV_START_SERVICE;
//			}else{
//				newOrderStatus = OrderSkillConstants.ORDER_STATUS_CUST_REFUSE_DV_START_SERVICE;
//				//退钱给用户
//				Long  customerId =  order.getCustomerId();
//				BigDecimal   payAmount =  order.getOrderAmounts();
//				accountService.inAccount(customerId, payAmount, TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.OrderRefund);
//			}
			commonService.updateOrder(orderId, newOrderStatus,null);
		}else{
			//订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，同意/拒绝订单完成");
		return commonResponse;
	}



	

	@Override
	public CommonResponse dvReceiveOrder(DvReceiveOrderRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝订单参数异常");
		}
		
		LOGGER.info("======>>>>>dvReceiveOrder()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		Integer  type =  request.getType();
		if(type != OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_YES   && type != OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_NO){
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝订单参数异常");
		}
		
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		Integer   newOrderStatus =  null ;
		if(order != null ){
			Integer  orderStatus = order.getOrderStatus();
//			//大V只能接受订单状态为：已支付的订单
//			if(OrderSkillConstants.ORDER_STATUS_PAYED  != orderStatus){
//				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
//			}
//			
//			//大V同意，状态为大V接受
//			if(OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_YES == type ){
//				newOrderStatus = OrderSkillConstants.ORDER_STATUS_PAYED_DV_ACCEPT_ORDER;
//			//大V不同意，状态为大V拒绝，退款给购买者
//			}else {
//				newOrderStatus = OrderSkillConstants.ORDER_STATUS_PAYED_DV_REFUSE;
//				BigDecimal  payAmount = order.getOrderAmounts();
//				Long   buyerId =  order.getCustomerId();
//				accountService.inAccount(buyerId, payAmount, TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.OrderRefund);
//			}
			commonService.updateOrder(orderId, newOrderStatus,null);
		}else{
			//订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，大V同意/拒绝订单订单完成");
		return commonResponse;
	}




	@Override
	public CommonResponse dvStartService(DvStartServiceRequest request) {
		if (request == null || request.getOrderId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "大V立即开始参数异常");
		}
		LOGGER.info("======>>>>>dvStartService()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		Integer   orderStatus =  null ;
//		if(order != null ){
//			Integer  oldOrderStatus =  order.getOrderStatus();
//			//订单状态为8.大V接受订单    大V接受订单之后可以开启立即服务
//			if(OrderSkillConstants.ORDER_STATUS_PAYED_DV_ACCEPT_ORDER  != oldOrderStatus){
//				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
//			}
//			orderStatus =  OrderSkillConstants.ORDER_STATUS_PAYED_DV_CONFIRM_START ;
//			commonService.updateOrder(orderId, orderStatus,null);
//		}else{
//			//订单不存在
//			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
//		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，大V立即开始完成");
		return commonResponse;
	}




	@Override
	public CommonResponse dvConfirmRefund(DvConfirmRefundRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝退款参数异常");
		}
		
		LOGGER.info("======>>>>>dvConfirmRefund()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		Integer  type =  request.getType();
		
		if(type != OrderSkillConstants.REQUEST_DV_REFUND_TYPE_YES   && type != OrderSkillConstants.REQUEST_DV_REFUND_TYPE_NO){
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝退款参数异常");
		}
		
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		Integer   newOrderStatus =  null ;
		if(order != null ){
			
			Integer  oldOrderStatus =  order.getOrderStatus();
			//订单状态为15.用户申请退款      只有用户发起退款申请，大V才能进行响应
//			if(OrderSkillConstants.ORDER_STATUS_USER_APPLAY_REFUND  != oldOrderStatus){
//				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
//			}
//			
//			//大V同意，状态为大V同意退款，给用户退款
//			if(OrderSkillConstants.REQUEST_DV_REFUND_TYPE_YES == type ){
//				newOrderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_DV_AGREE_REFUND;
//			//大V不同意，状态为大V拒绝
//			}else {
//				newOrderStatus = OrderSkillConstants.ORDER_STATUS_END_DV_REFUSE;
//			}
			commonService.updateOrder(orderId, newOrderStatus,null);
			//大V同意退款
			if(OrderSkillConstants.REQUEST_DV_REFUND_TYPE_YES == type ){
				Long  customerId =  order.getCustomerId();
				BigDecimal  payAmount = order.getOrderAmounts();
				//大V同意退款，对消费客户入账
				accountService.inAccount(customerId, payAmount,TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.OrderRefund);
			}
		}else{
			//订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，大V同意/拒绝退款订单完成");
		return commonResponse;
	}


	//================================发起的订单页相关结束==================================


	@Override
	public CommonResponse queryIngOrderCount(QueryIngOrderCountRequest request) {
		if (request == null || request.getBuyerId() == null  || request.getSellerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询进行中订单数量参数异常");
		}
		LOGGER.info("======>>>>>queryIngOrderCount()入参："+request.toString());
		Long  buyerId =  request.getBuyerId();
		Long  sellerId =  request.getSellerId();
//		Integer   count  =  orderMapper.queryIngOrderCount(buyerId,sellerId,OrderSkillConstants.ORDER_STATUS_GOING_ING,OrderSkillConstants.ORDER_STATUS_CUST_AGREE_DV_START_SERVICE);
//		if(count == null){
//			count = 0;
//		}
//		if(count == 0){
//			count  =  orderMapper.queryIngOrderCount(sellerId,buyerId,OrderSkillConstants.ORDER_STATUS_GOING_ING,OrderSkillConstants.ORDER_STATUS_CUST_AGREE_DV_START_SERVICE);
//		}
		
//		if(count == null){
//			count = 0;
//		}
		CommonResponse commonResponse = commonService.getCommonResponse();
//		commonResponse.setData(count);
		LOGGER.info("======>>>>>查询进行中订单数量，客户编号为：" + buyerId + ",大V客户编号："+sellerId+"查询成功");
		return commonResponse;
	}




	@Override
	public CommonResponse queryRefundReason(QueryRefundReasonRequest request) {
		CommonResponse commonResponse = commonService.getCommonResponse();
		List<String>  reasonList =  new ArrayList<String>();
		reasonList.add("态度恶劣");
		reasonList.add("迟到早退");
		reasonList.add("消极怠工");
		reasonList.add("不是本人");
		commonResponse.setData(reasonList);
		return commonResponse;
	}


}
