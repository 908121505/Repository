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
     * 检查用户是否存在
     * @param params
     * @return
     */
    @RequestMapping(value = "/regUserExist",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel regUserExist( IsPhoneExistsRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    
    /**
     * 登录
     * @param params
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel login(UserLoginRequest params) {
    	
    	 WebResponseModel response=new WebResponseModel();
    	 return response;
    }
    /**
     * 设置密码
     * @param params
     * @return
     */
    @RequestMapping(value = "/setPwd",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel setpwd( SetPwdRequest params) {
        WebResponseModel response = new WebResponseModel();
        return response;
    }
    /**
     * 找回密码
     * @param params
     * @return
     */
    @RequestMapping(value = "/findPwd",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel findPwd( FindPwdRequest params) {
        WebResponseModel response = new WebResponseModel();
        return response;
    }
    
    /**
     * 找回密码
     * @param params
     * @return
     */
    @RequestMapping(value = "/setHeardUrl",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel setHeardUrl( SetHeardUrlRequest params) {
        WebResponseModel response = new WebResponseModel();
        return response;
    }
    
   /* *//**
     * 退出登录
     * @param params
     * @return
     *//*
    @RequestMapping(value = "/loginout",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel loginout(LoginOutRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response; 
    }*/
    
    
    
    
    
}
