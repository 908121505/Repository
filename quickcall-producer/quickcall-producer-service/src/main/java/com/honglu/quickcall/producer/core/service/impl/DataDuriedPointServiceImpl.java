package com.honglu.quickcall.producer.core.service.impl;

import com.alibaba.fastjson.JSON;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(DataDuriedPointService.class);
    public static final String queues = "data-dury-point-queue";
    public static final String EXCHANGE = "data-dury-point-exchange";
    public static final String ROUTINGKEY = "data-dury-point-routingKey";

    @Autowired
    private RabbitSender rabbitSender;

    @Override
    public void buryGetCodeData(DataBuriedPointGetCodeReq req) {
        LOGGER.info("======producer:埋点--获取验证码--组装数据");
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_getCode.getValue());
        LOGGER.info("------producer:埋点--获取验证码--开始发送mq");
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void burySignUpResultData(DataBuriedPointRegistReq req) {
        LOGGER.info("======producer:埋点--注册-组装数据");
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_Sign_up_result.getValue());
        LOGGER.info("------producer:埋点--注册--开始发送mq");
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void buryUserIdLoginResultData(DataBuriedPointLoginReq req) {
        LOGGER.info("======producer:埋点--登陆--组装数据--req:"+req+"--userBean:"+req.getUserBean());
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_User_id_login_result.getValue());
        LOGGER.info("------producer:埋点--登陆--开始发送mq");
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void buryOrderButtonData(DataBuriedPointOrderButtonReq req) {
        LOGGER.info("======producer:埋点--订单按钮--组装数据");
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_Order_button.getValue());
        LOGGER.info("------producer:埋点--订单按钮--开始发送mq");
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void burySubmitOrderData(DataBuriedPointSubmitOrderReq req) {
        LOGGER.info("======producer:埋点--提交订单-组装数据");
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_submitorder.getValue());
        LOGGER.info("------producer:埋点--提交订单-开始发送mq");
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void buryMakeOrderData(BuryMakeOrderReq req) {
        LOGGER.info("======producer:埋点--下单-组装数据");
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_Make_Order.getValue());
        LOGGER.info("------producer:埋点--下单-开始发送mq");
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void burySetPwdDurationData(BurySetPwdDurationReq req) {
        LOGGER.info("======producer:埋点--设置密码页-组装数据");
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_Set_Password_Duration.getValue());
        LOGGER.info("------producer:埋点--设置密码页-开始发送mq");
        sendMessage(JSON.toJSONString(param));
    }

    @Override
    public void buryFirstChargeData(BuryFirstChargeReq req) {
        LOGGER.info("======producer:埋点--首次充值-组装数据");
        Map<String,Object> param = new HashMap<>();
        param.put("param",JSON.toJSONString(req));
        param.put("type", EventEnums.EVENT_First_Charge.getValue());
        LOGGER.info("------producer:埋点--首次充值-开始发送mq");
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
