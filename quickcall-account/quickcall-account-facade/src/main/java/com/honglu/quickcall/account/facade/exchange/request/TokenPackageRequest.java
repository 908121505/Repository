package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.exchange.PayRequest;

/**
 * Created by len.song on 2017-12-07.
 */
public class TokenPackageRequest extends PayRequest {
    
	private Integer type;//1为充值记录


	public Integer getType() {
		return type;
	}




	public void setType(Integer type) {
		this.type = type;
	}




	@Override
	public String getBizCode() {
		return AccountFunctionType.TokenPackage;
	}



}
