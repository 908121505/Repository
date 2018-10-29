package com.honglu.quickcall.databury.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * rabbitmq 连接配置
 */
@Configuration
public class AmqpConfig {

    /**
     * 埋点MQ工厂
     *
     * @param host
     * @param port
     * @param username
     * @param password
     * @param virtualHost
     * @return
     */
    @Bean(name = "buriedConnectionFactory")
    @Primary
    public ConnectionFactory firstConnectionFactory(
            @Value("${spring.rabbitmq-risk.host}") String host,
            @Value("${spring.rabbitmq-risk.port}") int port,
            @Value("${spring.rabbitmq-risk.username}") String username,
            @Value("${spring.rabbitmq-risk.password}") String password,
            @Value("${spring.rabbitmq-risk.connection-timeout}") int connectionTimeout,
            @Value("${spring.rabbitmq-risk.virtualHost}") String virtualHost) {
        return getConnectionFactory(host, port, username, password, connectionTimeout, virtualHost);
    }

    /**
     * 埋点mq
     *
     * 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
     *
     * @param connectionFactory
     * @return
     */
    @Bean(name = "buriedRabbitTemplate")
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate backendRabbitTemplate(
            @Qualifier("buriedConnectionFactory") ConnectionFactory connectionFactory) {
        return getRabbitTemplate(connectionFactory);
    }

    /**
     * 根据mq 的参数创建连接工厂
     * 支持多个mq环境
     *
     * @param host
     * @param port
     * @param username
     * @param password
     * @param virtualHost
     * @return
     */
    private ConnectionFactory getConnectionFactory(String host, int port, String username, String password, int connectionTimeout, String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setConnectionTimeout(connectionTimeout);
        connectionFactory.setVirtualHost(virtualHost);
        //如果需要回调就设置为true
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 提供连接工厂创建rabbitmq模块
     *
     * @param connectionFactory
     * @return
     */
    private RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
//        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
