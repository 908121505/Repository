package com.calf.module.resource.entity;

import java.util.Date;
/**
 * 资源池表
 */
public class ResourcePool {
    private String id;//ID

    private String resourceName;//资源池名称

    private Integer soundTotal;//声优总量

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