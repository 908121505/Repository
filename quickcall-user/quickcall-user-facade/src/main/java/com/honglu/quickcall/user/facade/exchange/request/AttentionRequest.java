package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 功能描述：添加关注 帖子
 *
 * @Package: com.honglu.quickcall.user.facade.exchange.req
 * @author: xiangping
 * @date: 2018年11月2日 下午12:51:39
 */
public class AttentionRequest extends UserCenterRequest{
	private static final long serialVersionUID = 5375590432701433397L;

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
		return UserFunctionType.ADD_ATTENTION;
	}

	@Override
	public String toString() {
		return "AttentionRequest [fansId=" + fansId + ", attendedId=" + attendedId + "]";
	}
}
