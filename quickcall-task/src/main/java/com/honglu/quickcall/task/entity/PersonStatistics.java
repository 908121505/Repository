package com.honglu.quickcall.task.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class PersonStatistics {
    private Integer user_id;//用户id
    private Integer totalTime;//在线总时长
    private Integer yTotalCapital;//总金额
    private Integer sTotalCapital;//总金额
    private Integer yTotalTime;//语音总时间
    private Integer sTotalTime;//视频总时间
    private Integer yconnectNum;//语音接通次数
    private Integer yUnConnectNum;//语音未接通次数
    private Integer yRefuseNum;//语音拒绝次数
    private Integer sconnectNum;//视频接通次数
    private Integer sUnConnectNum;//视频未接通次数
    private Integer sRefuseNum;//视频拒绝次数
    private Date createTime;//时间
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	
	public Integer getyTotalCapital() {
		return yTotalCapital;
	}
	public void setyTotalCapital(Integer yTotalCapital) {
		this.yTotalCapital = yTotalCapital;
	}
	public Integer getsTotalCapital() {
		return sTotalCapital;
	}
	public void setsTotalCapital(Integer sTotalCapital) {
		this.sTotalCapital = sTotalCapital;
	}
	public Integer getyTotalTime() {
		return yTotalTime;
	}
	public void setyTotalTime(Integer yTotalTime) {
		this.yTotalTime = yTotalTime;
	}
	public Integer getsTotalTime() {
		return sTotalTime;
	}
	public void setsTotalTime(Integer sTotalTime) {
		this.sTotalTime = sTotalTime;
	}
	public Integer getYconnectNum() {
		return yconnectNum;
	}
	public void setYconnectNum(Integer yconnectNum) {
		this.yconnectNum = yconnectNum;
	}
	public Integer getyUnConnectNum() {
		return yUnConnectNum;
	}
	public void setyUnConnectNum(Integer yUnConnectNum) {
		this.yUnConnectNum = yUnConnectNum;
	}
	public Integer getyRefuseNum() {
		return yRefuseNum;
	}
	public void setyRefuseNum(Integer yRefuseNum) {
		this.yRefuseNum = yRefuseNum;
	}
	public Integer getSconnectNum() {
		return sconnectNum;
	}
	public void setSconnectNum(Integer sconnectNum) {
		this.sconnectNum = sconnectNum;
	}
	public Integer getsUnConnectNum() {
		return sUnConnectNum;
	}
	public void setsUnConnectNum(Integer sUnConnectNum) {
		this.sUnConnectNum = sUnConnectNum;
	}
	public Integer getsRefuseNum() {
		return sRefuseNum;
	}
	public void setsRefuseNum(Integer sRefuseNum) {
		this.sRefuseNum = sRefuseNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}