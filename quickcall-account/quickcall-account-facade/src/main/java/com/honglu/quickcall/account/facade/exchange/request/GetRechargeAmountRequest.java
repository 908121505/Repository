package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2018-03-06.
 */
public class GetRechargeAmountRequest extends AbstractRequest {
    private Integer userId;                     //当前登录用户id

    @Override
    public String getBizCode() {
        return AccountFunctionType.GetRechargeAmountRequest;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
