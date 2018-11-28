package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.AttentionCancelRequest;
import com.honglu.quickcall.user.facade.exchange.request.AttentionRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiangping
 * @date 2018-11-02 15:22
 */
@Controller
public class AttentionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlacklistController.class);

    @Autowired
    private UserCenterService userCenterService;

    /**
     * 关注
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/attention", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel attention(AttentionRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }

    /**
     * 取消关注
     */
    @RequestMapping(value = "/cancelAttention", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel cancelAttention(AttentionCancelRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }

}
