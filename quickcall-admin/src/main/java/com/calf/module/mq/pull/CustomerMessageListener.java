package com.calf.module.mq.pull;

import com.alibaba.fastjson.JSON;
import com.calf.module.internal.service.InternalService;
import com.honglu.quickcall.common.api.code.MqMessageServiceCode;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * MQ -- 站内信消息监听者
 *
 * @author xiangxianjin
 * @date 2018-10-24 17:38:00
 */
public class CustomerMessageListener implements ChannelAwareMessageListener {

    private final static Logger LOGGER = Logger.getLogger(CustomerMessageListener.class);

    @Autowired
    private InternalService internalService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String json = "";
        try {
            LOGGER.info("admin后台：" + message.getMessageProperties() + ":" + new String(message.getBody()));

            json = new String(message.getBody(), "UTF-8");
            Map<String, Object> map = JSON.parseObject(json);
            if (map == null) {
                LOGGER.warn("MQ消息转换Map为空-------------");
                return;
            }
            String bizCode = String.valueOf(map.get("bizCode"));
            switch (bizCode) {
                case MqMessageServiceCode.ADMIN_CUSTOMER_MESSAGE:
                    LOGGER.info("开始发送站内信息---------");
                    internalService.sendMessage(String.valueOf(map.get("messageId")));
                    break;
                default:
                    LOGGER.warn("获取到未知服务编码的MQ消息-------------");
                    break;
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            LOGGER.error("mq消息消费异常,UserCenterMqExperienceListener 消息的请求参数为：" + json);
            throw new AmqpRejectAndDontRequeueException("消费异常，不再重复消费，错误消息："+e.getMessage());
        }
    }
}
