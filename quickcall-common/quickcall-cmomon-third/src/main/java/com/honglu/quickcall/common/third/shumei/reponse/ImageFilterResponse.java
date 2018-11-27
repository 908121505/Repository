package com.honglu.quickcall.common.third.shumei.reponse;

import java.io.Serializable;

public class ImageFilterResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1699638884838064524L;
	/**
	 * 请求唯一标识，后续可用于数据查询
	 */
	private String requestId;
	/**
	 * 任务编号，唯一标识该次图片审核任务
	 */
	private String taskId;
	
	/**
	 * 用户指定的图片标识
	 */
	
	private String btId;
	
	/**
	 * 风险分数
	 */
	private String score;
	/**
	 * 风险级别
	 */
	private String riskLevel;
	/**
	 * 风险详情
	 */
	private String detail;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getBtId() {
		return btId;
	}
	public void setBtId(String btId) {
		this.btId = btId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ImageFilterResponse [requestId=" + requestId + ", taskId=" + taskId + ", btId=" + btId + ", score="
				+ score + ", riskLevel=" + riskLevel + ", detail=" + detail + "]";
	}
	
	
}
