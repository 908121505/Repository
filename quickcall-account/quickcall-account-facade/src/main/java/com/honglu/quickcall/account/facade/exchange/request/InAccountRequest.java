package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.AccountOperatorTypeEnum;
import com.honglu.quickcall.common.api.exchange.AbstractPageRequest;

import java.math.BigDecimal;

/**
 * Created by len.song on 2017-12-18.
 */
public class InAccountRequest extends AbstractPageRequest {
    private AccountOperatorTypeEnum operatorType;                       //操作类型 参考：AccountOperatorTypeEnum
    private AccountBusinessTypeEnum businessType;                       //业务类型 参考：AccountBusinessTypeEnum
    private Integer userId;                                             //用户id
    private BigDecimal amount;                                          //金额 (所有的金额计算全是增加，如果是要扣钱，传输负数)

    @Override
    public String getBizCode() {
        return AccountFunctionType.inAccount;
    }

    public AccountOperatorTypeEnum getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(AccountOperatorTypeEnum operatorType) {
        this.operatorType = operatorType;
    }

    public AccountBusinessTypeEnum getBusinessType() {
        return businessType;
    }

    public void setBusinessType(AccountBusinessTypeEnum businessType) {
        this.businessType = businessType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
