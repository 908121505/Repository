package com.calf.module.customer.entity;

import java.util.Date;

/**
 * 拉黑名单
 */
public class BlackList {

    private String id;//主键ID

    private String customerId;//客户ID

    private String blackCustomerId;//被拉黑的客户ID

    private Integer status;//删除状态(0=删除，1=可用)

    private String remark;//备注

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBlackCustomerId() {
        return blackCustomerId;
    }

    public void setBlackCustomerId(String blackCustomerId) {
        this.blackCustomerId = blackCustomerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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