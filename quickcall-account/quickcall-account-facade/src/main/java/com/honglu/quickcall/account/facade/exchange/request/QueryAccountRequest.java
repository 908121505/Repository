package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2017-12-18.
 */
public class QueryAccountRequest extends AbstractRequest {
    private Integer userId;                 //用户id

    @Override
    public String getBizCode() {
        return AccountFunctionType.QueryAccount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
