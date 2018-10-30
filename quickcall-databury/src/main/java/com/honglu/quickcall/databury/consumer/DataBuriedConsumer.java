package com.honglu.quickcall.databury.consumer;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataBuriedConsumer {
//    public static final String queues = "data-dury-point-queue";
//    public static final String EXCHANGE = "data-dury-point-exchange";
//    public static final String ROUTINGKEY = "data-dury-point-routingKey";
//
//    @Bean("simpleListenerContainer")
//    public SimpleMessageListenerContainer createDetectModelListenerContainer(@Qualifier("buriedConnectionFactory") ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        // 监听的队列
//        container.setQueueNames(queues);
//        // 根据情况确认消息
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        container.setMessageListener((MessageListener) (message) -> consumer(message));
//        return container;
//    }
//
//    @RabbitListener(queues = queues,containerFactory = "simpleListenerContainer")
//    public void consumer(Message message) {
//        System.out.println("-----------DataBuryConsumer收到消费信息:"+message);
//    }
}