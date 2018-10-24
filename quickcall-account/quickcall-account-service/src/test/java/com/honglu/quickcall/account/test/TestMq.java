package com.honglu.quickcall.account.test;

import com.honglu.quickcall.user.facade.business.UserCenterSendMqMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private UserCenterSendMqMessageService userCenterSendMqMessageService;

    @Test
    public void testSendExperience() {
        userCenterSendMqMessageService.sendOrderCostExperience(1899282017997784647L);
    }
}
