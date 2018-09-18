package com.honglu.quickcall.account.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2017-12-07.
 */
public class applicationWithDrawRequest extends AbstractRequest {
    
	private Integer pid;
	private BigDecimal money;
	private String code;
	private String tel;

 

	public String getTel() {
		return tel;
	}




	public void setTel(String tel) {
		this.tel = tel;
	}




	public String getCode() {
		return code;
	}




	public void setCode(String code) {
		this.code = code;
	}




	public Integer getPid() {
		return pid;
	}




	public void setPid(Integer pid) {
		this.pid = pid;
	}













	public BigDecimal getMoney() {
		return money;
	}




	public void setMoney(BigDecimal money) {
		this.money = money;
	}




	@Override
    public String getBizCode() {
        return AccountFunctionType.applicationWithDraw;
    }
}
