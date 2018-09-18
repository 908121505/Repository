package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2018-01-18.
 */
public class QueryFamilyAccountRequest extends AbstractRequest{
    private Integer familyId;               //家族id

    public QueryFamilyAccountRequest(Integer familyId){
        this.familyId = familyId;
    }

    @Override
    public String getBizCode() {
        return AccountFunctionType.queryFamilyAccount;
    }

    public Integer getFamilyId() {
        return familyId;
    }
}
