package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2018-01-18.
 */
public class GetEconomyFamilyRulesRequest extends AbstractRequest {
    private Integer companyId;              //经济家族编号
    private Integer level;                  //级别

    public GetEconomyFamilyRulesRequest(Integer companyId, Integer level){
        this.companyId = companyId;
        this.level = level;
    }

    @Override
    public String getBizCode() {
        return AccountFunctionType.queryEconomyFamilyRules;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public Integer getLevel() {
        return level;
    }
}
