package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月19日 下午3:44:13
 */
public class CustomerSkillVO {
	
	/**技能ID*/
	private Long  customerSkillId;
	/**技能项（分类名称）*/
	private String  skillItemName;
	/**技能项ID(分类ID)*/
    private Long skillItemId;
//    /**已选中ID*/
//    private Long  oldSkillItemExtId;
    
    /**已选中ID 列表*/
    private  List<Long>   oldSkillItemExtIdList;
    
    
    /**已选中等级*/
    private Integer oldSkillRange;
    /**服务单位（小时/半小时/次）*/
    private String oldServiceUnit;
    /**已选中的价格*/
    private BigDecimal oldSkillPrice;
    /**技能开关 1：开启  0：关闭*/
    private Integer switchStatus;
    /**已选中的折扣*/
    private BigDecimal oldDiscountRate;
    /**折扣列表*/
    private List<BigDecimal> discontRateList ;
    /**分类项列表*/
    private List<CustomerSkillExtVO>  skillExtList;
    
    
    
    /**技能类型1：按次算     2：按时长算*/
    private  Integer  skillType;
    
    
	public Long getCustomerSkillId() {
		return customerSkillId;
	}
	public void setCustomerSkillId(Long customerSkillId) {
		this.customerSkillId = customerSkillId;
	}
	public String getSkillItemName() {
		return skillItemName;
	}
	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}
	public Long getSkillItemId() {
		return skillItemId;
	}
	public void setSkillItemId(Long skillItemId) {
		this.skillItemId = skillItemId;
	}
	
	public Integer getSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(Integer switchStatus) {
		this.switchStatus = switchStatus;
	}

	
	public String getOldServiceUnit() {
		return oldServiceUnit;
	}
	public void setOldServiceUnit(String oldServiceUnit) {
		this.oldServiceUnit = oldServiceUnit;
	}
	public BigDecimal getOldSkillPrice() {
		return oldSkillPrice;
	}
	public void setOldSkillPrice(BigDecimal oldSkillPrice) {
		this.oldSkillPrice = oldSkillPrice;
	}
	public BigDecimal getOldDiscountRate() {
		return oldDiscountRate;
	}
	public void setOldDiscountRate(BigDecimal oldDiscountRate) {
		this.oldDiscountRate = oldDiscountRate;
	}
	public List<CustomerSkillExtVO> getSkillExtList() {
		return skillExtList;
	}
	public void setSkillExtList(List<CustomerSkillExtVO> skillExtList) {
		this.skillExtList = skillExtList;
	}
	public Integer getOldSkillRange() {
		return oldSkillRange;
	}
	public void setOldSkillRange(Integer oldSkillRange) {
		this.oldSkillRange = oldSkillRange;
	}
	
	public List<BigDecimal> getDiscontRateList() {
		return discontRateList;
	}
	public void setDiscontRateList(List<BigDecimal> discontRateList) {
		this.discontRateList = discontRateList;
	}
	public Integer getSkillType() {
		return skillType;
	}
	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}
	public List<Long> getOldSkillItemExtIdList() {
		return oldSkillItemExtIdList;
	}
	public void setOldSkillItemExtIdList(List<Long> oldSkillItemExtIdList) {
		this.oldSkillItemExtIdList = oldSkillItemExtIdList;
	}
	

	
	
	
	
	
	
	
	
}