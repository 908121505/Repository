package com.honglu.quickcall.account.service.bussService;

import com.honglu.quickcall.account.facade.exchange.request.BarrageMessageRequest;
import com.honglu.quickcall.account.facade.exchange.request.BarrageMessageV2Request;
import com.honglu.quickcall.account.facade.exchange.request.FirstBarrageRequest;
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

    /**
     * 获取弹幕消息 - V2
     *
     * @desc 优化弹幕消息显示内容给客户端（文案客户端拼装）
     *
     * @return
     * @param request
     */
    CommonResponse rpopMessage(BarrageMessageV2Request request);

    /**
     * 每个用户每天只弹一次窗口
     *
     * @return
     * @param request
     */
    CommonResponse popWindowOnce(FirstBarrageRequest request);
}
