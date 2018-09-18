package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Created by len.song on 2017-12-07.
 */
public class IsPhoneExistsRequest extends UserCenterRequest {
    private String tel;
   
    public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
    public String getBizCode() {
        return UserFunctionType.CheckPhone;
    }
}
