package com.honglu.quickcall.consumer.core.consumer;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.consumer.core.enums.EventEnums;
import com.honglu.quickcall.consumer.core.service.DataBuriedPointService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Qmqp Rabbitmq
 * 
 * http://docs.spring.io/spring-amqp/docs/1.4.5.RELEASE/reference/html/
 * 
 * @author lkl
 * @version $Id: AmqpConfig.java, v 0.1 2015年11月01日 下午2:05:37 lkl Exp $
 */
 
@Component
public class DataBuriedPointCustomer {

    public static final String queues = "data-dury-point-queue";
    public static final String exchange = "data-dury-point-exchange";
    public static final String routingKey = "data-dury-point-routingKey";

    public static final String encoding = "UTF-8";
    public static final String deal_exchange = "data-dury-point-exchange";
    public static final String deal_routingKey = "data-dury-point-routingKey";

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBuriedPointCustomer.class);

    @Autowired
    @Qualifier("buriedConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Autowired
    private DataBuriedPointService dataBuriedPointService;

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
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue queue() {
        //队列持久
        return new Queue(queues, true);

    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(routingKey);
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        //设置确认模式手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 根据情况确认消息
        /*container.setAcknowledgeMode(AcknowledgeMode.AUTO);*/
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                //确认消息成功消费
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                //消费message
                consumerMessage(message,channel);
            }
        });
        return container;
    }

    public void consumerMessage(Message message, Channel channel) throws Exception {
        try {
            String json = new String(message.getBody(), encoding);
            LOGGER.info("数据埋点消费端收到--【BuriedPointCustomer】 RabbitMQ消息 :" + json);
            LOGGER.info("consumer--:"+message.getMessageProperties()+":"+ new String(message.getBody()));
            Map<String,Object> data = JSON.parseObject(json);
            String type = (String)data.get("type");
            LOGGER.info("========开始消费=========");
            if (EventEnums.EVENT_getCode.getValue().equals(type)){
                dataBuriedPointService.saveGetCodeData(data);
            }else if (EventEnums.EVENT_Sign_up_result.getValue().equals(type)){
                dataBuriedPointService.saveSignUpResultData(data);
            }else if (EventEnums.EVENT_User_id_login_result.getValue().equals(type)){
                dataBuriedPointService.saveUserIdLoginResultData(data);
            }else if (EventEnums.EVENT_Order_button.getValue().equals(type)){
                dataBuriedPointService.saveOrderButtonData(data);
            }else if (EventEnums.EVENT_submitorder.getValue().equals(type)){
                dataBuriedPointService.saveSubmitOrderData(data);
            }else if (EventEnums.EVENT_Make_Order.getValue().equals(type)){
                dataBuriedPointService.buryMakeOrderData(data);
            }else if (EventEnums.EVENT_First_Charge.getValue().equals(type)){
                dataBuriedPointService.buryFirstChargeData(data);
            }else if (EventEnums.EVENT_Set_Password_Duration.getValue().equals(type)){
                dataBuriedPointService.burySetPwdDurationData(data);
            }
        }catch (Exception e){
            e.printStackTrace();
            //ack返回false，并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            /*AMQP.BasicProperties properties = new AMQP.BasicProperties();
            channel.basicPublish(deal_exchange, deal_routingKey, properties,json.getBytes());*/
        }
    }
}