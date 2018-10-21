package com.honglu.quickcall.account.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能扩展表
 * @Package: com.honglu.quickcall.account.facade.entity 
 * @author: chenliuguang   
 * @date: 2018年10月20日 下午6:44:47
 */
public class CustomerSkillExt {
    
    private Long custSkillExtId;

    private Long customerSkillId;
    
    private Long skillItemExtId;

   
    private String serviceUnit;

   
    private BigDecimal skillPrice;

    private BigDecimal discountRate;

   
    private BigDecimal discountPrice;
    
    private Integer  switchStatus;

   
    private Date createTime;

   
    private Date modifyTime;

   
    private String createMan;

    
    private String modifyMan;

   
    private String remark;

   
    public Long getCustSkillExtId() {
        return custSkillExtId;
    }

    
    public void setCustSkillExtId(Long custSkillExtId) {
        this.custSkillExtId = custSkillExtId;
    }

    
    public Long getCustomerSkillId() {
        return customerSkillId;
    }

    
    public void setCustomerSkillId(Long customerSkillId) {
        this.customerSkillId = customerSkillId;
    }

    
    public String getServiceUnit() {
        return serviceUnit;
    }

    
    public void setServiceUnit(String serviceUnit) {
        this.serviceUnit = serviceUnit == null ? null : serviceUnit.trim();
    }

    
    public BigDecimal getSkillPrice() {
        return skillPrice;
    }

    
    public void setSkillPrice(BigDecimal skillPrice) {
        this.skillPrice = skillPrice;
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


	public Long getSkillItemExtId() {
		return skillItemExtId;
	}


	public void setSkillItemExtId(Long skillItemExtId) {
		this.skillItemExtId = skillItemExtId;
	}


	public Integer getSwitchStatus() {
		return switchStatus;
	}


	public void setSwitchStatus(Integer switchStatus) {
		this.switchStatus = switchStatus;
	}
	
	
    
    
}