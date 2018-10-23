package com.honglu.quickcall.user.facade.entity;

import java.util.Date;

public class CustomerDelate {
    private Long id; //主键

    private Long customerId; //被举报人Id

    private Long delateCustId; //举报人Id

    private Integer delateId; //举报内容Id

    private Date createTime;

    private Date modifyTime;

    private String createMan;

    private String modifyMan;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getDelateCustId() {
        return delateCustId;
    }

    public void setDelateCustId(Long delateCustId) {
        this.delateCustId = delateCustId;
    }

    public Integer getDelateId() {
        return delateId;
    }

    public void setDelateId(Integer delateId) {
        this.delateId = delateId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
