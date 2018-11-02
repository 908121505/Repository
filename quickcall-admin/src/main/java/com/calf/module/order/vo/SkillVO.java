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


	private String  id;
	private String name;
    private String skillDescribe;
    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String imageUrl;
    
    private String titleUrl;

    private Integer priceStep;
    private Integer sort;
    private Integer skillStatus;
    private String remark;

    public String getId() {
		return id;
	}

	public void setId(String id) {
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
		this.imageUrl = imageUrl;
	}

	public String getTitleUrl() {
		return titleUrl;
	}

	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}

	public Integer getPriceStep() {
        return priceStep;
    }

    public void setPriceStep(Integer priceStep) {
        this.priceStep = priceStep;
    }
    
    public Integer getSort() {
    	return sort;
    }
    
    public void setSort(Integer sort) {
    	this.sort = sort;
    }

    public Integer getSkillStatus() {
        return skillStatus;
    }

    public void setSkillStatus(Integer skillStatus) {
        this.skillStatus = skillStatus;
    }


    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
	
}