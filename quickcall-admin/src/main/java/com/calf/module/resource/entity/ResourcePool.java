package com.calf.module.resource.entity;

import java.util.Date;

public class ResourcePool {
    private String id;//ID

    private String resourceName;//资源池名称

    private Integer soundTotal;//声优总量

    private String soundTotalUIDStr;//声优总量UID

/*    private Integer soundOnline;//在线声优

    private Integer soundOrder;//已接单声优

    private Integer soundRemaining;//剩余声优*/

    private Integer status;//删除状态(0=不可用，1=可用)

    private Integer activeStatus;//资源位正在使用则无法删除，默认0=不可删除，1=可删除

    private Date createTime;

    private Date modifyTime;

    private String createMan;

    private String modifyMan;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getSoundTotal() {
        return soundTotal;
    }

    public void setSoundTotal(Integer soundTotal) {
        this.soundTotal = soundTotal;
    }

    public String getSoundTotalUIDStr() {
        return soundTotalUIDStr;
    }

    public void setSoundTotalUIDStr(String soundTotalUIDStr) {
        this.soundTotalUIDStr = soundTotalUIDStr;
    }

/*   public Integer getSoundOnline() {
        return soundOnline;
    }

    public void setSoundOnline(Integer soundOnline) {
        this.soundOnline = soundOnline;
    }

    public Integer getSoundOrder() {
        return soundOrder;
    }

    public void setSoundOrder(Integer soundOrder) {
        this.soundOrder = soundOrder;
    }

    public Integer getSoundRemaining() {
        return soundRemaining;
    }

    public void setSoundRemaining(Integer soundRemaining) {
        this.soundRemaining = soundRemaining;
    }*/

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan;
    }
}