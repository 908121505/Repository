package com.honglu.quickcall.account.service.bussService.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.vo.OrderTempResponseVO;
import com.honglu.quickcall.account.service.bussService.CommonService;
import com.honglu.quickcall.account.service.dao.OrderMapper;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.user.facade.entity.Customer;


@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Autowired
	private OrderMapper  orderMapper;
	
	@Override
	public CommonResponse  getCommonResponse(){
		 CommonResponse commonResponse = new CommonResponse();
		 commonResponse.setCode(BizCode.Success);
		 commonResponse.setMessage(BizCode.Success.desc());
		 return commonResponse;
	}

	@Override
	public void updateOrder(Long orderId, Integer orderStatus) {
		Order record = new Order();
		record.setOrderStatus(orderStatus);
		record.setOrderId(orderId);
		record.setModifyTime(new Date());
		//修改订单状态为：已支付
		orderMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public void updateOrderForPay(Long orderId, Integer orderStatus,Date  payTime) {
		Order record = new Order();
		record.setOrderStatus(orderStatus);
		record.setOrderId(orderId);
		record.setModifyTime(new Date());
		record.setPaymentTime(payTime);
		//修改订单状态为：已支付
		orderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void getNewOrder(Order order) {
		
		
		
		
	}

	/***
	 * 主要获取订单真是状态+真实的倒计时秒数
	 */
	@Override
	public OrderTempResponseVO getCountDownSeconds(Integer   oldOrderStatus ,Date  orderTime,Date  receiveOrderTime,Date startServiceTime,Date  expectEndTime,Date  appointTime) {
		OrderTempResponseVO  tempVO = new OrderTempResponseVO();
		Date  currTime = new Date();
		Long  countDownSeconds = 0L ;
		Date  endTime = null ;
		tempVO.setOrderStatus(oldOrderStatus);
		//需要调整用户5分钟未同意大V立即服务倒计时时间
		//待接单计算倒计时
		if(oldOrderStatus == OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE){
			if(orderTime != null){
				Calendar  cal =  Calendar.getInstance();
				cal.setTime(orderTime);
				cal.add(Calendar.MINUTE, OrderSkillConstants.RECEIVE_ORDER_OVER_TIME);
				endTime = cal.getTime();
				countDownSeconds =  DateUtils.getDiffSeconds(currTime, endTime);
			}
			//大于0说明正在倒计时，状态不变
			if(countDownSeconds >= 0){
				tempVO.setOrderStatus(oldOrderStatus);
				tempVO.setCountDownSeconds(countDownSeconds);
			}else{
				tempVO.setOrderStatus(OrderSkillConstants.ORDER_STATUS_CANCEL_SYSTEM_NOT_RECEIVE);
			}
			//大V未发起服务或者已经发起服务，用户未应答
		}else if(oldOrderStatus == OrderSkillConstants.ORDER_STATUS_WAITING_START ){
			if(receiveOrderTime != null){
				Calendar  cal =  Calendar.getInstance();
				cal.setTime(receiveOrderTime);
				cal.add(Calendar.MINUTE, OrderSkillConstants.START_SERVICE_OVER_TIME_DAV);//TODO 定义常量
				endTime = cal.getTime();
				countDownSeconds =  DateUtils.getDiffSeconds(currTime, endTime);
			}
			
			//大于0说明正在倒计时，状态不变
			if(countDownSeconds >= 0){
				tempVO.setOrderStatus(oldOrderStatus);
				tempVO.setCountDownSeconds(countDownSeconds);
			}else{
				//小于O说明真实状态已经变更
				Integer  newOrderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_START ;
				tempVO.setOrderStatus(newOrderStatus);
			}
			//用户5分钟未同意大V立即服务
		}else if(oldOrderStatus == OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE){
		
			if(startServiceTime != null){
				Calendar  cal =  Calendar.getInstance();
				//开始时间取声优发起立即服务这一刻
				cal.setTime(startServiceTime);
				cal.add(Calendar.MINUTE, OrderSkillConstants.START_SERVICE_OVER_TIME_DAV);//TODO 定义常量
				endTime = cal.getTime();
				countDownSeconds =  DateUtils.getDiffSeconds(currTime, endTime);
			}
			//大于0说明正在倒计时，状态不变
			if(countDownSeconds >= 0){
				tempVO.setOrderStatus(oldOrderStatus);
				tempVO.setCountDownSeconds(countDownSeconds);
			}else{
				//小于O说明真实状态已经变更
				Integer  newOrderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT ;
				tempVO.setOrderStatus(newOrderStatus);
			}
			//进行中倒计时计算，不同状态逻辑不同 ，需要重新梳理一下    TODO
		}else if(oldOrderStatus == OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT   || oldOrderStatus == OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH){
			//TODO  如果订单已经过了预计结束时间，则需要变更订单状态为已完成
			if(expectEndTime != null){
				endTime =  expectEndTime;
				countDownSeconds =  DateUtils.getDiffSeconds(currTime, endTime);
			}
			//大于0说明正在倒计时，状态不变  小于0的逻辑暂时不考虑
			if(countDownSeconds >= 0){
				tempVO.setOrderStatus(oldOrderStatus);
			}else{
				tempVO.setOrderStatus(OrderSkillConstants.ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME);
			}
			tempVO.setCountDownSeconds(countDownSeconds);
		//叫醒的24状态需要单独处理
		}else if(oldOrderStatus ==OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START){
			if(appointTime != null){
				//时间接单从当前时间到预约开始时间
				endTime = appointTime;
				countDownSeconds =  DateUtils.getDiffSeconds(currTime, endTime);
			}
			//大于0说明正在倒计时，状态不变  小于0的逻辑暂时不考虑
			tempVO.setOrderStatus(oldOrderStatus);
			if(countDownSeconds < 0){
				//小于0说明已经到了进行中状态
				tempVO.setOrderStatus(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT);
			}
			tempVO.setCountDownSeconds(countDownSeconds);
			
			
		}
		return tempVO ;
	}

	@Override
	public void dvReciveOrderUpdateOrder(Long orderId, Integer orderStatus, Date receiveOrderTime) {
		Order record = new Order();
		record.setOrderStatus(orderStatus);
		record.setOrderId(orderId);
		record.setModifyTime(new Date());
		record.setReceiveOrderTime(receiveOrderTime);
		//修改订单状态为：已支付
		orderMapper.updateByPrimaryKeySelective(record);
		
	}



	@Override
	public void confirmOrderUpdateOrder(Long orderId, Integer orderStatus, Date startTime,Date  endTime) {
		Order record = new Order();
		record.setOrderStatus(orderStatus);
		record.setOrderId(orderId);
		record.setModifyTime(new Date());
		Calendar  cal = Calendar.getInstance();
		cal.setTime(startTime);
//		cal.add(Calendar.MINUTE, 5);
		record.setStartTime(cal.getTime());
		if(endTime != null){
			record.setExpectEndTime(endTime);
		}
		//修改订单状态为：已支付
		orderMapper.updateByPrimaryKeySelective(record);
		
	}



	@Override
	public void dvStartServiceUpdateOrder(Long orderId, Integer orderStatus, Date startServiceTime) {
		Order record = new Order();
		record.setOrderStatus(orderStatus);
		record.setOrderId(orderId);
		record.setModifyTime(new Date());
		record.setStartServiceTime(startServiceTime);
		//修改订单状态为：已支付
		orderMapper.updateByPrimaryKeySelective(record);
		
	}



	@Override
	public void updateOrderReceiveOrder(List<Long>  orderIdList,Integer  orderStatus) {
		orderMapper.updateOrderReceiveOrder(orderIdList,orderStatus);
		
	}



	@Override
	public List<Order> selectOrderReceiveOrder(Long  serviceId ,Long orderId, Integer queryStatus, Integer skillType) {
		return orderMapper.selectOrderReceiveOrder(serviceId, orderId, queryStatus, skillType);
	}



	@Override
	public void cancelUpdateOrder(Long orderId, Integer orderStatus,Date cancelTime,String  selectReason,String   remarkReason,Integer  couponFlag) {
		Order record = new Order();
		record.setOrderStatus(orderStatus);
		record.setOrderId(orderId);
		record.setModifyTime(new Date());
		record.setCustCancelTime(cancelTime);
		record.setSelectReason(selectReason);
		record.setRemarkReason(remarkReason);
		//设置券状态
		record.setCouponFlag(couponFlag);
		//修改订单状态为：已支付
		orderMapper.updateByPrimaryKeySelective(record);
		
	}



	//用户方
	@Override
	public List<Integer> getSendOrderStatusList(Integer orderStatusParam) {
		List<Integer>  retList = new ArrayList<>();
		if(OrderSkillConstants.ORDER_STATUS_PARAM_TOTAL == orderStatusParam){
			retList = null ;
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_WAITING_RECEIVE_ORDER == orderStatusParam){
			retList.add(OrderSkillConstants.ORDER_STATUS_PARAM_WAITING_RECEIVE_ORDER);
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_WAITING_START == orderStatusParam){
			
			retList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START);
			retList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE);
			retList.add(OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START);
			
			
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_GOING == orderStatusParam){
			retList.add(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT);
			retList.add(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH);
		
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_FINISHED == orderStatusParam){
			
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISHED_AND_PINGJIA);
		
		
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_REFUSED == orderStatusParam){
			
			retList.add(OrderSkillConstants.ORDER_STATUS_DAV_REFUSED_RECEIVE);
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_CANCEL == orderStatusParam){
			
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_RECEIVE);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_SYSTEM_NOT_RECEIVE);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_DAV_START_ONE_ORDER);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_DAV_START);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_START);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCLE_USER_SELF_BEFORE_SERVICE);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_APPOINT_TIME);
		
		
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_PING_JIA == orderStatusParam){
			
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT);
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISH_DV_FINISH);
			retList.add(OrderSkillConstants.ORDER_STATUS_GOING_USRE_APPAY_FINISH);
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME);
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISH_BOTH_NO_OPERATE);
		}
		
		return retList;
	}
	
	
	//大V方
	@Override
	public List<Integer> getReceiveOrderStatusList(Integer orderStatusParam) {
		List<Integer>  retList = new ArrayList<>();
		if(OrderSkillConstants.ORDER_STATUS_PARAM_TOTAL == orderStatusParam){
			retList = null ;
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_WAITING_RECEIVE_ORDER == orderStatusParam){
			retList.add(OrderSkillConstants.ORDER_STATUS_PARAM_WAITING_RECEIVE_ORDER);
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_WAITING_START == orderStatusParam){
			retList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE);
			retList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START);
			retList.add(OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START);
			
			
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_GOING == orderStatusParam){
			retList.add(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT);
			retList.add(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH);
			
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_FINISHED == orderStatusParam){
			
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISHED_AND_PINGJIA);
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT);
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISH_DV_FINISH);
			retList.add(OrderSkillConstants.ORDER_STATUS_GOING_USRE_APPAY_FINISH);
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME);
			retList.add(OrderSkillConstants.ORDER_STATUS_FINISH_BOTH_NO_OPERATE);
			
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_REFUSED == orderStatusParam){
			
			retList.add(OrderSkillConstants.ORDER_STATUS_DAV_REFUSED_RECEIVE);
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_CANCEL == orderStatusParam){
			
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_RECEIVE);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_SYSTEM_NOT_RECEIVE);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_DAV_START_ONE_ORDER);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_DAV_START);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_START);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCLE_USER_SELF_BEFORE_SERVICE);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT);
			retList.add(OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_APPOINT_TIME);
			
		}else if(OrderSkillConstants.ORDER_STATUS_PARAM_PING_JIA == orderStatusParam){
			retList.add(999);
			
		}
		
		return retList;
	}



	@Override
	public void finishUpdateOrder(Long orderId, Integer orderStatus, Date cancelTime,Integer  sendMsgIndex) {
		Order record = new Order();
		record.setOrderStatus(orderStatus);
		record.setOrderId(orderId);
		record.setModifyTime(new Date());
		record.setAppayEndTime(cancelTime);
		if(sendMsgIndex != null){
			record.setEndTime(cancelTime);
		}
		//修改订单状态为：已支付
		orderMapper.updateByPrimaryKeySelective(record);
		
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);


	@Override
	public Customer getPhoneByCustomerId(Long customerId) {
		String  custStr = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO+customerId) ;
		if(StringUtils.isNotBlank(custStr)){
			try {
				Customer customer = JSON.parseObject(custStr,  Customer.class);
				return customer;
				
			} catch (Exception e) {
				LOGGER.error("从Redis中获取客户信息发生异常，异常信息：",e);
			}
		}
		return null;
		
	}



	@Override
	public void custConfirmFinishUpdateOrder(Long orderId, Integer orderStatus) {
		Order record = new Order();
		record.setOrderStatus(orderStatus);
		record.setOrderId(orderId);
		record.setModifyTime(new Date());
		//用户同意大V服务完成，设置订单结束时间
		record.setEndTime(new Date());
		//修改订单状态为：已支付
		orderMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public String getMsgContent(String customerFlag, Integer orderStatus) {
		String  result =  null;
		if(OrderSkillConstants.MSG_CONTENT_DAV.equals(customerFlag)){
			//通知声优消息
			if(OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE ==  orderStatus){
				result =   OrderSkillConstants.GT_MSG_CONTENT_START_SERVICE_TO_DAV;
			//声优拒绝接单
			}else if (OrderSkillConstants.ORDER_STATUS_DAV_REFUSED_RECEIVE ==  orderStatus){
				result =  OrderSkillConstants.IM_MSG_CONTENT_DAV_REFUSE_TO_DV;
			//声优接单	
			}else if (OrderSkillConstants.ORDER_STATUS_WAITING_START ==  orderStatus){
				result =   OrderSkillConstants.IM_MSG_CONTENT_DAV_CONFIRM_TO_DAV;
			//用户取消订单
			}else if(OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_RECEIVE== orderStatus || orderStatus == OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_DAV_START
					||orderStatus == OrderSkillConstants.ORDER_STATUS_CANCLE_USER_SELF_BEFORE_SERVICE
					||orderStatus == OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_APPOINT_TIME){
				result =   OrderSkillConstants.IM_MSG_CONTENT_CANCEL_ORDER_TO_DV;
			//声优发起立即服务
			}else if(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE == orderStatus ){
				result =  OrderSkillConstants.IM_MSG_CONTENT_DAV_START_SERVICE_TO_DAV;
			//用户同意声优立即服务
			}else if(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT == orderStatus || OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START == orderStatus){
				result =   OrderSkillConstants.IM_MSG_CONTENT_USER_CONFIRM_START_SERVICE_TO_DAV;
			//声优在服务时间内发起完成服务
			}else if(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH == orderStatus){
				result =   OrderSkillConstants.IM_MSG_CONTENT_CUST_FINISH_TO_DAV;
			//声优在服务时间之外发起完成服务
			}else if (OrderSkillConstants.ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME == orderStatus){
				result =   OrderSkillConstants.IM_MSG_CONTENT_CUST_NOT_PING_JIA_TO_DV;
			//用户同意声优服务完成
			}else if(OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT == orderStatus){
				result =   OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_DV;
			}
			
		}else{
			//通知用户消息
			if(OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE ==  orderStatus){
				result =  OrderSkillConstants.GT_MSG_CONTENT_START_SERVICE_TO_CUST;
			//声优拒绝接单
			}else if (OrderSkillConstants.ORDER_STATUS_DAV_REFUSED_RECEIVE ==  orderStatus){
				result =  OrderSkillConstants.IM_MSG_CONTENT_DAV_REFUSE_TO_CUST;
			//声优接单	
			}else if (OrderSkillConstants.ORDER_STATUS_WAITING_START ==  orderStatus){
				result =  OrderSkillConstants.IM_MSG_CONTENT_DAV_CONFIRM_TO_CUST;
			//用户取消订单
			}else if(OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_RECEIVE== orderStatus || orderStatus == OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_DAV_START
					||orderStatus == OrderSkillConstants.ORDER_STATUS_CANCLE_USER_SELF_BEFORE_SERVICE
					||orderStatus == OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_APPOINT_TIME){
				result =   OrderSkillConstants.IM_MSG_CONTENT_CANCEL_ORDER_TO_CUST;
			//声优发起立即服务
			}else if(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE == orderStatus ){
				result =   OrderSkillConstants.IM_MSG_CONTENT_DAV_START_SERVICE_TO_CUST;
			//用户同意声优立即服务
			}else if(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT == orderStatus || OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START == orderStatus){
				result =  OrderSkillConstants.IM_MSG_CONTENT_USER_CONFIRM_START_SERVICE_TO_CUST;
			//声优在服务时间内发起完成服务
			}else if(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH == orderStatus){
				result =  OrderSkillConstants.IM_MSG_CONTENT_CUST_FINISH_TO_CUST;
			//声优在服务时间之外发起完成服务
			}else if (OrderSkillConstants.ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME == orderStatus){
				result =  OrderSkillConstants.IM_MSG_CONTENT_CUST_NOT_PING_JIA_TO_CUST;
			//用户同意声优服务完成	
			}else if(OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT == orderStatus){
				result =   OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_CUST;
			}
		}
		
		return result;
	}

}
