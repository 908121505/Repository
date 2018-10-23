package com.honglu.quickcall.user.test;

import com.honglu.quickcall.common.api.code.MqMessageServiceCode;
import com.honglu.quickcall.user.facade.exchange.ExperienceSendMq;
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
//        for (int i = 1; i <= 10; i++) {
            ExperienceSendMq experienceSendMq = new ExperienceSendMq(MqMessageServiceCode.CUSTOMER_EXPERIENCE_ORDER_COST);
            experienceSendMq.setCustomerId(1809282058558244370L);
            experienceSendMq.setExperience(100);
            amqpTemplate.convertAndSend("userCenter-mq-exchange", "queue_userCenter_for_experience_key", experienceSendMq);
//        }
    }
}