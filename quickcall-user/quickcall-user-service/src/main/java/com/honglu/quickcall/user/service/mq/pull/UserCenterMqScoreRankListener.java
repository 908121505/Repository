package com.honglu.quickcall.user.service.mq.pull;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.code.MqMessageServiceCode;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.facade.exchange.mqrequest.EvaluationOrderMqRequest;
import com.honglu.quickcall.user.service.service.CustomerGetExperienceService;
import com.honglu.quickcall.user.service.service.ScoreRankService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 用户中心MQ -- 评分排名消息监听者
 *
 * @author duanjun
 * @date 2018-10-23 11:38:00
 */
public class UserCenterMqScoreRankListener implements ChannelAwareMessageListener {
    private final static Logger LOGGER = Logger.getLogger(UserCenterMqScoreRankListener.class);

    @Autowired
    private ScoreRankService scoreRankService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String json = "";
        try {
            json = new String(message.getBody(), "UTF-8");
            LOGGER.info("【UserCenterMqScoreRankListener】 RabbitMQ消息 :" + json);
            LOGGER.info("consumer--:" + message.getMessageProperties() + ":" + new String(message.getBody()));
            Map<String, Object> map = JSON.parseObject(json);
            if (map == null) {
                LOGGER.warn("MQ消息转换Map为空-------------");
                return;
            }
            String bizCode = map.get("bizCode") + "";
            switch (bizCode) {
                /** 主播更新评分排名 -- 下单消费完成 **/
                case MqMessageServiceCode.CUSTOMER_EXPERIENCE_ORDER_COST:
                    LOGGER.info("主播更新评分排名 -- 下单消费完成 - MQ消息---------");
                    scoreRankService.doOrderCast(JSON.parseObject(json, DoOrderCastMqRequest.class));
                    break;
                /** 主播更新评分排名 -- 客户评价订单 **/
                case MqMessageServiceCode.CUSTOMER_EVALUATION_ORDER:
                    LOGGER.info("主播更新评分排名 -- 客户评价订单 - MQ消息---------");
                    scoreRankService.evaluationOrder(JSON.parseObject(json, EvaluationOrderMqRequest.class));
                    break;
                default:
                    LOGGER.warn("获取到未知服务编码的MQ消息-------------");
                    break;
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            LOGGER.error("mq消息消费异常,UserCenterMqScoreRankListener 消息的请求参数为：" + json);
            LOGGER.error("ack返回true，并重新回到队列，错误信息为：" + e.getMessage(), e);
            //ack返回false，并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            AMQP.BasicProperties properties = new AMQP.BasicProperties();
            channel.basicPublish("queue_userCenter_for_scoreRank", "queue_userCenter_for_scoreRank_key", properties, json.getBytes());
        }
    }
}
