package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 首页搜索用户
 * @author zhaozheyi
 *
 */
public class SearchPersonRequest extends UserCenterRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7167561630995863410L;

	private String keyword;
	
	private Integer limitCount;
	
	private Long customerId;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}
	
	@Override
	public String getBizCode() {
		return UserFunctionType.SEARCH_PERSON_LIST;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
