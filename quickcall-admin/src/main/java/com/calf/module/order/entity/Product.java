package com.calf.module.order.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：服务方技能实体
 * @Package: com.calf.module.order.entity 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午6:17:17
 */
public class Product {
	private String productId;
	private String skillId;
    private String name;
    private BigDecimal price;
    private Integer serviceTime;
    private BigDecimal preferentialPrice;
    private BigDecimal discountRate;
    private BigDecimal discountPrice;
    private Integer type;
    private Integer productStatus;
    private String sellerId;
    private String productDescribe;
    private Date createTime;
    private Date modifyTime;
    private String createMan;
    private String modifyMan;
    private String remark;
    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
    public Integer getServiceTime() {
        return serviceTime;
    }

   
    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    
    public BigDecimal getPrice() {
        return price;
    }

    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

   
    public BigDecimal getPreferentialPrice() {
        return preferentialPrice;
    }

    
    public void setPreferentialPrice(BigDecimal preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

   
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

   
    public Integer getProductStatus() {
        return productStatus;
    }

    
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    
    public String getSellerId() {
        return sellerId;
    }

   
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    
    public String getProductDescribe() {
        return productDescribe;
    }

    
    public void setProductDescribe(String productDescribe) {
        this.productDescribe = productDescribe == null ? null : productDescribe.trim();
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
}