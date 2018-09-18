package com.honglu.quickcall.account.service.mq.pull;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by len.song on 2018-02-07.
 * 账户消费 userCommonMq 消息
 */
public class UserCenterForAccountMqLitener implements ChannelAwareMessageListener {
    private final static Logger logger = LoggerFactory.getLogger(UserCenterForAccountMqLitener.class);
   
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String json = "";
        try {
            json = new String(message.getBody(), "UTF-8");
            this.logger.info("【userCenterForAccountMqLitener】 RabbitMQ消息 :" + json);
            this.logger.info("consumer--:"+message.getMessageProperties()+":"+new String(message.getBody()));
            Map<String,Object> map = JSON.parseObject(json);
            if(map == null){
                return;
            }
            int mqtype = Integer.parseInt(map.get("mqtype")+"");
            switch (mqtype) {
               
                default:break;
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        }catch (Exception e) {
            logger.error("mq消息消费异常,userCenterForAccountMqLitener 消息的请求参数为："+ json);
            logger.error("错误信息为："+e.getMessage(),e);
            //ack返回false，并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            AMQP.BasicProperties properties = new AMQP.BasicProperties();
            channel.basicPublish("dead-letter-exchange", "queue_userCenter_common_key", properties, json.getBytes());
        }
    }
}
