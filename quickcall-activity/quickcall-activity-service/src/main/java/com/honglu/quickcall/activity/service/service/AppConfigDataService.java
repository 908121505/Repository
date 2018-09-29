package com.honglu.quickcall.activity.service.service;

import com.honglu.quickcall.activity.facade.exchange.request.BannerRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * App配置数据查询服务接口
 *
 * @author duanjun
 * @date 2018-09-21 17:36
 */
public interface AppConfigDataService {

    /**
     * 查询Banner信息
     *
     * @param request
     * @return
     */
    CommonResponse queryBannerInfo(BannerRequest request);
}
