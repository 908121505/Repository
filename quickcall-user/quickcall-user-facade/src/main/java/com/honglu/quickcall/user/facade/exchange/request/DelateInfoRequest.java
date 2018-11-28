package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class DelateInfoRequest extends UserCenterRequest {


    @Override
    public String getBizCode() {
        return UserFunctionType.getAllDelate;
    }
}
