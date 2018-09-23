package com.honglu.quickcall.account.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class AlipayNotifyRequest extends AbstractRequest {
	private String orderNo;//支付订单号
	private String phoneNum;
	private Integer payState;//支付状态 0失败 1成功
	private Long userId;//用户Id
	private BigDecimal amount;//支付金额
	private String extData;
	
	
	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public Integer getPayState() {
		return payState;
	}


	public void setPayState(Integer payState) {
		this.payState = payState;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getExtData() {
		return extData;
	}


	public void setExtData(String extData) {
		this.extData = extData;
	}


	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return AccountFunctionType.AlipayNotify;
	}

}
