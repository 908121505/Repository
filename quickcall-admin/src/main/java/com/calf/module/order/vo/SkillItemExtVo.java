package com.calf.module.order.vo;

import java.math.BigDecimal;
import java.util.Date;

public class SkillItemExtVo {
    private Long id;

    private Long skillItemId;

    private Integer skillExtType;

    private Integer skillExtRange;

    private Integer skillExtUnit;

    private BigDecimal skillExtPrice;

    private Integer skillExtStatus;

    private BigDecimal skillExtDiscont;

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

    public Integer getSkillExtType() {
        return skillExtType;
    }

    public void setSkillExtType(Integer skillExtType) {
        this.skillExtType = skillExtType;
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

    public BigDecimal getSkillExtDiscont() {
        return skillExtDiscont;
    }

    public void setSkillExtDiscont(BigDecimal skillExtDiscont) {
        this.skillExtDiscont = skillExtDiscont;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
