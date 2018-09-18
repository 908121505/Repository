package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2017-12-07.
 */
public class SelectWithDrawRequest extends AbstractRequest {
    
	private Integer pid;
	private Integer pageIndex;
	private Integer pageSize;
	

 

	public Integer getPid() {
		return pid;
	}




	public void setPid(Integer pid) {
		this.pid = pid;
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
        return AccountFunctionType.SelectWithDraw;
    }
}
