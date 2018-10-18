package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：首页分类列表信息
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月18日 上午9:45:28
 */
public class DaVinfoListVO implements  Serializable{
	
	
	private static final long serialVersionUID = -7237402509909241201L;
	/**技能名称*/
	private  String   skillName;
	/**技能ID*/
	private  Long   skillId;
	/**大V技能列表*/
	private List<DaVinfoVO>  daVinfoList;
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public List<DaVinfoVO> getDaVinfoList() {
		return daVinfoList;
	}
	public void setDaVinfoList(List<DaVinfoVO> daVinfoList) {
		this.daVinfoList = daVinfoList;
	}

	
	
	
}
