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
import com.honglu.quickcall.account.facade.entity.EvaluationLabel;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.entity.SkillItem;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.account.facade.exchange.request.CancelOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.ConfirmOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.CustConfirmFinishRequest;
import com.honglu.quickcall.account.facade.exchange.request.DetailOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvReceiveOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvStartServiceRequest;
import com.honglu.quickcall.account.facade.exchange.request.FinishOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderDaVSkillRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderEvaluationRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderEvaluationSubmitRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderReceiveOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSaveRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSendOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryIngOrderCountRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryRefundReasonRequest;
import com.honglu.quickcall.account.facade.vo.OrderDaVSkillVO;
import com.honglu.quickcall.account.facade.vo.OrderDetailVO;
import com.honglu.quickcall.account.facade.vo.OrderEvaluationVo;
import com.honglu.quickcall.account.facade.vo.OrderReceiveOrderListVO;
import com.honglu.quickcall.account.facade.vo.OrderSendOrderListVO;
import com.honglu.quickcall.account.facade.vo.OrderSkillItemVO;
import com.honglu.quickcall.account.facade.vo.OrderTempResponseVO;
import com.honglu.quickcall.account.service.bussService.AccountService;
import com.honglu.quickcall.account.service.bussService.BarrageMessageService;
import com.honglu.quickcall.account.service.bussService.CommonService;
import com.honglu.quickcall.account.service.bussService.IOrderService;
import com.honglu.quickcall.account.service.dao.CustomerSkillMapper;
import com.honglu.quickcall.account.service.dao.OrderMapper;
import com.honglu.quickcall.account.service.dao.SkillItemMapper;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.DateUtils;
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
	private OrderMapper  orderMapper;
	@Autowired
	private AccountService  accountService;
	@Autowired
	private CustomerSkillMapper  customerSkillMapper;
	@Autowired
	private BarrageMessageService barrageMessageService;
	@Autowired
	private SkillItemMapper skillItemMapper;
	
	
	

	
	
	
	
	
	@Override
	public CommonResponse queryDaVSkill(OrderDaVSkillRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询技能信息参数异常");
		}
		LOGGER.info("======>>>>>queryDaVSkill()入参："+request.toString());
		OrderDaVSkillVO  skill =  new OrderDaVSkillVO();
		skill.setHeadPortraitUrl("http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/c2bb9b11facd4ef98126d3a0ab495cd4.jpg");
		skill.setNickName("测试");
		skill.setServiceId(1000L);
		List<OrderSkillItemVO> custSkillList = new  ArrayList<>();
	
		for (int i = 0; i < 3; i++) {
			OrderSkillItemVO  vo =  new OrderSkillItemVO();
			vo.setPrice(new BigDecimal(10 *(i +1)));
			vo.setDiscontPrice(new BigDecimal(10 *(i +1)));
			vo.setUserSkillItemId(1000L);
			String  serviceUnit  = null ;
			String  skillIcon = null ;
			Integer  skillType = 1 ; 
			String  skillItemName  = "哄睡" ;
			if(i == 0){
				skillType = 1 ; 
				skillIcon  = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1540189000614.png" ;
				skillItemName  = "哄睡" ;
				serviceUnit = "半小时";
			}else if (i == 1){
				skillType = 2 ;
				skillIcon  = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1540189035478.png" ;
				skillItemName  = "情感咨询" ;
				serviceUnit = "小时";
			}else{
				skillType = 1 ;
				skillIcon  = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1540189035478.png" ;
				skillItemName  = "叫醒" ;
				serviceUnit = "次";
				
			}
			vo.setSkillType(skillType);
			vo.setSkillIcon(skillIcon);
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
	public CommonResponse queryDaVSkillExt(OrderDaVSkillRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询技能信息参数异常");
		}
		LOGGER.info("======>>>>>queryDaVSkill()入参："+request.toString());
		
		
		Long  customerId =  request.getCustomerId();
		OrderDaVSkillVO  resultVO = orderMapper.getCustomerByCustomerId(customerId);
		
		List<OrderSkillItemVO> custSkillList = new  ArrayList<>();
		
		List<CustomerSkill> skillList  = customerSkillMapper.querySkillInfoPersonal(customerId);
		if(!CollectionUtils.isEmpty(skillList)){
			for (CustomerSkill skill : skillList) {
				OrderSkillItemVO  vo =  new OrderSkillItemVO();
				vo.setPrice(skill.getSkillPrice());
				vo.setDiscontPrice(skill.getDiscountPrice());
				vo.setUserSkillItemId(skill.getCustomerSkillId());
				Long skillItemId = skill.getSkillItemId();
				SkillItem  skillItem = skillItemMapper.selectByPrimaryKey(skillItemId);
				vo.setSkillType(skillItem.getSkillType());
				vo.setSkillIcon(skillItem.getUnlockIcon());
				vo.setServiceUnit(skill.getServiceUnit());
				vo.setSkillItemName(skillItem.getSkillItemName());
				custSkillList.add(vo);
			}
		}
		
		resultVO.setCustSkillList(custSkillList );
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultVO);
		LOGGER.info("======>>>>>用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}
	
	
	
	
	@Override
	public CommonResponse saveOrder(OrderSaveRequest request) {
		if (request == null || request.getCustomerId() == null || request.getCustomerSkillId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "下单参数异常");
		}
		LOGGER.info("======>>>>>saveOrder()入参："+request.toString());
		
		Long  customerId =  request.getCustomerId();
		Long  customerSkillId =  request.getCustomerSkillId();
		//根据技能ID 获取等级获取价格信息
		CustomerSkill   customerSkill = customerSkillMapper.selectByPrimaryKey(customerSkillId);
		
		
		
		Integer  orderNum =  request.getOrderNum();
		BigDecimal  price =  customerSkill.getDiscountPrice();
		BigDecimal orderAmounts = new BigDecimal(orderNum).multiply(price);
		//判断余额是否充足
		Account account=accountService.queryAccount(customerId);
		//消费用户的充值金额
		BigDecimal  rechargeAmounts =  account.getRechargeAmounts();
		if(rechargeAmounts != null){
			if(orderAmounts.compareTo(rechargeAmounts) >  0){
				//返回余额不足状态  
				throw new BizException(AccountBizReturnCode.ORDER_PAY_BALANCE_NOT_ENOUGH, "余额不足，无法支付");
			}
		}
		
		//创建订单
		Order record = new Order();
		
		Long  skillItemId = customerSkill.getSkillItemId();
		SkillItem  skillItem = skillItemMapper.selectByPrimaryKey(skillItemId);
		if(skillItem != null){
			record.setSkillType(skillItem.getSkillType());
		}
		record.setServiceUnit(customerSkill.getServiceUnit());
		record.setServicePrice(customerSkill.getSkillPrice());
		record.setDiscountRate(customerSkill.getDiscountRate());
		
		Long  orderId =  UUIDUtils.getId();
		Long  serviceId =  request.getServiceId();
		record.setCustomerId(customerId);
		record.setOrderId(orderId);
		record.setServiceId(serviceId);
		record.setCreateTime(new Date());
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
		
		if(!CollectionUtils.isEmpty(resultList)){
			for (OrderReceiveOrderListVO info : resultList) {
				OrderTempResponseVO  responseVO = commonService.getCountDownSeconds(info.getOrderStatus(), info.getOrderTime(), info.getReceiveOrderTime());
				info.setCountDownSeconds(responseVO.getCountDownSeconds());
				info.setOrderStatus(responseVO.getOrderStatus());
			}
		}
			
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("======>>>>>查询收到的订单，用户编号为：" + request.getCustomerId() + "查询成功");
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
		
		LOGGER.info("======>>>>>querySendOrderList()入参："+request.toString());
		Long  customerId =  request.getCustomerId();
		List<OrderSendOrderListVO>  resultList =  orderMapper.querySendOrderList(customerId);
		if(!CollectionUtils.isEmpty(resultList)){
			for (OrderSendOrderListVO info : resultList) {
				OrderTempResponseVO  responseVO = commonService.getCountDownSeconds(info.getOrderStatus(), info.getOrderTime(), info.getReceiveOrderTime());
				info.setCountDownSeconds(responseVO.getCountDownSeconds());
				info.setOrderStatus(responseVO.getOrderStatus());
			}
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
			//根据不同状态进行取消
			//待接单取消
			if(OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE  == oldOrderStatus){
				//支付之前取消
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_RECEIVE;
			//待开始  大V接单
			}else if(OrderSkillConstants.ORDER_STATUS_WAITING_START  == oldOrderStatus){
				//订单状态5.大V接单前用户自主取消
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_DAV_START;
			//大V发起开始服务
			}else if(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE  == oldOrderStatus){
				//订单状态8.大V接受订单之后开始之前用户自主取消
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCLE_USER_SELF_BEFORE_SERVICE;
			}
			BigDecimal   payAmount =  order.getOrderAmounts();
			if(orderStatus == null){
				throw new BizException(AccountBizReturnCode.paramError, "取消订单参数异常");
			}else{
				commonService.updateOrder(orderId, orderStatus);
				//金额不为空，说明需要退款给用户
				if(payAmount != null){
					Long  customerId =  order.getCustomerId();
					accountService.inAccount(customerId, payAmount,TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.OrderRefund);
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
		//订单详情时需要有倒计时业务
		if(type == OrderSkillConstants.REQUEST_TYPE_CUST ){
			orderDetail =  orderMapper.queryCustOrderDetail(orderId);
		}else{
			orderDetail =  orderMapper.queryDvOrderDetail(orderId);
		}
		
		if(orderDetail != null){
			Date  birthday  =  orderDetail.getBirthday();
			int   age = DateUtils.getAgeByBirth(birthday);
			orderDetail.setAge(age);
			OrderTempResponseVO  responseVO = commonService.getCountDownSeconds(orderDetail.getOrderStatus(), orderDetail.getOrderTime(), orderDetail.getReceiveOrderTime());
			orderDetail.setCountDownSeconds(responseVO.getCountDownSeconds());
			orderDetail.setOrderStatus(responseVO.getOrderStatus());
		}
		
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderDetail);
		LOGGER.info("======>>>>>查询发送的订单，用户编号为：" + orderId + "查询成功");
		return commonResponse;
	}
	
	
	
//	@Override
//	public CommonResponse payOrder(PayOrderRequest request) {
//		if (request == null || request.getOrderId() == null) {
//			throw new BizException(AccountBizReturnCode.paramError, "查询发起订单参数异常");
//		}
//		
//		LOGGER.info("======>>>>>payOrder()入参："+request.toString());
//		Long  orderId =  request.getOrderId();
//		//查询订单详情
//		Order  order = orderMapper.selectByPrimaryKey(orderId);
//		if(order != null){
//			BigDecimal  payAmount =  order.getOrderAmounts();
//			Long  userId =  order.getOrderId();
//			Long  sellerId =  order.getOrderId();
//			//判断余额是否充足
//			Account account=accountService.queryAccount(userId);
//			//消费用户的充值金额
//			BigDecimal  rechargeAmounts =  account.getRechargeAmounts();
//			if(rechargeAmounts != null){
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
//			}else{
//				//余额不足提醒
//				throw new BizException(AccountBizReturnCode.ORDER_PAY_ACCOUNT_NOT_EXIST, "账户不存在，无法支付");
//			}
//		}else{
//			//订单不存在
//			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
//		}
//		CommonResponse commonResponse = commonService.getCommonResponse();
//		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，支付完成");
//		return commonResponse;
//	}




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
	public CommonResponse custConfirmFinish(CustConfirmFinishRequest request) {
		if (request == null || request.getOrderId() == null ) {
			throw new BizException(AccountBizReturnCode.paramError, "用户同意大V服务完成参数异常");
		}
		
		LOGGER.info("======>>>>>applayRefund()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		
		
		Integer  newOrderStatus = null ;
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId); 
		if(order != null ){
			Integer  orderStatus =  order.getOrderStatus();
			if(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH != orderStatus){
				//只有进行中才能进行退款
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}
			newOrderStatus = OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT;
			//修改订单状态为：申请退款
			commonService.updateOrder(orderId, newOrderStatus);
		}else{
			//订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，用户同意大V服务完成订单完成");
		return commonResponse;
	}




	@Override
	public CommonResponse confirmOrder(ConfirmOrderRequest request) {
		if (request == null || request.getOrderId() == null ) {
			throw new BizException(AccountBizReturnCode.paramError, "同意/拒绝订单参数异常");
		}
		LOGGER.info("======>>>>>confirmOrder()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		Integer   newOrderStatus = null ;
		if(order != null ){
			Integer orderStatus =  order.getOrderStatus();
			if(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE != orderStatus){
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}
			
			//用户同意，修改状态
			newOrderStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
			commonService.updateOrder(orderId, newOrderStatus);
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
			//大V只能接受订单状态为：待接单
			if(OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE  != orderStatus){
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}
			
			//大V同意，状态为大V接受
			if(OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_YES == type ){
				newOrderStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START;
				//设置大V接单时间
				commonService.dvReciveOrderUpdateOrder(orderId, newOrderStatus,new Date());
			//大V不同意，状态为大V拒绝，退款给购买者
			}else {
				newOrderStatus = OrderSkillConstants.ORDER_STATUS_DAV_REFUSED_RECEIVE;
				BigDecimal  payAmount = order.getOrderAmounts();
				Long   customerId =  order.getCustomerId();
				accountService.inAccount(customerId, payAmount, TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.OrderRefund);
				commonService.updateOrder(orderId, newOrderStatus);
			}
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
		if(order != null ){
			Integer  oldOrderStatus =  order.getOrderStatus();
			//订单状态为10.大V接受订单    大V接受订单之后可以开启立即服务
			if(OrderSkillConstants.ORDER_STATUS_WAITING_START  != oldOrderStatus ){
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}
			orderStatus =  OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE ;
			commonService.updateOrder(orderId, orderStatus);
		}else{
			//订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，大V立即开始完成");
		return commonResponse;
	}




	@Override
	public CommonResponse finishOrder(FinishOrderRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "用户/大V完成服务参数异常");
		}
		
		LOGGER.info("======>>>>>dvConfirmRefund()入参："+request.toString());
		Long  orderId =  request.getOrderId();
		Integer  type =  request.getType();
		
		if(type != OrderSkillConstants.REQUEST_DV_FINISH_TYPE   && type != OrderSkillConstants.REQUEST_CUST_FINISH_TYPE){
			throw new BizException(AccountBizReturnCode.paramError, "用户/大V完成服务参数异常");
		}
		
		//查询订单详情
		Order  order = orderMapper.selectByPrimaryKey(orderId);
		Integer   newOrderStatus =  null ;
		if(order != null ){
			
			Integer  oldOrderStatus =  order.getOrderStatus();
			
			//只有在订单状态为26.进行中（大V发起开始服务用户5分钟内同意）;双方才可以进行服务完成操作
			if(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT  != oldOrderStatus){
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}
			//大V发起完成服务
			if(OrderSkillConstants.REQUEST_DV_FINISH_TYPE == type){
				newOrderStatus = OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH ;
			}else{
			//用户发起完成服务	
				newOrderStatus = OrderSkillConstants.ORDER_STATUS_GOING_USRE_APPAY_FINISH ;
			}
			commonService.updateOrder(orderId, newOrderStatus);
		}else{
			//订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，用户/大V完成服务订单完成");
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


    @Override
    public CommonResponse orderEvaluation(OrderEvaluationRequest request) {
        // 根据订单Id查询评价页面需要的数据
        OrderDetailVO orderDetailVO = orderMapper.queryEvaluationData(request.getOrderId());
        if (orderDetailVO == null) {
            return ResultUtils.resultDataNotExist("订单信息不存在");
        }

        OrderEvaluationVo orderEvaluationVo = new OrderEvaluationVo();
        orderEvaluationVo.setOrderId(request.getOrderId());
        orderEvaluationVo.setNickName(orderDetailVO.getNickName());
        orderEvaluationVo.setHeadPortraitUrl(orderDetailVO.getHeadPortraitUrl());

        // 查询技能评价标签
        List<EvaluationLabel> labelList = skillItemMapper.queryEvaluationLabel(orderDetailVO.getSkillItemId(), orderDetailVO.getSex());
        List<OrderEvaluationVo.EvaluationLabel> labelLists = new ArrayList<>();
        for (EvaluationLabel bean : labelList){
            OrderEvaluationVo.EvaluationLabel label = orderEvaluationVo.new EvaluationLabel();
            label.setLabelId(bean.getLabelId());
            label.setLabelName(bean.getLabelName());
            label.setBorderColor(bean.getBorderColor());
            labelLists.add(label);
        }
        orderEvaluationVo.setEvaluationLabelList(labelLists);
        return ResultUtils.resultSuccess(orderEvaluationVo);
    }

    @Override
    public CommonResponse submitOrderEvaluation(OrderEvaluationSubmitRequest request) {
		// 保存订单表评价信息
		Order evaluationInfo = new Order();
		evaluationInfo.setOrderId(request.getOrderId());
		evaluationInfo.setEvaluateStart(request.getEvaluateStart());
		evaluationInfo.setCustomerEvaluate(request.getEvaluateContent());
		orderMapper.saveEvaluationInfo(evaluationInfo);

		// 如果选择了标签，保存评价的标签
		if(request.getLabelIds() != null && request.getLabelIds().length > 0){
			// 查询订单的服务方ID
			OrderDetailVO orderDetailVO = orderMapper.queryEvaluationData(request.getOrderId());
			if(orderDetailVO.getCustomerIsEvaluate() == 1){
				// 删除旧的评价标签
				orderMapper.deleteEvaluationLabels(request.getOrderId());
			}

			List<EvaluationLabel> list = new ArrayList<>();
			for(Integer labelId : request.getLabelIds()){
				EvaluationLabel label = new EvaluationLabel();
				label.setEvaluationId(UUIDUtils.getId());
				label.setCustomerId(orderDetailVO.getServiceId());
				label.setSkillItemId(orderDetailVO.getSkillItemId());
				label.setOrderId(request.getOrderId());
				label.setLabelId(labelId);
				list.add(label);
			}
			orderMapper.saveEvaluationLabels(list);
		}

        return ResultUtils.resultSuccess();
    }
}
