package com.honglu.quickcall.account.test;

import com.honglu.quickcall.account.service.mq.push.SendMqMessageService;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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
    private SendMqMessageService sendMqMessageService;

    @Test
    public void testSendExperience() {
        sendMqMessageService.sendExperience(1899282017997784647L);
    }
}
