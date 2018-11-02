package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：首页分类列表信息
 * @Package: com.honglu.quickcall.account.facade.resp
 * @author: chenliuguang   
 * @date: 2018年10月18日 上午9:45:28
 */
public class DaVinfoListVO implements  Serializable{
	
	
	private static final long serialVersionUID = -7237402509909241201L;
	/**技能名称*/
	private  String   skillItemName;
	/**技能ID*/
	private  Long   skillItemId;
	/**大V技能列表*/
	private List<DaVinfoVO>  daVinfoList;
	
	
	public String getSkillItemName() {
		return skillItemName;
	}
	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}
	public Long getSkillItemId() {
		return skillItemId;
	}
	public void setSkillItemId(Long skillItemId) {
		this.skillItemId = skillItemId;
	}
	public List<DaVinfoVO> getDaVinfoList() {
		return daVinfoList;
	}
	public void setDaVinfoList(List<DaVinfoVO> daVinfoList) {
		this.daVinfoList = daVinfoList;
	}

	
	
	
}
