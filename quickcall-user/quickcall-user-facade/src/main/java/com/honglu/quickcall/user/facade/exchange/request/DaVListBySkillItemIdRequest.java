package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 大V分类列表
 * @author zhaozheyi
 *
 */
public class DaVListBySkillItemIdRequest  extends  UserCenterRequest{
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6369756666688787741L;
	/**客户编号*/
	private Long  customerId;
	/**客户编号*/
	private Long  skillItemId;
	/**当前页*/
	private Integer  pageIndex;
	
	private Integer  pageSize;

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
	
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String getBizCode() {
		return  UserFunctionType.QUERY_DV_LIST_BY_TYPE;
	}
	@Override
	public String toString() {
		return "DaVListBySkillIdRequest [customerId=" + customerId + ", skillItemId=" + skillItemId + ", pageIndex="
				+ pageIndex + "]";
	}
	
	
	
	
	
	
	
	
	
}
