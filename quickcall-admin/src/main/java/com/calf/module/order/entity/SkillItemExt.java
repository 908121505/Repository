package com.calf.module.order.entity;

import com.google.common.base.Objects;

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

    public SkillItemExt(){ }

    private Long id;

   
    private Long skillItemId;
    
    private Integer skillExtType;

    
    private Integer skillExtRange;

    
    private Integer skillExtUnit;

    
    private BigDecimal skillExtPrice;

    private Integer skillExtThreshold;
    
    private Integer skillExtStatus;
    
    private BigDecimal skillExtDiscont;

    
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
    public Integer getSkillExtThreshold() {
		return skillExtThreshold;
	}

	public void setSkillExtThreshold(Integer skillExtThreshold) {
		this.skillExtThreshold = skillExtThreshold;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillItemExt that = (SkillItemExt) o;
        return Objects.equal(skillItemId, that.skillItemId) &&
                Objects.equal(skillExtRange, that.skillExtRange) &&
                Objects.equal(skillExtUnit, that.skillExtUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(skillItemId, skillExtRange, skillExtUnit);
    }

    @Override
    public String toString() {
        return "SkillItemExt{" +
                "id=" + id +
                ", skillItemId=" + skillItemId +
                ", skillExtType=" + skillExtType +
                ", skillExtRange=" + skillExtRange +
                ", skillExtUnit=" + skillExtUnit +
                ", skillExtPrice=" + skillExtPrice +
                ", skillExtStatus=" + skillExtStatus +
                ", skillExtDiscont=" + skillExtDiscont +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", createMan='" + createMan + '\'' +
                ", modifyMan='" + modifyMan + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}