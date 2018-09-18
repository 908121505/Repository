package com.honglu.quickcall.account.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.account.facade.exchange.request.ConsumeRequest;
import com.honglu.quickcall.account.facade.exchange.request.GetProductByProductIdRequest;
import com.honglu.quickcall.account.web.service.AccountOrderService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

/**
 * Created by len.song on 2017-12-19.
 */
@Controller
@RequestMapping("/userConsume")
public class ConsumeController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumeController.class);

    @Autowired
    private AccountOrderService accountOrderService;

    /**
     * 视频聊天扣费(闪配) / 语音聊天扣费 / 文字聊天扣费
     * @param request
     * @return
     */
    @RequestMapping(value = "/consume", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel videoConsume(@RequestBody ConsumeRequest request) {
        WebResponseModel response = accountOrderService.execute(request);
        return response;
    }
    
    /**
     * 通过产品ID获取产品信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProductByProductId",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getProductByProductId( GetProductByProductIdRequest request) {
        WebResponseModel response = accountOrderService.execute(request);
        return response;
    }
   

}
