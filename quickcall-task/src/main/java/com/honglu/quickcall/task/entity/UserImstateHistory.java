package com.honglu.quickcall.task.entity;

import java.util.Date;

public class UserImstateHistory {
    private Integer imChangeId;

    private Integer userId;

    private Integer fromState;

    private Integer toState;

    private Date createTime;

    private String desc;

    private String remark;

    private Integer oltime;
    private Integer totalTime;

    public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getImChangeId() {
        return imChangeId;
    }

    public void setImChangeId(Integer imChangeId) {
        this.imChangeId = imChangeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFromState() {
        return fromState;
    }

    public void setFromState(Integer fromState) {
        this.fromState = fromState;
    }

    public Integer getToState() {
        return toState;
    }

    public void setToState(Integer toState) {
        this.toState = toState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOltime() {
        return oltime;
    }

    public void setOltime(Integer oltime) {
        this.oltime = oltime;
    }
}