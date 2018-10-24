package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.facade.exchange.mqrequest.EvaluationOrderMqRequest;
import com.honglu.quickcall.user.service.service.ScoreRankService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 刷新评分排名表服务接口实现类
 *
 * @author duanjun
 * @date 2018-10-24 19:48
 */
@Service
public class ScoreRankServiceImpl implements ScoreRankService {
    private final static Logger LOGGER = Logger.getLogger(ScoreRankServiceImpl.class);

    @Override
    public void doOrderCast(DoOrderCastMqRequest parseObject) {

    }

    @Override
    public void evaluationOrder(EvaluationOrderMqRequest parseObject) {

    }
}
