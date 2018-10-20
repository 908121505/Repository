package com.honglu.quickcall.account.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：技能项扩展信息
 * @Package: com.honglu.quickcall.account.facade.entity 
 * @author: chenliuguang   
 * @date: 2018年10月20日 下午2:44:42
 */
public class SkillItemExt {
   
    private Integer id;

   
    private Long skillItemId;
    
    private Integer skillExtType;

    
    private Integer skillExtRange;

    
    private Integer skillExtUnit;

    
    private BigDecimal skillExtPrice;

    
    private Integer skillExtStatus;
    
    private BigDecimal skillExtDiscont;

    
    private Date createTime;

    
    private Date modifyTime;

   
    private String createMan;

    
    private String modifyMan;

    
    private String remark;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

   
    public Long getSkillItemId() {
        return skillItemId;
    }

    
    public void setSkillItemId(Long skillItemId) {
        this.skillItemId = skillItemId;
    }

    
    public Integer getSkillExtRange() {
        return skillExtRange;
    }

    
    public void setSkillExtRange(Integer skillExtRange) {
        this.skillExtRange = skillExtRange;
    }

 
    public Integer getSkillExtUnit() {
        return skillExtUnit;
    }

    
    public void setSkillExtUnit(Integer skillExtUnit) {
        this.skillExtUnit = skillExtUnit;
    }

    
    public BigDecimal getSkillExtPrice() {
        return skillExtPrice;
    }

   
    public void setSkillExtPrice(BigDecimal skillExtPrice) {
        this.skillExtPrice = skillExtPrice;
    }

    
    public Integer getSkillExtStatus() {
        return skillExtStatus;
    }

    
    public void setSkillExtStatus(Integer skillExtStatus) {
        this.skillExtStatus = skillExtStatus;
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
        this.createMan = createMan == null ? null : createMan.trim();
    }

    
    public String getModifyMan() {
        return modifyMan;
    }

   
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    public BigDecimal getSkillExtDiscont() {
        return skillExtDiscont;
    }

    public void setSkillExtDiscont(BigDecimal skillExtDiscont) {
        this.skillExtDiscont = skillExtDiscont;
    }
    
    public Integer getSkillExtType() {
        return skillExtType;
    }

    public void setSkillExtType(Integer skillExtType) {
        this.skillExtType = skillExtType;
    }
}