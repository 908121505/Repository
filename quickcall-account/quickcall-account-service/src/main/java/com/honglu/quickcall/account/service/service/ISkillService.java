package com.honglu.quickcall.account.service.service;

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
 * @Package: com.honglu.quickcall.account.web.service 
 * @author: chenliuguang   
 * @date: 2018年9月22日 下午3:16:34
 */
public interface ISkillService {
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
	public CommonResponse  getFirstPageSkillinfo(FirstPageSkillinfoRequest  request);
}
