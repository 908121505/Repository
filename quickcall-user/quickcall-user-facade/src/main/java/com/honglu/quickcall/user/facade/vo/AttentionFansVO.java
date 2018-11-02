package com.honglu.quickcall.user.facade.vo;


/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：关注/粉丝列表
 * @Package: com.honglu.quickcall.user.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月9日 下午1:05:55
 */
public class AttentionFansVO {
    /**客户ID*/
	private Long  customerId;
	/**头像url*/
	private String  headPortraitUrl;
	/**昵称*/
	private String nickName;
	/**性别0=女,1=男*/
    private Integer sex;
    /**年龄*/
    private Integer age;
    /**关注状态0：未关注  1：已关注*/
    private Integer attentionStatus;
    //互相关注状态0：不是 1：互相关注
    private Integer eachAttentionStatus;
    //是否大V
    private Integer type;
    
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}
	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
    
    
    

}