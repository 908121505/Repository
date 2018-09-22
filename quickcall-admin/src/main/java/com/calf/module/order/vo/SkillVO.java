package com.calf.module.order.vo;

import java.math.BigDecimal;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：技能参数
 * @Package: com.calf.module.order.vo 
 * @author: chenliuguang   
 * @date: 2018年9月21日 下午3:55:12
 */
public class SkillVO {


    private String name;
    private String skillDescribe;
    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String imageUrl;

    private Short priceStep;
    private Byte skillStatus;

    private String remark;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSkillDescribe() {
        return skillDescribe;
    }

    public void setSkillDescribe(String skillDescribe) {
        this.skillDescribe = skillDescribe == null ? null : skillDescribe.trim();
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Short getPriceStep() {
        return priceStep;
    }

    public void setPriceStep(Short priceStep) {
        this.priceStep = priceStep;
    }

    public Byte getSkillStatus() {
        return skillStatus;
    }

    public void setSkillStatus(Byte skillStatus) {
        this.skillStatus = skillStatus;
    }


    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
	
}