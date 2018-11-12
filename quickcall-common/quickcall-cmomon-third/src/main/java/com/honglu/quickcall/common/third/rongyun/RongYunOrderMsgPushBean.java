package com.honglu.quickcall.common.third.rongyun;

/**
 * 服务端发送消息的实体类，若发送消息出现异常可与客户端确认发送消息的内容解析状况 因为客户端使用自定义格式发送，故不需要按照官方文档进行，有问题与客户端确认
 * 
 * @author daihuan
 *
 */
public class RongYunOrderMsgPushBean {

	private Integer categoryId;// 发送文字消息默认用1
	private MsgContent msgContent;// 发送消息的内容
	private Integer direction;// 发送文字消息默认用1
	private Integer receiveStatus;// 默认传0
	private Integer sendStatus;// 默认传10
	private Long sendTime;// 发送者信息
	private SendUser sendUser;// 发送者信息
	private Long senderId;// 发送的id
	private Long targetId;// 目标id
	private Integer type;// 21订单消息
	
	
	
	public RongYunOrderMsgPushBean(Integer categoryId, MsgContent msgContent, Integer direction, Integer receiveStatus,
			Integer sendStatus, Long sendTime, SendUser sendUser, Long senderId, Long targetId, Integer type) {
		super();
		this.categoryId = categoryId;
		this.msgContent = msgContent;
		this.direction = direction;
		this.receiveStatus = receiveStatus;
		this.sendStatus = sendStatus;
		this.sendTime = sendTime;
		this.sendUser = sendUser;
		this.senderId = senderId;
		this.targetId = targetId;
		this.type = type;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public MsgContent getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(MsgContent msgContent) {
		this.msgContent = msgContent;
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
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
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
		return "RongYunOrderMsgPushBean [categoryId=" + categoryId + ", msgContent=" + msgContent + ", direction="
				+ direction + ", receiveStatus=" + receiveStatus + ", sendStatus=" + sendStatus + ", sendTime="
				+ sendTime + ", sendUser=" + sendUser + ", senderId=" + senderId + ", targetId=" + targetId + ", type="
				+ type + "]";
	}

	
	
	
	
}
