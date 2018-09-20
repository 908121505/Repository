package com.honglu.quickcall.user.service.mq.pull;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.user.facade.constants.UserMqType;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by len.song on 2018-01-29.
 */
public class UserFromUserMqLitener implements ChannelAwareMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(UserFromUserMqLitener.class);

   

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String json = "";
        try {
            json = new String(message.getBody(), "UTF-8");
            this.logger.info("【UserFromUserMqLitener】 RabbitMQ消息 :" + json);
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
            e.printStackTrace();
            //ack返回false，并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            AMQP.BasicProperties properties = new AMQP.BasicProperties();
            channel.basicPublish("dead-letter-exchange", "queue_userCenter_homepage_key", properties, json.getBytes());
        }
    }
}
