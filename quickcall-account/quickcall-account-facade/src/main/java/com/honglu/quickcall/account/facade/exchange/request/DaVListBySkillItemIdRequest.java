package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：查询某分类下大V列表
 * @Package: com.honglu.quickcall.account.facade.exchange.req
 * @author: chenliuguang   
 * @date: 2018年10月18日 下午2:17:51
 */
public class DaVListBySkillItemIdRequest  extends  AbstractRequest{
    
	
	private static final long serialVersionUID = 6557505373627777658L;
	/**客户编号（预留暂时不用）*/
	private Long  customerId;
	/**客户编号（预留暂时不用）*/
	private Long  skillItemId;
	/**当前页*/
	private Integer  pageIndex;

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
	
	
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	
	@Override
	public String getBizCode() {
		return  OrderRequestType.QUERY_DV_LIST_BY_TYPE;
	}
	@Override
	public String toString() {
		return "DaVListBySkillIdRequest [customerId=" + customerId + ", skillItemId=" + skillItemId + ", pageIndex="
				+ pageIndex + "]";
	}
	
	
	
	
	
	
	
	
	
}
