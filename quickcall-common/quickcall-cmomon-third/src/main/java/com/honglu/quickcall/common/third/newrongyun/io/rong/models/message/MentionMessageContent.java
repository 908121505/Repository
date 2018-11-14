package com.honglu.quickcall.common.third.newrongyun.io.rong.models.message;

import com.honglu.quickcall.common.third.newrongyun.io.rong.messages.BaseMessage;
import com.honglu.quickcall.common.third.newrongyun.io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class MentionMessageContent {
	private BaseMessage content;
	private MentionedInfo mentionedInfo;

	public MentionMessageContent(BaseMessage content, MentionedInfo mentionedInfo) {
		this.content = content;
		this.mentionedInfo = mentionedInfo;
	}

	public BaseMessage getContent() {
		return this.content;
	}

	public void setContent(BaseMessage content) {
		this.content = content;
	}

	public MentionedInfo getMentionedInfo() {
		return this.mentionedInfo;
	}

	public void setMentionedInfo(MentionedInfo mentionedInfo) {
		this.mentionedInfo = mentionedInfo;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, MentionMessageContent.class);
	}
}
