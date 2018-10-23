package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;

import com.honglu.quickcall.user.facade.constants.UserBizConstants;

/**
 * 首页搜索用户列表返回
 * @author zhaozheyi
 *
 */
public class SearchPersonListVO implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1361079514883540575L;
	/**
	 * 是否关注
	 */
	private Integer attentionStatus = UserBizConstants.ATTENTION_STATUS_UN_ATTENED;;
	/**
	 * 互相关注
	 */
	private Integer eachAttentionStatus = UserBizConstants.ATTENTION_STATUS_UN_ATTENED;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 主播性别
	 */
	private Integer sex;
	/**
	 * 主播编号
	 */
	private Long customerId;
	/**
	 * 是否大V
	 */
	private Integer type;
	/**
	 * 头像地址
	 */
	private String headPortraitUrl;
	/**
	 * 主播年龄
	 */
	private Integer age;
	
	public Integer getAttentionStatus() {
		return attentionStatus;
	}
	public void setAttentionStatus(Integer attentionStatus) {
		this.attentionStatus = attentionStatus;
	}
	public Integer getEachAttentionStatus() {
		return eachAttentionStatus;
	}
	public void setEachAttentionStatus(Integer eachAttentionStatus) {
		this.eachAttentionStatus = eachAttentionStatus;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}
	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
