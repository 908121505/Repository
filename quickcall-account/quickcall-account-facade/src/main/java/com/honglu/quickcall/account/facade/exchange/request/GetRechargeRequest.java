package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.exchange.PayRequest;

/**
 * Created by len.song on 2017-12-07.
 */
public class GetRechargeRequest extends PayRequest {
    
	private Integer persion_id;
	private Integer type;//1为充值记录




	public Integer getPersion_id() {
		return persion_id;
	}




	public void setPersion_id(Integer persion_id) {
		this.persion_id = persion_id;
	}




	public Integer getType() {
		return type;
	}




	public void setType(Integer type) {
		this.type = type;
	}




	@Override
	public String getBizCode() {
		return AccountFunctionType.GetRecharge;
	}



}
