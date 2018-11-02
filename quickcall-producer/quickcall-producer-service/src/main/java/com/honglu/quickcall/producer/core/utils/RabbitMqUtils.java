package com.honglu.quickcall.producer.core.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Rabbit工具类
 *
 * @author xiangping
 * @date 2018-10-30
 */
public final class RabbitMqUtils {

    private static final Logger LOGGER = LogManager.getLogger(RabbitMqUtils.class);

    private static Channel channel;

    public static void setChannel(Channel channel) {
        RabbitMqUtils.channel = channel;
    }

    /**
     * 死信队列信息
     */
    private static Map<String, Object> PARAMS = new HashMap<String, Object>();

    static {
        // 死信队列绑定关系
//        PARAMS.put("x-dead-letter-exchange", RabbitConstants.D_L_E_EXCHANGE);
//        PARAMS.put("x-dead-letter-routing-key", RabbitConstants.D_L_K_ROUTINGKEY);
    }

    /**
     * 获取指定队列未消费的消息数量
     *
     * @param queueName
     * @return
     */
    public static long getUnConsumedMessages(String queueName) {
        try {
            return channel.messageCount(queueName);
        } catch (IOException e) {
            LOGGER.error("获取队列：{}未消费的消息数出现异常：", queueName, e.getMessage());
            return 0;
        }
    }

    /**
     * 创建并绑定指定名字的队列到交换机
     *
     * @param queueName
     * @return
     */
    public static AMQP.Queue.BindOk createAndBindExchangeOnQueue(String exchange, String queueName, String routingKey) {
        try {
            channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC, true);
        } catch (IOException e) {
            LOGGER.error("创建交换机：{}出现异常：{}", exchange, e.getMessage());
        }
        try {
            channel.queueDeclare(queueName, true, false, false, PARAMS);
            return channel.queueBind(queueName, exchange, routingKey);
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = String.format("绑定交换机：%1$s和队列：%2$s出现异常：%3$s", exchange, queueName, e.getMessage());
            throw new AmqpException(errorMessage);
        }
    }

    /**
     * 创建指定名字的队列
     *
     * @param queueName
     * @return
     */
    public static AMQP.Queue.DeclareOk createQueue(String queueName) {
        try {
            return channel.queueDeclare(queueName, true, false, false, PARAMS);
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = String.format("创建队列：%2$s出现异常：%3$s", queueName, e.getMessage());
            throw new AmqpException(errorMessage);
        }
    }

    /**
     * 删除指定名字的队列
     * 只有当队列没用，并且为空的情况才能删除
     *
     * @param queueName
     * @return
     */
    public static AMQP.Queue.DeleteOk deleteQueue(String queueName) {
        try {
            return channel.queueDelete(queueName, true, true);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("删除队列：{}出现异常：", queueName, e.getMessage());
            return null;
        }
    }
}
