package com.honglu.quickcall.task.entity;

import java.util.Date;

public class CallRecords {
    private Integer id;

    private Integer persionId;

    private Integer acceptId;

    private Date createTime;

    private Date updateTime;

    private Integer state;

    private Integer callType;
    private Integer totalNum;

    public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersionId() {
        return persionId;
    }

    public void setPersionId(Integer persionId) {
        this.persionId = persionId;
    }

    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
        this.acceptId = acceptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
    }
}