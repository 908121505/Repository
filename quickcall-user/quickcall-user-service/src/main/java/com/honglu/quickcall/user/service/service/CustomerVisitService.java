package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.RecentVisitRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetVisitReadRequest;

public interface CustomerVisitService {
    /**
     * 查询最近来访列表
     * @param params
     * @return
     */
    CommonResponse queryRecentVisitList(RecentVisitRequest params);
    
    /**
     * 设置来访为已读
     * @param params
     * @return
     */
    CommonResponse setVisitRead(SetVisitReadRequest params);
    
    
}
