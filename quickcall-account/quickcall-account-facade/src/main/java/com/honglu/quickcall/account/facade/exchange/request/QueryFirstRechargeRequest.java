package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2017-12-18.
 */
public class QueryFirstRechargeRequest extends AbstractRequest {
    private Integer userId;                 //用户id

    @Override
    public String getBizCode() {
        return AccountFunctionType.QueryFirstRecharge;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public QueryFirstRechargeRequest(Integer userId) {
		super();
		this.userId = userId;
	}

	public QueryFirstRechargeRequest() {
		super();
	}
	
    
}
