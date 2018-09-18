package com.honglu.quickcall.activity.web.controller;

import com.honglu.quickcall.activity.web.service.ActivityCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author liuqiugui
 * @date 2017-12-10
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityCenterService activityCenterService;

   
    
}
