package com.honglu.quickcall.task.entity;

import java.util.Date;

public class Profit {
    private Integer id;

    private Integer level;

    private Integer servicechargeVoice;

    private Integer servicechargeVideo;

    private Double proportion;

    private Integer upper;

    private Integer floor;

    private Date createtime;

    private String operation;

    private Date updatetime;

    private Integer love;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getServicechargeVoice() {
        return servicechargeVoice;
    }

    public void setServicechargeVoice(Integer servicechargeVoice) {
        this.servicechargeVoice = servicechargeVoice;
    }

    public Integer getServicechargeVideo() {
        return servicechargeVideo;
    }

    public void setServicechargeVideo(Integer servicechargeVideo) {
        this.servicechargeVideo = servicechargeVideo;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Integer getUpper() {
        return upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}