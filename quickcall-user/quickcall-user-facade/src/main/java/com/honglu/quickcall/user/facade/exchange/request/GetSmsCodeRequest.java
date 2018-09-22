package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.user.facade.code.UserFunctionType;

public class GetSmsCodeRequest  extends AbstractRequest{

	private String tel;
	private String codeType;
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return UserFunctionType.getSmsCode;
	}

}
