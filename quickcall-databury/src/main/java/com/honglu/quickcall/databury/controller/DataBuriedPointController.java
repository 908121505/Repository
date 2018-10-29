package com.honglu.quickcall.databury.controller;

import com.honglu.quickcall.databury.producer.RabbitSender;
import com.honglu.quickcall.databury.producer.Sender;
import com.honglu.quickcall.databury.producer.XPMessage;
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
    private static final String encoding = "UTF-8";
    private static final String queue = "queue-dataBuried";
    private static final String exchange = "dataBuried-exchange";
    private static final String routingKey = "dataBuried-routing-key";

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
        xnMessage.setExchangeName(exchange);
        xnMessage.setRoutingKey(routingKey);
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
        xnMessage.setMessageId("1234");
        xnMessage.setMessageBody("alsdkfjlasdkjflaksd");
        xnMessage.setBusinessCode("300");
        xnMessage.setTraceId("123456789");
        sender.sendMessage(xnMessage);
        return "success";
    }
}
