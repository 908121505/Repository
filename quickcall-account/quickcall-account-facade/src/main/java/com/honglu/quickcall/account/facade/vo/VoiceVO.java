package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：大V声音信息vo
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月15日 下午3:20:39
 */
public class VoiceVO {
    
    private Integer voiceStatus;
    private String  voiceUrl;
    /**已设置金额*/
    private BigDecimal  voiceTime;

	public Integer getVoiceStatus() {
		return voiceStatus;
	}

	public void setVoiceStatus(Integer voiceStatus) {
		this.voiceStatus = voiceStatus;
	}

	public String getVoiceUrl() {
		return voiceUrl;
	}

	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}

	public BigDecimal getVoiceTime() {
		return voiceTime;
	}

	public void setVoiceTime(BigDecimal voiceTime) {
		this.voiceTime = voiceTime;
	}
    
   
}