package com.honglu.quickcall.common.third.newrongyun.io.rong.models.response;

import java.util.List;

import com.honglu.quickcall.common.third.newrongyun.io.rong.models.Result;
import com.honglu.quickcall.common.third.newrongyun.io.rong.util.GsonUtil;

/**
 * lisitGagGroupUser 返回结果
 */
public class ListGagGroupUserResult extends Result {
	// 群组被禁言用户列表。
	List<GagGroupUser> members;

	public ListGagGroupUserResult(Integer code, String msg, List<GagGroupUser> members) {
		super(code, msg);
		this.members = members;
	}

	public ListGagGroupUserResult(List<GagGroupUser> members) {
		this.members = members;
	}

	/**
	 * 获取members
	 *
	 * @return List
	 */
	public List<GagGroupUser> getMembers() {
		return this.members;
	}

	/**
	 * 设置members
	 *
	 */
	public void setMembers(List<GagGroupUser> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListGagGroupUserResult.class);
	}
}
