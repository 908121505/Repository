package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2018-01-17.
 */
public class GetWomanInComeProportionRequest extends AbstractRequest{
    private Integer companyId;              //经济家族编号
    private Integer level;                  //级别

    @Override
    public String getBizCode() {
        return AccountFunctionType.WomanInComeProportion;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
