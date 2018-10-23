package com.honglu.quickcall.user.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * MQ测试
 *
 * @author duanjun
 * @date 2018-10-23 10:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMq {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Test
    public void testSend() {
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "测试消息--- = " + i);
            map.put("customerId", 1809282058558244370L);
            map.put("addExperience", 100 + i);
            map.put("MQ_TYPE", 1);
            amqpTemplate.convertAndSend("userCenter-mq-exchange", "queue_userCenter_for_experience_key", map);
        }
    }
}
