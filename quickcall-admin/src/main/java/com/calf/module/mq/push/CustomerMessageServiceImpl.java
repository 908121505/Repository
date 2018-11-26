package com.calf.module.mq.push;


import com.alibaba.fastjson.JSON;
import com.calf.module.mq.request.CustomerMessageRequest;
import com.calf.module.mq.service.CustomerMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户中心发送MQ消息 -- 服务类
 *
 * @author duanjun
 * @date 2018-10-23 13:09
 */
@Service
public class CustomerMessageServiceImpl implements CustomerMessageService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerMessageServiceImpl.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 发送MQ消息 -- 给所有用户发送站内消息
     *
     * @param messageId
     */
    @Override
    public void sendInternalMessage(String messageId) {
        try {
            CustomerMessageRequest customerMessageRequest = new CustomerMessageRequest();
            customerMessageRequest.setMessageId(messageId);
            String jsonStr = JSON.toJSONString(customerMessageRequest);
            LOGGER.info("发送MQ消息 --  发送站内信消息: {}", jsonStr);
            amqpTemplate.convertAndSend("admin-mq-exchange", "queue_admin_customer_message_key", customerMessageRequest);
        }catch (Exception e){
            LOGGER.error("发送MQ消息 -- 给所有用户发送站内消息异常，messageId : {}， 异常信息：", messageId, e);
            throw new AmqpRejectAndDontRequeueException("");
        }
    }
}
