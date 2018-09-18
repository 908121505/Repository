package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class ApplePayNotifyRequest extends AbstractRequest {
    private String payOrderNo;
    private String receiptData;
    private String accountFunctionType;

    public ApplePayNotifyRequest() {
    }

    public ApplePayNotifyRequest(String payOrderNo, String receiptData, String accountFunctionType) {
        this.payOrderNo = payOrderNo;
        this.receiptData = receiptData;
        this.accountFunctionType = accountFunctionType;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getReceiptData() {
        return receiptData;
    }

    public void setReceiptData(String receiptData) {
        this.receiptData = receiptData;
    }

    public String getAccountFunctionType() {
        return accountFunctionType;
    }

    public void setAccountFunctionType(String accountFunctionType) {
        this.accountFunctionType = accountFunctionType;
    }

    @Override
    public String getBizCode() {
        return accountFunctionType;
    }
}
