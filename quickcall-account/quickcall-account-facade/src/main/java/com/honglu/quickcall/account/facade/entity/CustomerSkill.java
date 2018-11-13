package com.honglu.quickcall.account.facade.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能信息
 * @Package: com.honglu.quickcall.account.facade.entity 
 * @author: chenliuguang   
 * @date: 2018年10月19日 上午11:56:54
 */
public class CustomerSkill {
	
	
    private Long customerSkillId;

    private Long certifyId;

    private Long customerId;

    private Long skillItemId;
    
    private Long skillItemExtId;

    private String skillName;

    private String serviceUnit;
    
    private Integer skillRange;

    private BigDecimal skillPrice;

    private Integer skillStatus;

    private Integer switchStatus;

    private Integer receiveStatus;
    
    
    private Integer autoReceiveStatus;

    private BigDecimal discountRate;

    private BigDecimal discountPrice;

    private Integer monday;

    private Integer tuesday;

    private Integer wednesday;

    private Integer thursday;

    private Integer friday;

    private Integer saturday;

    private Integer sunday;

    private String endTimeStr;

    /**接单开关开启时间*/
    private String startTimeStr;
    
    /**预约接单开始时间*/
    private Date  appointStartTime;
    /**预约接单结束时间*/
    private Date  appointEndTime;

    private String skillDescribe;

    private Date createTime;

    private Date modifyTime;

    private String createMan;

    private String modifyMan;

    private String remark;
    
    private  Integer  skillType;
    

	public Integer getSkillType() {
		return skillType;
	}

	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public Integer getAutoReceiveStatus() {
		return autoReceiveStatus;
	}

	public void setAutoReceiveStatus(Integer autoReceiveStatus) {
		this.autoReceiveStatus = autoReceiveStatus;
	}

	public Date getAppointStartTime() {
		return appointStartTime;
	}

	public void setAppointStartTime(Date appointStartTime) {
		this.appointStartTime = appointStartTime;
	}

	public Date getAppointEndTime() {
		return appointEndTime;
	}

	public void setAppointEndTime(Date appointEndTime) {
		this.appointEndTime = appointEndTime;
	}

	public Integer getSkillStatus() {
		return skillStatus;
	}

	public void setSkillStatus(Integer skillStatus) {
		this.skillStatus = skillStatus;
	}

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

	public Integer getSkillRange() {
		return skillRange;
	}

	public void setSkillRange(Integer skillRange) {
		this.skillRange = skillRange;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Long getSkillItemExtId() {
		return skillItemExtId;
	}

	public void setSkillItemExtId(Long skillItemExtId) {
		this.skillItemExtId = skillItemExtId;
	}
	
	

	
    
	
    
    
}