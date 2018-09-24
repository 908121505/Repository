package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.PushAppMsgJobRequest;
import com.honglu.quickcall.user.facade.exchange.request.PushAppMsgRequest;

/**
 * 推送APP消息服务
 *
 * @author duanjun
 * @date 2018-09-23 17:24
 */
public interface PushAppMsgService {

    /**
     * 推送用户消息到APP端
     *
     * @param request
     * @return
     */
    CommonResponse pushMsg(PushAppMsgRequest request);

    /**
     * 推送消息Job任务
     *
     * @param request
     * @return
     */
    CommonResponse pushMsgJob(PushAppMsgJobRequest request);
}
