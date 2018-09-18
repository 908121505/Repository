package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.exchange.PayRequest;

/**
 * 接收支付宝回调实体类
 */
public class AlipayNotifyRequest extends PayRequest {
    String urlEncodeParam;
    String accountFunctionType;

    public String getAccountFunctionType() {
        return accountFunctionType;
    }

    public void setAccountFunctionType(String accountFunctionType) {
        this.accountFunctionType = accountFunctionType;
    }

    public String getUrlEncodeParam() {
        return urlEncodeParam;
    }

    public void setUrlEncodeParam(String urlEncodeParam) {
        this.urlEncodeParam = urlEncodeParam;
    }

    @Override
    public String getBizCode() {
        return this.accountFunctionType;
    }
}