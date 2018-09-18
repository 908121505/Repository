package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2017-12-25.
 */
public class PaySuccessMqRequest extends AbstractRequest {
    @Override
    public String getBizCode() {
        return AccountFunctionType.PaySuccessMqTest;
    }
}
