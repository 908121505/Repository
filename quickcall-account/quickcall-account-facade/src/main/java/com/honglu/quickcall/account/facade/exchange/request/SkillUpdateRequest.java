package com.honglu.quickcall.account.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;


/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：客户技能更新入参
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午2:22:07 
 */
public class SkillUpdateRequest extends AbstractRequest {
	
	
	private static final long serialVersionUID = 8424942355002671655L;
	/**客户编号*/
	private Long  customerId;
	/**产品ID,可为空，但是有了必须传入*/
	private Long  productId;
	/**技能ID*/
	private Long  skillId;
	/**产品状态1：开启  0：关闭*/
	private Integer  productStatus;
	/**单价*/
	private BigDecimal  price;
	/**服务时长(预留字段暂时不用)*/
	private  Integer  serviceTime;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}




	@Override
	public String getBizCode() {
		return OrderRequestType.UPDATE_SKILL_INFO;
	}



}
