package com.honglu.quickcall.common.third.shumei.request;

/**
 * 敏感词过滤输入
 * @author zhaozheyi
 *
 */
public class WordFilterRquest {
	public final static String channel_nickname = "qinyin_nickname";
	public final static String channel_signature = "qinyin_signature";
	/**
	 * 敏感字
	 */
	private String text;
	/**
	 * 客户ID
	 */
	private String tokenId;
	/**
	 * 业务渠道
	 */
	private String channel;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 回溯ID
	 */
	private String btId;
	/**
	 * 客户端IP地址
	 */
	private String ip;
	private String phone;
	private String deviceId;
	private String relationship;
	private String receiveTokenId;
	private String level;
	private String role;
	private String room;
	private String title;
	private String topic;
	private String nickocr;
	private String imei;
	private String mac;
	private String idfv;
	private String idfa;
	
	/**
	 * 构造函数
	 * @param text 输入的字体
	 * @param tokenId	默认为客户ID
	 * @param channel	业务渠道
	 */
	public WordFilterRquest(String text,String tokenId,String channel){
		super();
		this.text = text;
		this.tokenId = tokenId;
		this.channel = channel;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBtId() {
		return btId;
	}
	public void setBtId(String btId) {
		this.btId = btId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getReceiveTokenId() {
		return receiveTokenId;
	}
	public void setReceiveTokenId(String receiveTokenId) {
		this.receiveTokenId = receiveTokenId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getNickocr() {
		return nickocr;
	}
	public void setNickocr(String nickocr) {
		this.nickocr = nickocr;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getIdfv() {
		return idfv;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public static String getChannelNickname() {
		return channel_nickname;
	}
	public static String getChannelSignature() {
		return channel_signature;
	}
	
	
}
