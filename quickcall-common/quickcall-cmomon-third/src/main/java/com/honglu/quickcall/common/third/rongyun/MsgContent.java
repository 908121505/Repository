package com.honglu.quickcall.common.third.rongyun;

/**
 * 服务端发送消息的实体类，若发送消息出现异常可与客户端确认发送消息的内容解析状况 因为客户端使用自定义格式发送，故不需要按照官方文档进行，有问题与客户端确认
 * 
 * @author daihuan
 *
 */
public class MsgContent {

	
	private  Long  orderId;
	
	private  String  orderDesc;
	
	private Integer  status;
	
	

	public MsgContent(Long orderId, String orderDesc, Integer status) {
		super();
		this.orderId = orderId;
		this.orderDesc = orderDesc;
		this.status = status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MsgContent [orderId=" + orderId + ", orderDesc=" + orderDesc + ", status=" + status + "]";
	}
	
	
	
}
