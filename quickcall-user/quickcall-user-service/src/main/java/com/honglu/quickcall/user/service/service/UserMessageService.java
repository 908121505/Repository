package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.UserUnreadMessageNumRequest;

/**
 * 用户消息服务接口
 *
 * @author duanjun
 * @date 2018-09-22 17:26
 */
public interface UserMessageService {

    /**
     * 查询用户未读消息数量
     *
     * @param params 请求参数
     * @return
     */
    CommonResponse queryUserUnreadMessageNum(UserUnreadMessageNumRequest params);
}
