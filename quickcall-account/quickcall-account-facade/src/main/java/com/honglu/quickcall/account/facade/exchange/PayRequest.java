package com.honglu.quickcall.account.facade.exchange;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付宝充值请求类
 */
public abstract class PayRequest extends AbstractRequest {
    String ActivityTaskId;

    public String getActivityTaskId() {
        return ActivityTaskId;
    }

    public void setActivityTaskId(String activityTaskId) {
        ActivityTaskId = activityTaskId;
    }
}
