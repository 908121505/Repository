package com.honglu.quickcall.user.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：大V声音设置
 * @Package: com.honglu.quickcall.user.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年10月12日 下午4:20:33 
 */
public class SaveDvVoiceRequest extends UserCenterRequest {


	private static final long serialVersionUID = 8411604225895637218L;


	/**
     * 登录用户id
     */
    private Long customerId;

    
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

   

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.SAVE_DV_VOICE_INFO;
    }
}
