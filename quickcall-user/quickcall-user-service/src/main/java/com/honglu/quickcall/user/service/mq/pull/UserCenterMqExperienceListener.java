package com.honglu.quickcall.user.service.mq.pull;

import com.alibaba.fastjson.JSON;
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
            Map<String, Object> map = JSON.parseObject(json);
            if (map == null) {
                return;
            }
            int mqtype = Integer.parseInt(map.get("mqtype") + "");
            switch (mqtype) {
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            LOGGER.error("mq消息消费异常,UserCenterMqExperienceListener 消息的请求参数为：" + json);
            LOGGER.error("错误信息为：" + e.getMessage(), e);
            //ack返回false，并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            AMQP.BasicProperties properties = new AMQP.BasicProperties();
            channel.basicPublish("queue_userCenter_for_experience", "queue_userCenter_for_experience_key", properties, json.getBytes());
        }
    }
}
