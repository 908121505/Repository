package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.code.PrivilegeCodeEnum;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.StringUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.exchange.request.*;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by len.song on 2017-12-08.
 */
@Controller
@RequestMapping("/user")
public class UserCommonController {
    private final static Logger logger = LoggerFactory.getLogger(UserCommonController.class);

    @Autowired
    private UserCenterService userCenterService;

    /**
     * 检测手机号是否存在
     * @param params
     * @return
     */
    @RequestMapping(value = "/reg_tel_exist",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel regTelExist( IsPhoneExistsRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    
    
}
