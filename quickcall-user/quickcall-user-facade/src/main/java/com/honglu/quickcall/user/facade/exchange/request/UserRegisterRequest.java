package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class UserRegisterRequest  extends UserCenterRequest{
	private String tel; //电话
    private String wechatOpenId;//微信 唯一标识

    private String qqOpenId;//QQ唯一标识

    private String microblogOpenId;//微博唯一标识
    
    private String verifyCode;
    
    private String codeType;
    
    private String heardUrl;//第三方头像
    
    private String nickName;//第三方昵称
    
    private Integer scource;
	private String deviceNo;
	private String appChannelName;
    
    
	public Integer getScource() {
		return scource;
	}


	public void setScource(Integer scource) {
		this.scource = scource;
	}


	public String getDeviceNo() {
		return deviceNo;
	}


	public void setDevice_no(String deviceNo) {
		this.deviceNo = deviceNo;
	}


	public String getAppChannelName() {
		return appChannelName;
	}


	public void setAppChannelName(String appChannelName) {
		this.appChannelName = appChannelName;
	}


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


	public String getHeardUrl() {
		return heardUrl;
	}


	public void setHeardUrl(String heardUrl) {
		this.heardUrl = heardUrl;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	@Override
	public String getBizCode() {
		// TODO Auto-generated method stub
		return UserFunctionType.register;
	}

}
