package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

import java.math.BigDecimal;

/**
 * Created by len.song on 2018-03-07.
 */
public class GetRechargeByAmountRequest extends AbstractRequest {
    private BigDecimal amount;

    @Override
    public String getBizCode() {
        return AccountFunctionType.GetRechargeByAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
