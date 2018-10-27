package com.honglu.quickcall.user.facade.entity;

import java.util.Date;

/**
 * 资源配置数据库对象
 *
 * @author duanjun
 * @date 2018-10-25 14:24
 */
public class ResourceConfig {

    private Integer resourceConfigId;

    private Integer configNum;

    private Integer strategy;

    private Long resourcePoolId;

    private Date createTime;

    private Date modifyTime;

    private String createMan;

    private String modifyMan;

    private String remark;

    public Integer getResourceConfigId() {
        return resourceConfigId;
    }

    public void setResourceConfigId(Integer resourceConfigId) {
        this.resourceConfigId = resourceConfigId;
    }

    public Integer getConfigNum() {
        return configNum;
    }

    public void setConfigNum(Integer configNum) {
        this.configNum = configNum;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    public Long getResourcePoolId() {
        return resourcePoolId;
    }

    public void setResourcePoolId(Long resourcePoolId) {
        this.resourcePoolId = resourcePoolId;
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