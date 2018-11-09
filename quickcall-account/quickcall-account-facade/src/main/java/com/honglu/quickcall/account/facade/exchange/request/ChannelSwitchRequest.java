package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 渠道获取开关请求类
 * @author zhaozheyi
 *
 */
public class ChannelSwitchRequest extends AbstractRequest {
	
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 6178059602905715876L;

	/**
	 * 渠道编号
	 */
	private String channel;
	
	/**
	 * app版本号
	 */
	private String version;
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String getBizCode() {
		return AccountFunctionType.CHANNEL_SWITCH_STATUS;
	}
	
	@Override
	public String toString() {
		return "ChannelSwitchRequest [channel=" + channel + ", version=" + version + "]";
	}
}
