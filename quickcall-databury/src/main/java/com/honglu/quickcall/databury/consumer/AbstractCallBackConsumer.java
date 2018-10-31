package com.honglu.quickcall.databury.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;

/**
 * MQ基类
 *
 * @author xiangpign
 * @date 2018-10-28
 */
public abstract class AbstractConsumer {

    public static final String queues = "data-dury-point-queue";
    public static final String EXCHANGE = "data-dury-point-exchange";
    public static final String ROUTINGKEY = "data-dury-point-routingKey";

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     *
     *
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE);
    }


    @Bean
    public Queue queue() {
        //队列持久
        return new Queue(queues, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(ROUTINGKEY);
    }

    /**
     * 创建简单消息监听器容器
     *
     * @param connectionFactory
     * @param queueNames
     * @return
     */
    public SimpleMessageListenerContainer createRabbitMessageContainer(ConnectionFactory connectionFactory,
                                                           String... queueNames) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(queueNames);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        //设置确认模式手工确认
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 根据情况确认消息
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);

        container.setMessageListener((MessageListener) (message) -> consumerAround(message));

//        container.setMessageListener(new ChannelAwareMessageListener() {
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//                System.out.println("----------------------收到消费消息："+message.getBody());
//            }
//        });
        return container;
    }

    /**
     * 记录日志
     * @param message
     */
    public void consumerAround(Message message){
        try{
            consumer(message);
            //TODO 记录日志
        }catch (Exception e){
            e.printStackTrace();
            //TODO 记录日志
        }
    }

    /**
     * 具体消费消息的业务逻辑方法
     *
     * @param message
     */
    public abstract void consumer(Message message);

}
