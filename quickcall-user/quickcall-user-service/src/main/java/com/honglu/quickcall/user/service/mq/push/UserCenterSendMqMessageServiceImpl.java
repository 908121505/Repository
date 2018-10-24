package com.honglu.quickcall.user.service.mq.push;


import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.user.facade.business.UserCenterSendMqMessageService;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.facade.exchange.mqrequest.EvaluationOrderMqRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户中心发送MQ消息 -- 服务类
 *
 * @author duanjun
 * @date 2018-10-23 13:09
 */
@Service("User.UserCenterSendMqMessageService")
public class UserCenterSendMqMessageServiceImpl implements UserCenterSendMqMessageService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserCenterSendMqMessageServiceImpl.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendOrderCostMqMessage(Long orderId) {
        // 发送MQ消息 -- 下单客户获取经验值
        try {
            DoOrderCastMqRequest request = new DoOrderCastMqRequest();
            request.setOrderId(orderId);
            LOGGER.info("发送MQ消息 --  客户【下单话费】获取经验值: {}", JSON.toJSONString(request));
            amqpTemplate.convertAndSend("userCenter-mq-exchange", "queue_userCenter_for_experience_key", request);
        }catch (Exception e){
            LOGGER.error("发送MQ消息 -- 客户【下单话费】获取经验值异常，orderId : " + orderId + " ， 异常信息：", e);
        }

        // 发送MQ消息 -- 计算主播评分排名
        try {
            DoOrderCastMqRequest request = new DoOrderCastMqRequest();
            request.setOrderId(orderId);
            LOGGER.info("发送MQ消息 -- 客户【下单花费】 -- 更新主播排名: {}", JSON.toJSONString(request));
            amqpTemplate.convertAndSend("userCenter-mq-exchange", "queue_userCenter_for_scoreRank_key", request);
        }catch (Exception e){
            LOGGER.error("发送MQ消息 -- 客户【下单花费】 -- 更新主播排名异常，orderId : " + orderId + " ， 异常信息：", e);
        }
    }

    @Override
    public void sendEvaluationOrderScoreRank(Long orderId) {
        try {
            EvaluationOrderMqRequest request = new EvaluationOrderMqRequest();
            request.setOrderId(orderId);
            LOGGER.info("发送MQ消息 -- 客户【评价订单】 -- 更新主播排名: {}", JSON.toJSONString(request));
            amqpTemplate.convertAndSend("userCenter-mq-exchange", "queue_userCenter_for_scoreRank_key", request);
        }catch (Exception e){
            LOGGER.error("发送MQ消息 -- 客户【评价订单】 -- 更新主播排名异常，orderId : " + orderId + " ， 异常信息：", e);
        }
    }
}
