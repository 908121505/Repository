package com.honglu.quickcall.account.service.bussService;

import com.honglu.quickcall.account.facade.exchange.request.DaVListBySkillItemIdRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageDaVinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageSkillinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillInfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillUpdateRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：技能配置综合
 * @Package: com.honglu.quickcall.account.web.core
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:16:34
 */
public interface ISkillBussService {
    /**
     * 获取个人技能信息
     * @param request
     */
	public CommonResponse  querySkillInfoPersonal(SkillInfoRequest  request);
	/**
	 * 修改个人技能信息
	 * @param request
	 */
	public CommonResponse  updateSkillInfoPersonal(SkillUpdateRequest  request);
	
	
	/**
	 *  首页大V技能展示
	 * @param request
	 */
	public CommonResponse  getFirstPageDaVinfo(FirstPageDaVinfoRequest  request);
	/**
	 * 首页技能种类展示
	 * @param request
	 */
	public CommonResponse  getFirstPageSkillItemInfo(FirstPageSkillinfoRequest  request);
	
	/**
	 * 根据技能ID获取该分类下的大V列表
	 * @param request
	 * @return
	 */
	public CommonResponse getDaVListBySkillItemId(DaVListBySkillItemIdRequest request);
}
