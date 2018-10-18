package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.util.List;


/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：首页大V列表技能展示
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月18日 上午9:56:01 
 */
public class FirstPageDaVinfoVO implements  Serializable{

	
	private static final long serialVersionUID = -3055007574006586759L;
	private   List<DaVinfoListVO>   daVinfoList;

	public List<DaVinfoListVO> getDaVinfoList() {
		return daVinfoList;
	}

	public void setDaVinfoList(List<DaVinfoListVO> daVinfoList) {
		this.daVinfoList = daVinfoList;
	}
	
	
	
}
