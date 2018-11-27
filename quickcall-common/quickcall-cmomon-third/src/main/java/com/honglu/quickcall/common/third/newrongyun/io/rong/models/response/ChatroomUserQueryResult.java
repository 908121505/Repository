package com.honglu.quickcall.common.third.newrongyun.io.rong.models.response;

import java.util.List;

import com.honglu.quickcall.common.third.newrongyun.io.rong.models.Result;
import com.honglu.quickcall.common.third.newrongyun.io.rong.models.chatroom.ChatroomMember;
import com.honglu.quickcall.common.third.newrongyun.io.rong.util.GsonUtil;

/**
 * chatroomUserQuery 返回结果
 */
public class ChatroomUserQueryResult extends Result {
	/**
	 * 聊天室中用户数。
	 *
	 */
	Integer total;
	/**
	 * 聊天室成员列表。
	 *
	 */
	List<ChatroomMember> members;

	public ChatroomUserQueryResult(Integer code, String msg, Integer total, List<ChatroomMember> members) {
		super(code, msg);
		this.total = total;
		this.members = members;
	}

	public ChatroomUserQueryResult(Integer total, List<ChatroomMember> members) {
		this.total = total;
		this.members = members;
	}

	/**
	 * 设置total
	 *
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 获取total
	 *
	 * @return Integer
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * 获取members
	 *
	 * @return List
	 */
	public List<ChatroomMember> getMembers() {
		return this.members;
	}

	/**
	 * 设置members
	 *
	 */
	public void setMembers(List<ChatroomMember> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomUserQueryResult.class);
	}
}
