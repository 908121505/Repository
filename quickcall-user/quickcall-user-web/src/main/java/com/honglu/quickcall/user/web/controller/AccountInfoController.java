package com.honglu.quickcall.user.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;

/**
 * @author liuyinkai
 */
@Controller
@RequestMapping("/account/info")
public class AccountInfoController {
	private static Logger logger = LoggerFactory.getLogger(AccountInfoController.class);

    @Autowired
    UserCenterService userCenterService;

    /**
     *查询个人信息
     */
    @RequestMapping(value = "/queryPersonInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryPersonInfo( PersonInfoRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
}
