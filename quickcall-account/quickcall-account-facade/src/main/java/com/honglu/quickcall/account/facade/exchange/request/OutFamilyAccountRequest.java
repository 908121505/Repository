package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.AccountOperatorTypeEnum;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

import java.math.BigDecimal;

/**
 * Created by len.song on 2018-01-18.
 */
public class OutFamilyAccountRequest extends AbstractRequest{
    private AccountOperatorTypeEnum operatorType;                       //操作类型 参考：AccountOperatorTypeEnum
    private AccountBusinessTypeEnum businessType;                       //业务类型 参考：AccountBusinessTypeEnum
    private Integer familyId;                                           //家族编号id
    private BigDecimal amount;                                          //金额 (所有的金额计算全是增加，如果是要扣钱，传输负数)

    public OutFamilyAccountRequest(AccountOperatorTypeEnum operatorType,AccountBusinessTypeEnum businessType,Integer familyId,BigDecimal amount){
        this.operatorType = operatorType;
        this.businessType = businessType;
        this.familyId = familyId;
        this.amount = amount;
    }

    public AccountOperatorTypeEnum getOperatorType() {
        return operatorType;
    }

    public AccountBusinessTypeEnum getBusinessType() {
        return businessType;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String getBizCode() {
        return AccountFunctionType.familyOutAccount;
    }
}
