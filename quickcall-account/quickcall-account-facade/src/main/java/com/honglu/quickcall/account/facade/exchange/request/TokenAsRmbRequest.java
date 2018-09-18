package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.AccountOperatorTypeEnum;
import com.honglu.quickcall.common.api.exchange.AbstractPageRequest;

import java.math.BigDecimal;

/**
 * Created by len.song on 2017-12-18.
 */
public class TokenAsRmbRequest extends AbstractPageRequest {

    @Override
    public String getBizCode() {
        return AccountFunctionType.TokenAsRmb;
    }
}
