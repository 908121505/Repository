package com.honglu.quickcall.user.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能信息
 * 
 * @Package: com.honglu.quickcall.user.facade.entity
 * @author: chenliuguang
 * @date: 2018年10月19日 上午11:55:32
 */
public class CustomerSkill {
	
	
    private Long customerSkillId;

    private Long certifyId;

    private Long customerId;

    private Long skillItemId;

    private String skillName;

    private String serviceUnit;

    private BigDecimal skillPrice;

    private Integer skillStatus;

    private Integer switchStatus;

    private Integer receiveStatus;

    private BigDecimal discountRate;

    private BigDecimal discountPrice;

    private Integer monday;

    private Integer tuesday;

    private Integer wednesday;

    private Integer thursday;

    private Integer friday;

    private Integer saturday;

    private Integer sunday;

    private Date startServiceTime;

    private Date endServiceTime;

    private String skillDescribe;

    private Date createTime;

    private Date modifyTime;

    private String createMan;

    private String modifyMan;

    private String remark;

    /**********扩展查询字段********/
    private String skillImageUrl;
    private String skillVoiceUrl;
    private BigDecimal skillVoiceTime;

    public Long getCustomerSkillId() {
        return customerSkillId;
    }

    public void setCustomerSkillId(Long customerSkillId) {
        this.customerSkillId = customerSkillId;
    }

    public Long getCertifyId() {
        return certifyId;
    }

    public void setCertifyId(Long certifyId) {
        this.certifyId = certifyId;
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

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName == null ? null : skillName.trim();
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

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
    }

    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
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

    public Integer getMonday() {
        return monday;
    }

    
    public void setMonday(Integer monday) {
        this.monday = monday;
    }

    
    public Integer getTuesday() {
        return tuesday;
    }

    
    public void setTuesday(Integer tuesday) {
        this.tuesday = tuesday;
    }

    
    public Integer getWednesday() {
        return wednesday;
    }

    
    public void setWednesday(Integer wednesday) {
        this.wednesday = wednesday;
    }

    
    public Integer getThursday() {
        return thursday;
    }

    
    public void setThursday(Integer thursday) {
        this.thursday = thursday;
    }

    
    public Integer getFriday() {
        return friday;
    }

    
    public void setFriday(Integer friday) {
        this.friday = friday;
    }

   
    public Integer getSaturday() {
        return saturday;
    }

    
    public void setSaturday(Integer saturday) {
        this.saturday = saturday;
    }

    
    public Integer getSunday() {
        return sunday;
    }

    
    public void setSunday(Integer sunday) {
        this.sunday = sunday;
    }

   
    public Date getStartServiceTime() {
        return startServiceTime;
    }

    
    public void setStartServiceTime(Date startServiceTime) {
        this.startServiceTime = startServiceTime;
    }

   
    public Date getEndServiceTime() {
        return endServiceTime;
    }

    
    public void setEndServiceTime(Date endServiceTime) {
        this.endServiceTime = endServiceTime;
    }

    
    public String getSkillDescribe() {
        return skillDescribe;
    }

    
    public void setSkillDescribe(String skillDescribe) {
        this.skillDescribe = skillDescribe == null ? null : skillDescribe.trim();
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

    public Integer getSkillStatus() {
        return skillStatus;
    }

    public void setSkillStatus(Integer skillStatus) {
        this.skillStatus = skillStatus;
    }

    public String getSkillImageUrl() {
        return skillImageUrl;
    }

    public void setSkillImageUrl(String skillImageUrl) {
        this.skillImageUrl = skillImageUrl;
    }

    public String getSkillVoiceUrl() {
        return skillVoiceUrl;
    }

    public void setSkillVoiceUrl(String skillVoiceUrl) {
        this.skillVoiceUrl = skillVoiceUrl;
    }

    public BigDecimal getSkillVoiceTime() {
        return skillVoiceTime;
    }

    public void setSkillVoiceTime(BigDecimal skillVoiceTime) {
        this.skillVoiceTime = skillVoiceTime;
    }

}