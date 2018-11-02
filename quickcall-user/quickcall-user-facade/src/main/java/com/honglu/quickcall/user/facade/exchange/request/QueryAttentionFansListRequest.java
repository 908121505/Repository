package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：查询关注/粉丝列表
 * @Package: com.honglu.quickcall.user.facade.exchange.req
 * @author: chenliuguang   
 * @date: 2018年10月9日 下午12:51:39 
 */
public class QueryAttentionFansListRequest extends UserCenterRequest{
	
	
	private static final long serialVersionUID = -6604547045122967103L;
	/**客户编号*/
	private  Long  customerId;
	/**查询类型1：关注列表   2：粉丝列表*/
	private Integer   type;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}




	@Override
	public String getBizCode() {
		return UserFunctionType.QUERY_ATTENDTION_FANS_LIST;
	}

	@Override
	public String toString() {
		return "QueryAttentionFansListRequest [customerId=" + customerId + ", type=" + type + "]";
	}
	
	
	

}
