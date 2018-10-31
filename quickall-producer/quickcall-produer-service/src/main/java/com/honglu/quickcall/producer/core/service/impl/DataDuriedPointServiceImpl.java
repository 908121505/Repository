package com.honglu.quickcall.producer.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.producer.core.buisness.DataDuriedPointBusinessImpl;
import com.honglu.quickcall.producer.core.enums.EventEnums;
import com.honglu.quickcall.producer.core.producer.RabbitSender;
import com.honglu.quickcall.producer.core.producer.XPMessage;
import com.honglu.quickcall.producer.core.service.DataDuriedPointService;
import com.honglu.quickcall.producer.core.utils.UUIDUtils;
import com.honglu.quickcall.producer.facade.req.databury.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangping
 * @date 2018-10-30 16:16
 */
@Service("dataDuriedPointService")
public class DataDuriedPointServiceImpl implements DataDuriedPointService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataDuriedPointBusinessImpl.class);
    public static final String queues = "data-dury-point-queue";
    public static final String EXCHANGE = "data-dury-point-exchange";
    public static final String ROUTINGKEY = "data-dury-point-routingKey";

    @Autowired
    private RabbitSender rabbitSender;

    @Override
    public void buryGetCodeData(DataBuriedPointGetCodeReq req) {
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_getCode.getValue());
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void burySignUpResultData(DataBuriedPointRegistReq req) {
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_Sign_up_result.getValue());
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void buryUserIdLoginResultData(DataBuriedPointLoginReq req) {
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_User_id_login_result.getValue());
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void buryOrderButtonData(DataBuriedPointOrderButtonReq req) {
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_Order_button.getValue());
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void burySubmitOrderData(DataBuriedPointSubmitOrderReq req) {
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_submitorder.getValue());
        sendMessage(JSON.toJSONString(param));
    }

    /**
     * 发送mq
     * @param body
     */
    public void sendMessage(String body){
        XPMessage xnMessage = new XPMessage();
        xnMessage.setMessageId(UUIDUtils.getUUID());
        xnMessage.setExchangeName(EXCHANGE);
        xnMessage.setRoutingKey(ROUTINGKEY);
        xnMessage.setMessageBody(body);
        xnMessage.setBusinessCode("300");
        xnMessage.setTraceId(UUIDUtils.getUUID());
        rabbitSender.sendMessage(xnMessage);
    }
}
