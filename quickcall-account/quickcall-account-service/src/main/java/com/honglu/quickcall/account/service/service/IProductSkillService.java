package com.honglu.quickcall.account.service.service;

import java.util.List;

import com.honglu.quickcall.account.facade.exchange.request.SkillUpdateRequest;
import com.honglu.quickcall.account.facade.vo.CustomerSkillInfoVO;
import com.honglu.quickcall.account.facade.vo.DaVinfoListVO;
import com.honglu.quickcall.account.facade.vo.DaVinfoVO;
import com.honglu.quickcall.account.facade.vo.FirstPageSkillIteminfoVO;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：大V产品相关操作
 * @Package: com.honglu.quickcall.account.core.core
 * @author: chenliuguang   
 * @date: 2018年10月18日 上午10:59:57
 */
public interface IProductSkillService {

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

	/**
	 * 查询所有分类列表
	 * @return
	 */
	List<FirstPageSkillIteminfoVO> selectPartSkill();

	/**
	 * 获取某分类下的大V列表
	 * @return
	 */
	List<DaVinfoVO> getDaVListBySkillId(Long  skillId);

	/***
	 * 个人技能（接单设置）展示
	 * @param customerId
	 * @return
	 */
	CustomerSkillInfoVO querySkillInfoPersonal(Long customerId);

	/**
	 * 
	 * @param request
	 */
	void updateSkillInfoPersonal(SkillUpdateRequest request);
	
	
	
   
}
