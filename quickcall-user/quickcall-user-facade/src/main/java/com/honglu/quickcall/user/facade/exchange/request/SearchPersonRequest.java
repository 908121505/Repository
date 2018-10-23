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
	
	private Long customerId;
	
	private Integer pageIndex;
	
	private Integer pageSize;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.SEARCH_PERSON_LIST;
	}

	

}
