package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;

public class UnifiedOrderRequest extends AbstractRequest {
    private Integer userId;
    private Integer payAmount;
    private String payType;
    private String activityId;
    private String accountFunctionType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

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
