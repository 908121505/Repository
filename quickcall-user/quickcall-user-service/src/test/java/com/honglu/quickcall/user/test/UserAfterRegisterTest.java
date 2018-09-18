package com.honglu.quickcall.user.test;

import com.honglu.quickcall.user.service.mq.push.UserForAccountMqGatway;
import com.honglu.quickcall.user.service.mq.push.UserForUserMqGatway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by len.song on 2018-02-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserAfterRegisterTest {
    @Autowired
    private UserForAccountMqGatway userForAccountMqGatway;

    @Test
    public void afterRegisterSendMqTest(){
        userForAccountMqGatway.afterRegister(3);
    }
}
