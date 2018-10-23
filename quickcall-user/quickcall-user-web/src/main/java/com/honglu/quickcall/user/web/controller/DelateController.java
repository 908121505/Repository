package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.DelateInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.DelateInsertRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/delate")
public class DelateController {

    private static final Logger logger = LoggerFactory.getLogger(DelateController.class);

    @Autowired
    private UserCenterService userCenterService;

    @RequestMapping(value = "/getAllDelate",method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getAllDelate(DelateInfoRequest request){
        WebResponseModel responseModel = userCenterService.execute(request);
        return responseModel;
    }

    @RequestMapping(value = "/insertDelate",method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel insertDelate(DelateInsertRequest request){
        WebResponseModel responseModel = userCenterService.execute(request);
        return responseModel;
    }



}
