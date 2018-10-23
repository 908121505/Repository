package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;

/**
 * 客户获取经验值接口
 *
 * @author duanjun
 * @date 2018-10-23 14:42
 */
public interface CustomerGetExperienceService {

    /**
     * 客户下单消费获取经验值
     * @param request
     */
    void doOrderCast(DoOrderCastMqRequest request);
}
