package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;


/**
 * Created by len.song on 2017-12-16.
 */
public class CreateUserAccountRequest extends AbstractRequest {
    private Long userId;                             //用户id

    @Override
    public String getBizCode() {
        return AccountFunctionType.CreateUserAccount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}


