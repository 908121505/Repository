package com.honglu.quickcall.consumer.core.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 消息执行
 *
 * @author xiangxianjin
 * @date 2018-04-12 18:46
 */
@Service("rabbitSender")
public class RabbitSender{

    private static final Logger LOGGER = LogManager.getLogger("RabbitSender");

    @Autowired
    @Qualifier("buriedRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送MQ消息的入口
     *
     * @param xnMessage
     */
    public void sendMessage(XPMessage xnMessage){
        LOGGER.info("发送MQ消息，流水号：{}，消息体：{}", xnMessage.getTraceId(), xnMessage.getMessageId());

        /**
         * 重写消息参数实现类，添加traceId(全局唯一流水号)字段
         */
        CorrelationData correlationData = new CorrelationData(xnMessage.getTraceId());
        MessagePostProcessor messagePostProcessor = (Message message) -> {
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("traceId", xnMessage.getTraceId());
            messageProperties.setHeader("businessCode", xnMessage.getBusinessCode());
            messageProperties.setHeader("messageBody", xnMessage.getMessageBody());
            messageProperties.setMessageId(xnMessage.getMessageId());
            messageProperties.setCorrelationId(correlationData.getId().getBytes());
            messageProperties.setCorrelationIdString(correlationData.getId());
            messageProperties.setContentEncoding("UTF-8");
            messageProperties.setContentType("application/json");
            return new Message(xnMessage.getMessageBody().getBytes(), messageProperties);
        };

        // 发送消息
        rabbitTemplate.convertAndSend(xnMessage.getExchangeName(), xnMessage.getRoutingKey(),
                xnMessage.getMessageBody(), messagePostProcessor);
        LOGGER.info("发送MQ消息成功，流水号：{}，消息体：{}", xnMessage.getTraceId(), xnMessage.getMessageId());
    }
}
