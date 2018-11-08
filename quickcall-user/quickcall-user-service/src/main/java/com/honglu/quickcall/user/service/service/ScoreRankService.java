package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.facade.exchange.mqrequest.EvaluationOrderMqRequest;

/**
 * 刷新评分排名表服务接口
 *
 * @author duanjun
 * @date 2018-10-24 19:47
 */
public interface ScoreRankService {

    /**
     * 客户下单消费 -- 更新主播评分排名表
     *
     * @param request
     */
    void doOrderCast(DoOrderCastMqRequest request);

    /**
     * 客户评价订单 -- 更新主播评分排名表
     * @param request
     */
    void evaluationOrder(EvaluationOrderMqRequest request);

    /**
     * 初始化大V评分排名数据
     * @return
     */
    CommonResponse initBigvScoreRankData();
}
