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
 * Created by len.song on 2018-02-07.
 */
@Service("userForAccountMqGatway")
public class UserForAccountMqGatway {
    private final static Logger logger = LoggerFactory.getLogger(UserForUserMqGatway.class);

    @Resource
    private AmqpTemplate amqpTemplate;


    private void send(String exchange, String routingKey, Object message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    /**
     * 注册完成之后发mq
     * @param userId
     */
    public void afterRegister(Integer userId){
        logger.info("用户编号为：{} 的用户注册完成,开始发送mq",userId);
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("sendMqTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("mqtype", UserMqType.MQ_AFTER_REGISTER);
        send("userCenter-mq-exchange", "queue_userCenter_forAccount_key", map);
        logger.info("用户编号为：{} 的mq发送完成",userId);
    }
}
