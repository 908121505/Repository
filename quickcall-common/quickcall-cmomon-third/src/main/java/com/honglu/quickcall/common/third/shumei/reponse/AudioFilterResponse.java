package com.honglu.quickcall.common.third.shumei.reponse;

import java.io.Serializable;

public class AudioFilterResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5814307353462485201L;
	
	private String code;
	
	private String requestId;
	private String score;
	private String riskLevel;
	private String detail;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Override
	public String toString() {
		return "AudioFilterResponse [code=" + code + ", requestId=" + requestId + ", score=" + score + ", riskLevel="
				+ riskLevel + ", detail=" + detail + "]";
	}
	
	
	
}
