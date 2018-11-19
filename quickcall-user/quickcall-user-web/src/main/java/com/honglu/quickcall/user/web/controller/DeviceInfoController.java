package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.DeviceInfoRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: xieshiqiang
 * @Date: 2018/11/19 17:50
 * @Description: 设备信息
 */
@Controller
@RequestMapping("/device")
public class DeviceInfoController {

    private static Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);
    @Autowired
    private UserCenterService userCenterService;

    /**
     * 增加或者更新设备信息
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateDeviceInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel saveOrUpdateDeviceInfo(DeviceInfoRequest params) {
        logger.info("userWeb.DeviceInfoController saveDeviceInfo request data : " + params);
        WebResponseModel response = userCenterService.execute(params);
        logger.info("userWeb.DeviceInfoController saveDeviceInfo response data : " + response);
        return response;
    }
}
