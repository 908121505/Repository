package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.WeiXinRequest;

/**
 * @author xiangping
 * @date 2018-11-08 21:12
 */
public interface WeiXinService {
    /**
     * 获取微信openid
     *
     * @param params 请求参数
     * @return
     */
    CommonResponse getOpenId(WeiXinRequest params);
}
