package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.entity.Skill;
import com.honglu.quickcall.account.service.dao.OrderMapper;
import com.honglu.quickcall.account.service.service.CommonService;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
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
	public void updateOrder(Long orderId, Integer orderStatus,String  refundReason) {
		Order record = new Order();
		record.setOrderStatus(orderStatus);
		record.setOrderId(orderId);
		record.setModifyTime(new Date());
		if(StringUtils.isNotBlank(refundReason)){
			record.setRefundReason(refundReason);
		}
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

}
