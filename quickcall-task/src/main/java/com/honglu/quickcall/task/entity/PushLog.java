package com.honglu.quickcall.task.entity;

import java.util.Date;

public class PushLog {
    private String id;

    private String title;

    private String content;

    private Integer state;

    private Integer isTiming;

    private Date pushTime;

    private Integer pushTotalNum;

    private Integer pushSuccessNum;

    private Integer isReadNum;

    private String linkUrl;

    private String remark;

    private Date createTime;

    private String createMan;

    private Date modifyTime;

    private String modifyMan;

    private String alias;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsTiming() {
        return isTiming;
    }

    public void setIsTiming(Integer isTiming) {
        this.isTiming = isTiming;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getPushTotalNum() {
        return pushTotalNum;
    }

    public void setPushTotalNum(Integer pushTotalNum) {
        this.pushTotalNum = pushTotalNum;
    }

    public Integer getPushSuccessNum() {
        return pushSuccessNum;
    }

    public void setPushSuccessNum(Integer pushSuccessNum) {
        this.pushSuccessNum = pushSuccessNum;
    }

    public Integer getIsReadNum() {
        return isReadNum;
    }

    public void setIsReadNum(Integer isReadNum) {
        this.isReadNum = isReadNum;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }
}