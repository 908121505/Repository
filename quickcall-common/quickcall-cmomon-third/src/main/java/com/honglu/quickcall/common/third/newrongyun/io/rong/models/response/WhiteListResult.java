package com.honglu.quickcall.common.third.newrongyun.io.rong.models.response;

import com.honglu.quickcall.common.third.newrongyun.io.rong.models.Result;
import com.honglu.quickcall.common.third.newrongyun.io.rong.models.user.UserModel;
import com.honglu.quickcall.common.third.newrongyun.io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class WhiteListResult extends Result {

	private UserModel[] members;

	public WhiteListResult(Integer code, String msg, UserModel[] members) {
		super(code, msg);
		this.members = members;
	}

	public WhiteListResult(UserModel[] members) {
		this.members = members;
	}

	public UserModel[] getMembers() {
		return this.members;
	}

	public void setMembers(UserModel[] members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, WhiteListResult.class);
	}

}
