package com.honglu.quickcall.account.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class InAccountRequest extends AbstractRequest {
      private Long userId;
      private BigDecimal amount;
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
	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return AccountFunctionType.InAccount;
	}

}
