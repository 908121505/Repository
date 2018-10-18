package com.honglu.quickcall.account.service.service;

import java.util.List;

import com.honglu.quickcall.account.facade.vo.DaVinfoListVO;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：大V产品相关操作
 * @Package: com.honglu.quickcall.account.service.service 
 * @author: chenliuguang   
 * @date: 2018年10月18日 上午10:59:57
 */
public interface IProductService {

	/**
	 * 获取首页资源位：推荐大V列表
	 * @return
	 */
	DaVinfoListVO getResourceDaVinfoList();

	/**
	 * 获取标签位大V列表
	 * @return
	 */
	List<DaVinfoListVO> getTagDaVinfoList();
	
	
	
   
}
