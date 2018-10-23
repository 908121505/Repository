package com.honglu.quickcall.user.service.mq.push;


import com.honglu.quickcall.common.api.code.MqMessageServiceCode;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by len.song on 2018-01-29.
 */
@Service("userCenterMqSendService")
public class UserCenterMqSendService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserCenterMqSendService.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    private void send(UserCenterRequest request) {
        switch (request.getBizCode()) {
            /** 客户获取经验值 -- 下单花费 **/
            case MqMessageServiceCode.CUSTOMER_EXPERIENCE_ORDER_COST:
                LOGGER.info("获取到客户经验值MQ消息---------");
                amqpTemplate.convertAndSend("userCenter-mq-exchange", "queue_userCenter_for_experience_key", request);
                break;
            default:
                LOGGER.warn("获取到未知服务编码的MQ消息-------------");
                break;
        }
    }

}
