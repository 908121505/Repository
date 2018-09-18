package com.honglu.quickcall.user.service.mq.push;

import com.honglu.quickcall.user.facade.constants.UserMqType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by len.song on 2018-02-01.
 * 发活动相关mq
 */
@Service("userForActivityMqGatway")
public class UserForActivityMqGatway {
    private final static Logger logger = LoggerFactory.getLogger(UserForUserMqGatway.class);
    @Resource
    private AmqpTemplate amqpTemplate;

    private void send(String exchange, String routingKey, Object message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }


    /**
     * 砸蛋站内信MQ
     */
    public void sendSmashingEggs(Integer userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userId", userId);
        data.put("mqtype", UserMqType.MQ_TYPE_ACTIVITY_EGGS);
        data.put("sendMqTime", sdf.format(new Date()));

        send("userCenter-mq-exchange", "queue_userCenter_forActivity_key",data);
    }
}



