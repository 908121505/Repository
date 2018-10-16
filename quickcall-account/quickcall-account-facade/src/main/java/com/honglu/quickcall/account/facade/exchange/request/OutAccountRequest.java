package com.honglu.quickcall.account.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class OutAccountRequest extends AbstractRequest {
	private Long userId;
	private BigDecimal amount;
	private Integer transferType;// 1 -可用金额 2-提现金额

	public Integer getTransferType() {
		return transferType;
	}

	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
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

	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return AccountFunctionType.OutAccount;
	}

}
