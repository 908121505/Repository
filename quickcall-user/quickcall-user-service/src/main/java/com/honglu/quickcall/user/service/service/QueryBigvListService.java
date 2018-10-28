package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.DaVListBySkillItemIdRequest;
import com.honglu.quickcall.user.facade.exchange.request.FirstPageBigvListRequest;

/**
 * 查询大V列表Service
 *
 * @author duanjun
 * @date 2018-10-25 14:23
 */
public interface QueryBigvListService {

    /**
     * 查询首页大V列表
     *
     * @param request
     * @return
     */
    CommonResponse queryHomeBigvList(FirstPageBigvListRequest request);
    
    /**
     * 查询分类的大V列表
     * @param request
     * @return
     */
    CommonResponse queryClassifyBigvList(DaVListBySkillItemIdRequest request);
}
