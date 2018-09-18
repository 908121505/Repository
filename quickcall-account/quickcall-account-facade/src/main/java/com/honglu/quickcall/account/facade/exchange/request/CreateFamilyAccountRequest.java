package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2018-01-18.
 */
public class CreateFamilyAccountRequest extends AbstractRequest{
    private Integer familyId;              //家族编号

    @Override
    public String getBizCode() {
        return AccountFunctionType.createFamilyAccount;
    }

    public CreateFamilyAccountRequest(Integer familyId){
        this.familyId = familyId;
    }

    public Integer getFamilyId() {
        return familyId;
    }
}
