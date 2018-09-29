package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class UserLoginRequest  extends UserCenterRequest{

	private String tel; //电话
    private String wechatOpenId;//微信 唯一标识

    private String qqOpenId;//QQ唯一标识

    private String microblogOpenId;//微博唯一标识
    
    private String passWord;
    
    private String verifyCode;
    
    private String codeType;
	
	
	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


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


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public String getVerifyCode() {
		return verifyCode;
	}


	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}


	public String getCodeType() {
		return codeType;
	}


	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}


	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return UserFunctionType.login;
	}

}
