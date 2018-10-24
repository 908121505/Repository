package com.honglu.quickcall.account.service.mq.push;


import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.code.MqMessageServiceCode;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发送MQ消息 -- 服务类
 *
 * @author duanjun
 * @date 2018-10-23 13:09
 */
@Service("sendMqMessageService")
public class SendMqMessageService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SendMqMessageService.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 发送MQ消息 -- 客户【下单话费】获取经验值
     *
     * @param orderId
     */
    public void sendExperience(Long orderId) {
        try {
            DoOrderCastMqRequest request = new DoOrderCastMqRequest();
            request.setOrderId(orderId);
            LOGGER.info("发送MQ消息 --  客户【下单话费】获取经验值: {}", JSON.toJSONString(request));
            amqpTemplate.convertAndSend("userCenter-mq-exchange", "queue_userCenter_for_experience_key", request);
        }catch (Exception e){
            LOGGER.error("发送MQ消息 -- 客户【下单话费】获取经验值异常，orderId : " + orderId + " ， 异常信息：", e);
        }
    }
}
