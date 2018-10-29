package com.honglu.quickcall.databury.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户中心发送MQ消息 -- 服务类
 *
 * @author duanjun
 * @date 2018-10-23 13:09
 */
@Service("sender")
public class Sender implements RabbitTemplate.ConfirmCallback{
    private final static Logger LOGGER = LoggerFactory.getLogger(Sender.class);
    public static final String queues = "data-dury-point-queue";
    public static final String EXCHANGE = "data-dury-point-exchange";
    public static final String ROUTINGKEY = "data-dury-point-routingKey";

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入
     */
    @Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendMessage(XPMessage message) {
        LOGGER.info("发送MQ消息，流水号：{}，消息体：{}", message.getTraceId(), message.getMessageId());
        /**
         * 重写消息参数实现类，添加traceId(全局唯一流水号)字段
         */
        CorrelationData correlationData = new CorrelationData(message.getTraceId());
        // 发送消息
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTINGKEY, message,correlationData);

        LOGGER.info("发送MQ消息成功，流水号：{}，消息体：{}", message.getTraceId(), message.getMessageId());
    }

    /**
     * 回调
     * 如果需要回调：
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + cause);
        }
    }
}
