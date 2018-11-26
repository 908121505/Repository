package com.calf.module.order.entity;

import java.math.BigDecimal;

public class Discount {
    private String id; //表的主键

    private String skillItemId; //关联的技能Id

    private BigDecimal discount; //折扣

    private String skillItemName; //技能名字

    private Integer skillExtStatus; //技能状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillItemId() {
        return skillItemId;
    }

    public void setSkillItemId(String skillItemId) {
        this.skillItemId = skillItemId;
    }

    public BigDecimal getDiscount() {
        if(discount!=null){
            return discount.multiply(new BigDecimal("10")).setScale(0);
        }
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getSkillItemName() {
        return skillItemName;
    }

    public void setSkillItemName(String skillItemName) {
        this.skillItemName = skillItemName;
    }

    public Integer getSkillExtStatus() {
        return skillExtStatus;
    }

    public void setSkillExtStatus(Integer skillExtStatus) {
        this.skillExtStatus = skillExtStatus;
    }
}
