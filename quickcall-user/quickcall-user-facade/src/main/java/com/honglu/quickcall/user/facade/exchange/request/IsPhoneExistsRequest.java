package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Created by len.song on 2017-12-07.
 */
public class IsPhoneExistsRequest extends UserCenterRequest {
    private String tel; //电话
    private String wechatOpenId;//微信 唯一标识

    private String qqOpenId;//QQ唯一标识

    private String microblogOpenId;//微博唯一标识
    
   
    public String getWechatOpenId() {
		return wechatOpenId;
	}

	public void setWechatOpenId(String wechatOpenId) {
		this.wechatOpenId = wechatOpenId;
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	public String getMicroblogOpenId() {
		return microblogOpenId;
	}

	public void setMicroblogOpenId(String microblogOpenId) {
		this.microblogOpenId = microblogOpenId;
	}


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
