package com.honglu.quickcall.common.third.shumei.request;

import java.io.Serializable;

public class AudioFilterRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3630852312579709593L;

	/**
	 * 音频唯一标示
	 */
	private String btid;
	
	/**
	 * 音频地址
	 */
	
	private String url;
	/**
	 * 音频名称
	 */
	private String audioName;
	
	
	
	/**
	 * 客户端唯一标示tokenId
	 */
	private String tokenId;
	
	/**
	 * 业务渠道
	 */
	private String channel;
	
	/**
	 * ip
	 */
	private String ip;

	public String getBtid() {
		return btid;
	}

	public void setBtid(String btid) {
		this.btid = btid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAudioName() {
		return audioName;
	}

	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "AudioFilterRequest [btid=" + btid + ", url=" + url + ", audioName=" + audioName + ", tokenId=" + tokenId
				+ ", channel=" + channel + ", ip=" + ip + "]";
	}
	
	
}
