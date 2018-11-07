package com.honglu.quickcall.account.service.bussService;

import com.honglu.quickcall.account.facade.exchange.request.ChannelSwitchRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

public interface ChannelSwitchService {
    /**
     * 获取渠道开关状态
     *
     * @return
     * @param request
     */
    CommonResponse getChannelSwitchStatus(ChannelSwitchRequest request);
}
