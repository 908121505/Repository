package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class AccountNotParamsRequest extends AbstractRequest {
    //账户操作类型
    private String accountFunctionType;

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
