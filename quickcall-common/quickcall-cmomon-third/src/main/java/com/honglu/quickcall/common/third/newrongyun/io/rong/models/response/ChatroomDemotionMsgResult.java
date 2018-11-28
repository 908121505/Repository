package com.honglu.quickcall.common.third.newrongyun.io.rong.models.response;

import com.honglu.quickcall.common.third.newrongyun.io.rong.models.Result;
import com.honglu.quickcall.common.third.newrongyun.io.rong.util.GsonUtil;

public class ChatroomDemotionMsgResult extends Result {

	private String[] objectNames;

	public ChatroomDemotionMsgResult(Integer code, String msg, String[] objectNames) {
		super(code, msg);
		this.objectNames = objectNames;
	}

	public ChatroomDemotionMsgResult(String[] objectNames) {
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
		return GsonUtil.toJson(this, ChatroomDemotionMsgResult.class);
	}
}
