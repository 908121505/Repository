package com.honglu.quickcall.common.third.rongyun;
/**
 * 服务端发送消息的实体类，若发送消息出现异常可与客户端确认发送消息的内容解析状况
 * 因为客户端使用自定义格式发送，故不需要按照官方文档进行，有问题与客户端确认
 * @author daihuan
 *
 */
public class RongYunPushBean {

	private Integer categoryId;//发送文字消息默认用1
	private String content;//发送消息的内容
	private Integer direction;//发送文字消息默认用1
	private Integer receiveStatus;//默认传0
	private Integer sendStatus;//默认传10
	private Integer senderId;//发送的id
	private Integer targetId;//目标id
	private Integer type;//1为文字消息
	private SendUser sendUser;//发送者信息
	private Long sendTime;//发送者信息
	
	
	
	
	public RongYunPushBean(Integer categoryId, String content, Integer direction, Integer receiveStatus,
			Integer sendStatus, Integer senderId, Integer targetId, Integer type, SendUser sendUser, Long sendTime) {
		super();
		this.categoryId = categoryId;
		this.content = content;
		this.direction = direction;
		this.receiveStatus = receiveStatus;
		this.sendStatus = sendStatus;
		this.senderId = senderId;
		this.targetId = targetId;
		this.type = type;
		this.sendUser = sendUser;
		this.sendTime = sendTime;
	}
	public Long getSendTime() {
		return sendTime;
	}
	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}
	public SendUser getSendUser() {
		return sendUser;
	}
	public void setSendUser(SendUser sendUser) {
		this.sendUser = sendUser;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public Integer getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public Integer getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public Integer getTargetId() {
		return targetId;
	}
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "RongYunPushBean [categoryId=" + categoryId + ", content=" + content + ", direction=" + direction
				+ ", receiveStatus=" + receiveStatus + ", sendStatus=" + sendStatus + ", senderId=" + senderId
				+ ", targetId=" + targetId + ", type=" + type + "]";
	}
	
	
	
}
