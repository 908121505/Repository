package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 检查是否互相关注
 * @author zhaozheyi
 *
 */
public class CheckEachAttentionRequest extends UserCenterRequest{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 2528217322480992782L;
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
		return UserFunctionType.CHECK_EACH_ATTENTION;
	}
	@Override
	public String toString() {
		return "CheckEachAttentionRequest [fansId=" + fansId + ", attendedId=" + attendedId + "]";
	}
}
