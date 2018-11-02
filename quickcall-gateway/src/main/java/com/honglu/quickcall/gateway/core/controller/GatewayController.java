package com.honglu.quickcall.gateway.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiangping
 * @date 2018-10-31 15:24
 */
@Controller
@RequestMapping("/gate")
public class GatewayController {

    @RequestMapping("/test")
    @ResponseBody
    public Object test(){

        return "succes test";
    }
}
