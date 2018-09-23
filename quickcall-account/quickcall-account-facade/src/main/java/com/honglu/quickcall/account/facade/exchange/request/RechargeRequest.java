package com.honglu.quickcall.account.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class RechargeRequest extends AbstractRequest {
	private String remoteIp;//真实IP
    private String userId;//用户Id
    private BigDecimal amount;//支付金额
    
    
    
	public String getRemoteIp() {
		return remoteIp;
	}



	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return AccountFunctionType.AlipayRecharge;
	}

}
