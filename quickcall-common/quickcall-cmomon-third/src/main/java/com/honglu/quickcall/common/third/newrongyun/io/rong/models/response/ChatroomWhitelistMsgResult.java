package com.honglu.quickcall.common.third.newrongyun.io.rong.models.response;

import com.honglu.quickcall.common.third.newrongyun.io.rong.models.Result;
import com.honglu.quickcall.common.third.newrongyun.io.rong.util.GsonUtil;

public class ChatroomWhitelistMsgResult extends Result {
	private String[] objectNames;

	public ChatroomWhitelistMsgResult(Integer code, String msg, String[] objectNames) {
		super(code, msg);
		this.objectNames = objectNames;
	}

	public ChatroomWhitelistMsgResult(String[] objectNames) {
		this.objectNames = objectNames;
	}

	public String[] getObjectNames() {
		return this.objectNames;
	}

	public void setObjectNames(String[] objectNames) {
		this.objectNames = objectNames;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomWhitelistMsgResult.class);
	}

}
