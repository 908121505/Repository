package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：查询某分类下大V列表
 * @Package: com.honglu.quickcall.account.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年10月18日 下午2:17:51
 */
public class DaVListBySkillIdRequest  extends  AbstractRequest{
    
	
	private static final long serialVersionUID = -4331779888322189242L;
	/**客户编号（预留暂时不用）*/
	private Long  customerId;
	/**客户编号（预留暂时不用）*/
	private Long  skillId;

	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	@Override
	public String getBizCode() {
		return  OrderRequestType.QUERY_DV_LIST_BY_TYPE;
	}
	@Override
	public String toString() {
		return "DaVListBySkillIdRequest [customerId=" + customerId + ", skillId=" + skillId + "]";
	}
	
	
	
	
	
	
}
