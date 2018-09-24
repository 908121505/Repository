package com.honglu.quickcall.user.web;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.user.facade.business.UserPushAppMsgBusiness;
import com.honglu.quickcall.user.facade.exchange.request.PushAppMsgRequest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试Dubbo服务
 *
 * @author duanjun
 * @date 2018-09-23 19:19
 */
public class TestDubboService {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext-dubbo-consumer.xml");
        context.start();

        UserPushAppMsgBusiness userPushAppMsgBusiness = (UserPushAppMsgBusiness) context.getBean("userPushAppMsgBusiness");
        PushAppMsgRequest request = new PushAppMsgRequest();

        CommonResponse response = userPushAppMsgBusiness.excute(request);

        System.out.println("请求完成时间：" + DateUtils.getCurrentDateTime());
    }
}
