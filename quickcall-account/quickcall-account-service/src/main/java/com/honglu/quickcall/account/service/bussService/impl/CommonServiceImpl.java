package com.honglu.quickcall.account.service.bussService.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.entity.Skill;
import com.honglu.quickcall.account.facade.vo.OrderTempResponseVO;
import com.honglu.quickcall.account.service.bussService.CommonService;
import com.honglu.quickcall.account.service.dao.OrderMapper;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.user.facade.business.UserPushAppMsgBusiness;
import com.honglu.quickcall.user.facade.enums.PushAppMsgTypeEnum;


@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Autowired
	private  UserPushAppMsgBusiness  userPushAppMsgBusiness ;
	@Autowired
	private OrderMapper  orderMapper;
	
	public CommonResponse  getCommonResponse(){
		 CommonResponse commonResponse = new CommonResponse();
		 commonResponse.setCode(BizCode.Success);
		 commonResponse.setMessage(BizCode.Success.desc());
		 return commonResponse;
	}

	@Override
	public List<BigDecimal> getPriceList(Skill skill) {
		List<BigDecimal>   retList =  new  ArrayList<BigDecimal>();
		BigDecimal  maxPrice = skill.getMaxPrice();
		BigDecimal  minPrice = skill.getMinPrice();
		BigDecimal  priceStep = skill.getPriceStep();
		
		retList.add(minPrice);
		BigDecimal   tempPrice =  minPrice;
		for (;;) {
			tempPrice =  tempPrice.add(priceStep);
			if(tempPrice.compareTo(maxPrice) <= 0){
				retList.add(tempPrice);
			}else{
				break;
			}
		}
		return retList ;
	}

	@Override
	public void pushMessage(PushAppMsgTypeEnum msgType,Long  sellerId,Long  customerId) {
//		PushAppMsgRequest  msgRequest =  new PushAppMsgRequest();
//		msgRequest.setMsgType(PushAppMsgTypeEnum.NEW_ORDER);
//		msgRequest.setReceiverId(sellerId);
//		msgRequest.setSenderId(customerId);
//		//发送消息
//		userPushAppMsgBusiness.excute(msgRequest);
		
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

	@Override
	public OrderTempResponseVO getCountDownSeconds(Integer   oldOrderStatus ,Date  orderTime,Date  receiveOrderTime) {
		OrderTempResponseVO  tempVO = new OrderTempResponseVO();
		Calendar  cal =  Calendar.getInstance();
		Date  currTime = cal.getTime();
		Long  countDownSeconds = null ;
		Date  endTime = null ;
		//待接单计算倒计时
		if(oldOrderStatus == OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE){
			Calendar  cal1 =  Calendar.getInstance();
			cal1.setTime(orderTime);
			cal1.add(Calendar.MINUTE, 15);//TODO 定义常量
			endTime = cal1.getTime();
			countDownSeconds =  DateUtils.getDiffSeconds(currTime, endTime);
			//大于0说明正在倒计时，状态不变
			if(countDownSeconds > 0){
				tempVO.setOrderStatus(oldOrderStatus);
				tempVO.setCountDownSeconds(countDownSeconds);
			}else{
				tempVO.setOrderStatus(OrderSkillConstants.ORDER_STATUS_CANCEL_SYSTEM_NOT_RECEIVE);
			}
			//大V未发起服务或者已经发起服务，用户未应答
		}else if(oldOrderStatus == OrderSkillConstants.ORDER_STATUS_WAITING_START  || oldOrderStatus == OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE){
			Calendar  cal1 =  Calendar.getInstance();
			cal1.setTime(receiveOrderTime);
			cal1.add(Calendar.MINUTE, 5);//TODO 定义常量
			endTime = cal1.getTime();
			countDownSeconds =  DateUtils.getDiffSeconds(currTime, endTime);
			//大于0说明正在倒计时，状态不变
			if(countDownSeconds > 0){
				tempVO.setOrderStatus(oldOrderStatus);
				tempVO.setCountDownSeconds(countDownSeconds);
			}else{
				//小于O说明真实状态已经变更
				Integer  newOrderStatus = null ;
				if(oldOrderStatus == OrderSkillConstants.ORDER_STATUS_WAITING_START ){
					newOrderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_START ;
				}else if (oldOrderStatus == OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE){
					newOrderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT ;
				}
				tempVO.setOrderStatus(newOrderStatus);
			}
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

}
