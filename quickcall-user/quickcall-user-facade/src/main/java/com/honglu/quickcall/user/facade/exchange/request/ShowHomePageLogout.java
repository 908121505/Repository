package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：进入大V个人主页
 * @Package: com.honglu.quickcall.user.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年10月10日 上午10:19:56 
 */
public class ShowHomePageLogout extends UserCenterRequest{
	
	private static final long serialVersionUID = -7587477468626533467L;
	/**大V客户ID*/
	private Long customerId;
	/**当前人ID*/
	private Long  myUserId;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
	
	

	public Long getMyUserId() {
		return myUserId;
	}

	public void setMyUserId(Long myUserId) {
		this.myUserId = myUserId;
	}

	@Override
	public String getBizCode() {
		return UserFunctionType.ShowHomePageLogout;
	}

	@Override
	public String toString() {
		return "ShowHomePageLogout [customerId=" + customerId + ", myUserId=" + myUserId + "]";
	}
	
	

}
