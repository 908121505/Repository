package com.honglu.quickcall.user.facade.business;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 用户中心 -- 推送APP端消息Dubbo服务
 *
 * @author duanjun
 * @date 2018-09-23 16:48
 */
public interface UserPushAppMsgBusiness {

    /**
     * 执行任务
     *
     * @param request
     * @return
     */
    CommonResponse excute(AbstractRequest request);

}
