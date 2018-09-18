package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.exchange.PayRequest;

public class WechatPayNotifyRequest extends PayRequest {
    String wechatParamMap;

    public String getWechatParamMap() {
        return wechatParamMap;
    }

    public void setWechatParamMap(String wechatParamMap) {
        this.wechatParamMap = wechatParamMap;
    }

    @Override
    public String getBizCode() {
        return AccountFunctionType.WechatNotify;
    }
}
