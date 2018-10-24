package com.honglu.quickcall.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.honglu.quickcall.user.facade.business.UserCenterSendMqMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Dubbo服务测试类
 *
 * @author duanjun
 * @date 2018-10-24 10:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//若是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//@WebAppConfiguration
public class DubboServiceTest {

    @Reference(version = "0.0.1", group = "userCenter")
    private UserCenterSendMqMessageService userCenterSendMqMessageService;

    @Test
    public void testUserCenterMqSend(){
        for(int i=1;i<=100;i++){
            userCenterSendMqMessageService.sendOrderCostExperience(1899282017997784647L + i);
        }
    }

}
