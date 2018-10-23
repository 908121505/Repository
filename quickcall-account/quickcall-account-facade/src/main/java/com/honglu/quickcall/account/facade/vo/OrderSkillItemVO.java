package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：用户技能信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月22日 上午10:55:17 
 */
public class OrderSkillItemVO implements  Serializable{


	/**技能ICON*/
	private String  skillIcon;
	/**用户技能ID*/
	private Long  userSkillItemId;
	/**技能名称*/
	private String  skillItemName;
	/**技能单价*/
	private BigDecimal  price ;
	/**折扣价格*/
	private BigDecimal  discontPrice;
	/**服务单位（小时/半小时/次）*/
	private String  serviceUnit;
	/**技能类型1：按次收费 有预约时间，2：按时间段收费 没有预约时间*/
	private Integer  skillType;
	public Long getUserSkillItemId() {
		return userSkillItemId;
	}
	public void setUserSkillItemId(Long userSkillItemId) {
		this.userSkillItemId = userSkillItemId;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getServiceUnit() {
		return serviceUnit;
	}
	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}
	public String getSkillItemName() {
		return skillItemName;
	}
	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}
	public String getSkillIcon() {
		return skillIcon;
	}
	public void setSkillIcon(String skillIcon) {
		this.skillIcon = skillIcon;
	}
	public BigDecimal getDiscontPrice() {
		return discontPrice;
	}
	public void setDiscontPrice(BigDecimal discontPrice) {
		this.discontPrice = discontPrice;
	}
	public Integer getSkillType() {
		return skillType;
	}
	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}
	
	
	
	
	
	
	
	
}
