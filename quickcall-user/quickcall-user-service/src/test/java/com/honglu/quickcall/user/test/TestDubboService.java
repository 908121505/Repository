package com.honglu.quickcall.user.test;

import com.honglu.quickcall.account.facade.business.IAccountOrderService;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Dubbo 服务测试类
 *
 * @author duanjun
 * @date 2018-10-23 10:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDubboService {

    @Resource
    private IAccountOrderService accountOrderService;

    @Test
    public void testSend() {
        accountOrderService.checkReceiveOrderByCustomerSkillId(2222L);
    }
}
