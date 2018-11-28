package com.honglu.quickcall.user.test;

import com.honglu.quickcall.account.facade.business.IAccountOrderService;
import com.honglu.quickcall.user.service.dao.ResourceConfigMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Resource
    private ResourceConfigMapper resourceConfigMapper;


//    @Test
    public void testSend() {
        accountOrderService.checkReceiveOrderByCustomerSkillId(2222L);
        //accountOrderService.checkReceiveOrderByCustomerSkillId(11111L, 2222L);
    }

}
