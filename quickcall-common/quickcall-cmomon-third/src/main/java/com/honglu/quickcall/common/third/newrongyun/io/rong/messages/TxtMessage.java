package com.honglu.quickcall.common.third.newrongyun.io.rong.messages;

import com.honglu.quickcall.common.third.newrongyun.io.rong.util.GsonUtil;

/**
 *
 * 文本消息。
 *
 */
public class TxtMessage extends BaseMessage {
	private String content = "";
	private String extra = "";
	private transient static final String TYPE = "RC:TxtMsg";

	public TxtMessage(String content, String extra) {
		this.content = content;
		this.extra = extra;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	/**
	 * 获取消息内容。
	 *
	 * @return String
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            设置消息内容。
	 *
	 *
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @return String
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * @param extra
	 *            设置附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 *
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, TxtMessage.class);
	}
}