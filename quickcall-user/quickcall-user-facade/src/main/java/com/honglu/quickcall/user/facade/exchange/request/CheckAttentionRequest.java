package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：检查是否关注对方入参
 * @Package: com.honglu.quickcall.user.facade.exchange.request 
 * @author: chenliuguang   
 * @date: 2018年10月9日 下午12:51:39 
 */
public class CheckAttentionRequest extends UserCenterRequest{
	
	private static final long serialVersionUID = -3608510292775717008L;
	/**粉丝ID*/
	private  Long  fansId;
	/**被关注人ID*/
	private  Long  attendedId;
	
	public Long getFansId() {
		return fansId;
	}
	public void setFansId(Long fansId) {
		this.fansId = fansId;
	}
	public Long getAttendedId() {
		return attendedId;
	}
	public void setAttendedId(Long attendedId) {
		this.attendedId = attendedId;
	}



	@Override
	public String getBizCode() {
		return UserFunctionType.CHECK_ATTENTION;
	}
	@Override
	public String toString() {
		return "CheckAttentionRequest [fansId=" + fansId + ", attendedId=" + attendedId + "]";
	}

	
	

}
