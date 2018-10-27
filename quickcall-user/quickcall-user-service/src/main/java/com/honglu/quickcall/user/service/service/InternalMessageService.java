package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.InternalMessageRequest;

/**
 * @author xiangxianjin
 * @date 2018年10月24日 22点14分
 * @description: 站内信消息查询
 */
public interface InternalMessageService {

    /**
     * 查询所有的站内消息
     * @param internalMessageRequest
     * @return
     */
    CommonResponse queryMessages(InternalMessageRequest internalMessageRequest);
}
