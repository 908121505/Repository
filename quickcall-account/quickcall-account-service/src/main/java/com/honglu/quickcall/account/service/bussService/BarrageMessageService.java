package com.honglu.quickcall.account.service.bussService;

import com.honglu.quickcall.account.facade.exchange.request.BarrageMessageRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 弹幕功能接口
 *
 * @author duanjun
 * @date 2018-10-18 9:10
 */
public interface BarrageMessageService {

    /**
     * 下单成功 -- 入队弹幕消息
     *
     * @param orderId
     */
    void lpushMessage(Long orderId);

    /**
     * 获取弹幕消息
     *
     * @return
     * @param request
     */
    CommonResponse rpopMessage(BarrageMessageRequest request);
}
