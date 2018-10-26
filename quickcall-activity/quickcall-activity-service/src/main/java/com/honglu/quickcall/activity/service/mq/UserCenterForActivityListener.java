package com.honglu.quickcall.activity.service.mq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;


/**
 * Created by len.song on 2018-02-01.
 */
public class UserCenterForActivityListener implements ChannelAwareMessageListener {
    private final static Logger logger = LoggerFactory.getLogger(UserCenterForActivityListener.class);
 
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {/*

    	logger.info("=============UserCenterForActivityListener消费进入=================");
        String json = "";
        try {
            json = new String(message.getBody(), "UTF-8");
            this.logger.info("【UserCenterForActivityListener】 RabbitMQ消息 :" + json);
            this.logger.info("consumer--:"+message.getMessageProperties()+":"+new String(message.getBody()));
            Map<String,Object> map = JSON.parseObject(json);
            int mqtype = Integer.parseInt(map.get("mqtype")+"");
            Integer userId=Integer.parseInt(map.get("userId")+"");
            
            switch (mqtype) {
                case UserMqType.MQ_TYPE_ACTIVITY_EGGS:
                	logger.info("消费成功....userId="+userId);
                    if(userId!=null) {
                    	activityService.smashingEggs(userId);
                    }
                    break;
                default:
                    logger.info("mq消息未走对应方法:参数为"+json);
                    break;
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        }catch (Exception e){
            e.printStackTrace();
            //ack返回false，并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            AMQP.BasicProperties properties = new AMQP.BasicProperties();
            channel.basicPublish("dead-letter-exchange", "dead", properties,
                    json.getBytes());
        }
    */}
}
