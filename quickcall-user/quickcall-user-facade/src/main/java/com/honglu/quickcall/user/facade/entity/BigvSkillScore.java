package com.honglu.quickcall.user.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BigvSkillScore {

    private Long id;

    private Long customerId;

    private Long skillItemId;

    private Long customerSkillId;

    private Integer orderTotal;

    private BigDecimal scoreTotal;

    private Date createTime;

    private Date modifyTime;

    private String createMan;

    private String modifyMan;

    private String remark;

    /***********扩展查询字段************/

    /** 技能释放被预约 **/
    private Integer skillOrderd;

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

    public Long getSkillItemId() {
        return skillItemId;
    }

    public void setSkillItemId(Long skillItemId) {
        this.skillItemId = skillItemId;
    }

    public Long getCustomerSkillId() {
        return customerSkillId;
    }

    public void setCustomerSkillId(Long customerSkillId) {
        this.customerSkillId = customerSkillId;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public BigDecimal getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(BigDecimal scoreTotal) {
        this.scoreTotal = scoreTotal;
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

    public Integer getSkillOrderd() {
        return skillOrderd;
    }

    public void setSkillOrderd(Integer skillOrderd) {
        this.skillOrderd = skillOrderd;
    }
}