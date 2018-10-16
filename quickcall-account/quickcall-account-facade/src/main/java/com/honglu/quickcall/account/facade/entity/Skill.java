package com.honglu.quickcall.account.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Skill {
    
    private Long id;

    
    private String name;

    
    private String skillDescribe;

    
    private BigDecimal minPrice;

    
    private BigDecimal maxPrice;

    
    private String imageUrl;
    private String titleUrl;

    
    private Integer sort;

    
    private BigDecimal priceStep;

   
    private Integer skillStatus;

    
    private Date createTime;

    
    private Date modifyTime;

    
    private String createMan;

    
    private String modifyMan;

    
    private String remark;

    
    
    
    public String getTitleUrl() {
		return titleUrl;
	}


	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}


	public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
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

    
    public Integer getSort() {
        return sort;
    }

   
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    
    public BigDecimal getPriceStep() {
        return priceStep;
    }

    
    public void setPriceStep(BigDecimal priceStep) {
        this.priceStep = priceStep;
    }

    
    public Integer getSkillStatus() {
        return skillStatus;
    }

    
    public void setSkillStatus(Integer skillStatus) {
        this.skillStatus = skillStatus;
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