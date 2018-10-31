package com.honglu.quickcall.user.test;

import com.honglu.quickcall.user.facade.business.UserCenterSendMqMessageService;
import com.honglu.quickcall.user.facade.entity.ResourceConfig;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.service.dao.ResourceConfigMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private UserCenterSendMqMessageService userCenterSendMqMessageService;
    @Resource
    private ResourceConfigMapper resourceConfigMapper;

    @Test
    public void testSend() {
//        for (int i = 1; i <= 10; i++) {
            DoOrderCastMqRequest experienceSendMq = new DoOrderCastMqRequest();
            experienceSendMq.setOrderId(1809282102486183353L);
            amqpTemplate.convertAndSend("userCenter-core-exchange", "queue_userCenter_for_experience_key", experienceSendMq);
//        }
    }

    @Test
    public void testSendMqService(){

        List<ResourceConfig> configs = resourceConfigMapper.selectAllResourceConfig();
        System.out.println(configs);
//        userCenterSendMqMessageService.sendEvaluationOrderMqMessage(1809282102486183353L);
    }
}
