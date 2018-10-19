package com.honglu.quickcall.account.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.account.facade.exchange.request.BarrageMessageRequest;
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
}
