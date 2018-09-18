package com.honglu.quickcall.task.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BalanceOfPayment {
    private Integer id;

    private Integer persionId;

    private Integer type;

    private Integer token;
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Date finishDate;

    private Integer state;

    private Integer present;

    private Integer chat;

    private Integer otherId;

    private Double capital;

    private Integer charm;

    private String rongOrderId;

    private Double adminget;

    private Integer fId;

    private Double fRate;

    private Integer sId;

    private Double sRate;

    private Integer tId;

    private Double tRate;

    private Integer grade;

    private Integer aFlag;
    private Integer totalCapital;
    private Integer totalTime;
    private Integer total_number;

    public Integer getTotal_number() {
        return total_number;
    }

    public void setTotal_number(Integer total_number) {
        this.total_number = total_number;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPresent() {
        return present;
    }

    public void setPresent(Integer present) {
        this.present = present;
    }

    public Integer getChat() {
        return chat;
    }

    public void setChat(Integer chat) {
        this.chat = chat;
    }

    public Integer getOtherId() {
        return otherId;
    }

    public void setOtherId(Integer otherId) {
        this.otherId = otherId;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Integer getCharm() {
        return charm;
    }

    public void setCharm(Integer charm) {
        this.charm = charm;
    }

    public String getRongOrderId() {
        return rongOrderId;
    }

    public void setRongOrderId(String rongOrderId) {
        this.rongOrderId = rongOrderId == null ? null : rongOrderId.trim();
    }

    public Double getAdminget() {
        return adminget;
    }

    public void setAdminget(Double adminget) {
        this.adminget = adminget;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Double getfRate() {
        return fRate;
    }

    public void setfRate(Double fRate) {
        this.fRate = fRate;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Double getsRate() {
        return sRate;
    }

    public void setsRate(Double sRate) {
        this.sRate = sRate;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Double gettRate() {
        return tRate;
    }

    public void settRate(Double tRate) {
        this.tRate = tRate;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getaFlag() {
        return aFlag;
    }

    public void setaFlag(Integer aFlag) {
        this.aFlag = aFlag;
    }

	public Integer getTotalCapital() {
		return totalCapital;
	}

	public void setTotalCapital(Integer totalCapital) {
		this.totalCapital = totalCapital;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	
}