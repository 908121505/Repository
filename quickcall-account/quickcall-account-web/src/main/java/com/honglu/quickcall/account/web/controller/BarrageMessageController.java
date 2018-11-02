package com.honglu.quickcall.account.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.account.facade.exchange.request.BarrageMessageRequest;
import com.honglu.quickcall.account.facade.exchange.request.BarrageMessageV2Request;
import com.honglu.quickcall.account.facade.exchange.request.FirstBarrageRequest;
import com.honglu.quickcall.account.web.service.AccountCenterService;
import com.honglu.quickcall.common.api.exchange.BaseController;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 弹幕头条Controller
 *
 * @author duanjun
 * @date 2018-10-18 16:55
 */
@Controller
@RequestMapping("/barrage")
public class BarrageMessageController extends BaseController {
    private static Logger LOGGER = LoggerFactory.getLogger(AliPayController.class);

    @Autowired
    private AccountCenterService accountCenterService;

    /**
     * 查询弹幕消息
     */
    @RequestMapping(value = "/getBarrageMessage", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getBarrageMessage(HttpServletRequest request, BarrageMessageRequest params) {
        LOGGER.info(this.getRemoteHost(request) + "accountWeb barrage message request data : " + JSONObject.toJSONString(params));
        WebResponseModel response = accountCenterService.execute(params);
        LOGGER.info("accountWeb barrage message response data : " + JSONObject.toJSONString(response));
        return response;
    }


    /**
     * 查询弹幕消息 -- 第二版本
     * @desc 优化弹幕消息显示内容给客户端（文案客户端拼装）
     * @date 2018-10-31 17:30:00
     */
    @RequestMapping(value = "/getBarrageMessageV2", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getBarrageMessageV2(HttpServletRequest request, BarrageMessageV2Request params) {
        LOGGER.info(this.getRemoteHost(request) + "accountWeb barrage message V2 request data : " + JSONObject.toJSONString(params));
        WebResponseModel response = accountCenterService.execute(params);
        LOGGER.info("accountWeb barrage message V2 response data : " + JSONObject.toJSONString(response));
        return response;
    }

    /**
     * 每个用户每天只弹窗一次
     */
    @RequestMapping(value = "/getPopWindowStatus", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getPopWindowStatus(FirstBarrageRequest request) {
        LOGGER.info("accountWeb first barrage request data : " + JSONObject.toJSONString(request));
        WebResponseModel response = accountCenterService.execute(request);
        LOGGER.info("accountWeb first barrage response data : " + JSONObject.toJSONString(response));
        return response;
    }
}
