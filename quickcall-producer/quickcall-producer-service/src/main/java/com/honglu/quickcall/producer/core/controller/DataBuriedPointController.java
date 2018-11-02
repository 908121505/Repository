package com.honglu.quickcall.producer.core.controller;

import com.honglu.quickcall.producer.core.producer.RabbitSender;
import com.honglu.quickcall.producer.core.producer.Sender;
import com.honglu.quickcall.producer.core.producer.XPMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiangping
 * @date 2018-10-28 23:24
 */
@Controller
@RequestMapping("/data")
public class DataBuriedPointController {
    public static final String queues = "data-dury-point-queue";
    public static final String EXCHANGE = "data-dury-point-exchange";
    public static final String ROUTINGKEY = "data-dury-point-routingKey";

    @Autowired
    private RabbitSender rabbitSender;

    @Autowired
    private Sender sender;

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    @ResponseBody
    public Object sendMessage(HttpServletRequest request) {
        // 发送消息
        XPMessage xnMessage = new XPMessage();
        xnMessage.setMessageId("1234");
        xnMessage.setExchangeName(EXCHANGE);
        xnMessage.setRoutingKey(ROUTINGKEY);
        xnMessage.setMessageBody("alsdkfjlasdkjflaksd");
        xnMessage.setBusinessCode("300");
        xnMessage.setTraceId("123456789");
        rabbitSender.sendMessage(xnMessage);
        return "success";
    }

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping(value = "/sendBack", method = RequestMethod.GET)
    @ResponseBody
    public Object sendBack(HttpServletRequest request) {
        // 发送消息
        XPMessage xnMessage = new XPMessage();
        xnMessage.setMessageId("12345654344");
        xnMessage.setExchangeName(EXCHANGE);
        xnMessage.setRoutingKey(ROUTINGKEY);
        xnMessage.setMessageBody("alsdkasdfasdfasdfasdfdkjflaksd");
        xnMessage.setBusinessCode("40012");
        xnMessage.setTraceId("43212341");
        sender.sendMessage(xnMessage);
        return "success";
    }
}
