package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

import java.io.Serializable;

/**
 * @author xiangxianjin
 * @date 2018年10月21日 13点29分
 * descrption: 苹果内置支付回调确认
 */
public class ApplePurchaseRequest extends AbstractRequest implements Serializable {

	private static final long serialVersionUID = -2888905893019681375L;
	/**
	 * 充值单号
	 */
	private String orderId;
	/**
	 * 苹果支付交易编号
	 */
	private String tradeNo;
    /**
     * base64加密串
     */
	private String appReceipt;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getAppReceipt() {
		return appReceipt;
	}

	public void setAppReceipt(String appReceipt) {
		this.appReceipt = appReceipt;
	}

	@Override
	public String getBizCode() {
		return AccountFunctionType.APPLY_PAY_NOTIFY;
	}
}
