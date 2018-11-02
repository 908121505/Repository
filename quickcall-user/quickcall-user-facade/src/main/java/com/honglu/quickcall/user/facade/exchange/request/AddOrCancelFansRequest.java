package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：添加/取消关注
 * @Package: com.honglu.quickcall.user.facade.exchange.req
 * @author: chenliuguang   
 * @date: 2018年10月9日 下午12:51:39 
 */
public class AddOrCancelFansRequest extends UserCenterRequest{
	
	
	private static final long serialVersionUID = 5375590432701433397L;
	/**粉丝ID*/
	private  Long  fansId;
	/**被关注人ID*/
	private  Long  attendedId;
	/**操作类型1：添加关注   2：取消关注*/
	private Integer  type ;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	@Override
	public String getBizCode() {
		return UserFunctionType.ADD_OR_UPDATE_FANS;
	}
	
	
	
	@Override
	public String toString() {
		return "AddOrCancelFansRequest [fansId=" + fansId + ", attendedId=" + attendedId + ", type=" + type + "]";
	}






}
