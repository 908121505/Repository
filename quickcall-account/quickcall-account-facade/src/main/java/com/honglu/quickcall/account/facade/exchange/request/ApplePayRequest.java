package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xiangxianjin
 * @date 2018年10月21日 13点29分
 * descrption: 苹果内置支付
 */
public class ApplePayRequest extends AbstractRequest implements Serializable {

	private static final long serialVersionUID = 7981525573588005933L;

	/**
	 * 客户编号
	 */
	private Long customerId;
	/**
	 * 支付金额
	 */
	private BigDecimal amount;
	/**
	 * 真实IP
	 */
	private String remoteIp;

	/**
	 * 业务参数签名加密
	 */
	private String bizSign;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getBizSign() {
		return bizSign;
	}

	public void setBizSign(String bizSign) {
		this.bizSign = bizSign;
	}

	@Override
	public String getBizCode() {
		return AccountFunctionType.APPLY_PAY_RECHARGE;
	}
}
