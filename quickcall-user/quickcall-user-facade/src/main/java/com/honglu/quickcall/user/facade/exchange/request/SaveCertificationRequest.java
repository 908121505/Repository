package com.honglu.quickcall.user.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 保存认证信息请求对象
 *
 * @author duanjun
 * @date 2018-09-23 12:10
 */
public class SaveCertificationRequest extends UserCenterRequest {

	private static final long serialVersionUID = 8422040350133766310L;

	/**
     * 登录用户id
     */
    private Long customerId;

    /**
     * 认证类型：0上传身份证照片；1身份认证；2大V认证
     */
    private Integer certifyType;

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 证件类型
     */
    private Integer credentialsType;
    /**
     * 证件号码
     */
    private String credentialsNum;
    /**
     * 身份证正面URL
     */
    private String frontPortraitUrl;
    /**
     * 身份证反面URL
     */
    private String backPortraitUrl;
    /**
     * 大V认证上传的声音URL
     */
    private String voiceUrl;
    /**认证声音时长*/
    private BigDecimal  voiceTime;
    
    

    public BigDecimal getVoiceTime() {
		return voiceTime;
	}

	public void setVoiceTime(BigDecimal voiceTime) {
		this.voiceTime = voiceTime;
	}

	public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getCertifyType() {
        return certifyType;
    }

    public void setCertifyType(Integer certifyType) {
        this.certifyType = certifyType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public String getFrontPortraitUrl() {
        return frontPortraitUrl;
    }

    public void setFrontPortraitUrl(String frontPortraitUrl) {
        this.frontPortraitUrl = frontPortraitUrl;
    }

    public String getBackPortraitUrl() {
        return backPortraitUrl;
    }

    public void setBackPortraitUrl(String backPortraitUrl) {
        this.backPortraitUrl = backPortraitUrl;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.SAVE_USER_CERTIFY_INFO;
    }
}
