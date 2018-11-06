package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.util.List;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：主播技能信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月22日 上午10:56:48 
 */
public class OrderDaVSkillVO implements  Serializable{

	private static final long serialVersionUID = 3257834087300015177L;
	/**主播ID*/
	private Long  serviceId;
	/**主播昵称*/
	private String  nickName;
	/**主播头像*/
	private String  headPortraitUrl;

	/**产品ID*/
	private List<OrderSkillItemVO>  custSkillList;
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}
	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}
	public List<OrderSkillItemVO> getCustSkillList() {
		return custSkillList;
	}
	public void setCustSkillList(List<OrderSkillItemVO> custSkillList) {
		this.custSkillList = custSkillList;
	}
	
}
