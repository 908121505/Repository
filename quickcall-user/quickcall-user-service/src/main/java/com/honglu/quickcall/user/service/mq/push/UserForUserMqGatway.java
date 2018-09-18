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
 * Created by len.song on 2018-01-29.
 */
@Service("userForUserMqGatway")
public class UserForUserMqGatway {
    private final static Logger logger = LoggerFactory.getLogger(UserForUserMqGatway.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    
    private void send(String exchange, String routingKey, Object message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    /**
     * 首页女神缓存mq
     * @param map
     */
    public void getHomePageFirstPage(Map<String,Object> map) {
        logger.info("用户编号为{}，获取首页的第一页后，开始发送mq 进行剩余页数缓存..." + map.get("id"));

        map.put("sendMqTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("mqtype", UserMqType.MQ_HOMEPAGE_CACHE_SUCCESS);
        send("userCenter-mq-exchange", "queue_userCenter_homepage_common_key", map);
        logger.info("用户编号为{}，获取首页的第一页后，发送mq成功..." + map.get("id"));
    }




}
