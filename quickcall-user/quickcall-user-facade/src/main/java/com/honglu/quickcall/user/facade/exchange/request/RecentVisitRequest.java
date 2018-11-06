package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 最近访问请求参数
 * @author zhaozheyi
 *
 */
public class RecentVisitRequest extends  UserCenterRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2360838781875279571L;
	
	private Long startTime;
	
	private Long customerId;
	
	private Integer pageIndex;
	
	private Integer pageSize;
	
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

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.Recent_Visit_List;
	}
}
