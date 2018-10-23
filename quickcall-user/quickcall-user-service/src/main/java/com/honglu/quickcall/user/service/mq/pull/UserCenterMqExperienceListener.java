package com.honglu.quickcall.user.service.mq.pull;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.code.MqMessageServiceCode;
import com.honglu.quickcall.user.facade.exchange.ExperienceSendMq;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 用户中心MQ -- 经验值消息监听者
 *
 * @author duanjun
 * @date 2018-10-23 11:38:00
 */
public class UserCenterMqExperienceListener implements ChannelAwareMessageListener {
    private final static Logger LOGGER = Logger.getLogger(UserCenterMqExperienceListener.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String json = "";
        try {
            json = new String(message.getBody(), "UTF-8");
            LOGGER.info("【UserCenterMqExperienceListener】 RabbitMQ消息 :" + json);
            LOGGER.info("consumer--:" + message.getMessageProperties() + ":" + new String(message.getBody()));
            ExperienceSendMq mqObj = JSON.parseObject(json, ExperienceSendMq.class);
//            Map<String, Object> map = JSON.parseObject(json);
//            if (mqObj == null) {
//                return;
//            }
//            int bizCode = Integer.parseInt(map.get("MQ_BIZ_CODE") + "");
            switch (mqObj.getBizCode()) {
                /** 客户获取经验值 -- 下单花费 **/
                case MqMessageServiceCode.CUSTOMER_EXPERIENCE_ORDER_COST:
                    LOGGER.info("获取到客户经验值MQ消息---------");
                    break;

                default:
                    LOGGER.warn("获取到未知服务编码的MQ消息-------------");
                    break;
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            LOGGER.error("mq消息消费异常,UserCenterMqExperienceListener 消息的请求参数为：" + json);
            LOGGER.error("ack返回true，并重新回到队列，错误信息为：" + e.getMessage(), e);
            //ack返回false，并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            AMQP.BasicProperties properties = new AMQP.BasicProperties();
            channel.basicPublish("queue_userCenter_for_experience", "queue_userCenter_for_experience_key", properties, json.getBytes());
        }
    }
}
