package com.honglu.quickcall.common.third.rongyun;

/**
 * 服务端发送消息的实体类，若发送消息出现异常可与客户端确认发送消息的内容解析状况 因为客户端使用自定义格式发送，故不需要按照官方文档进行，有问题与客户端确认
 * 
 * @author daihuan
 *
 */
public class SendUser {

	private String name;// 发送消息的内容
	private String portrait;// 发送文字消息默认用1
	private Integer sex;// 默认传0
	private Long userId;// 默认传10
	private String remarkName;//备注
	
	

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public SendUser(String name, String portrait, Integer sex, Long userId) {
		super();
		this.name = name;
		this.portrait = portrait;
		this.sex = sex;
		this.userId = userId;
	}

	public SendUser(String name, String portrait, Integer sex, Long userId, String remarkName) {
		super();
		this.name = name;
		this.portrait = portrait;
		this.sex = sex;
		this.userId = userId;
		this.remarkName = remarkName;
	}
	
	

}
