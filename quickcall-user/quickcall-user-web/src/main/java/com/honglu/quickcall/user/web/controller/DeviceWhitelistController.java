package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.QueryDeviceWhitelistReq;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.SaveDeviceWhitelistReq;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 设备白名单
 *
 * @author chenpeng
 * @date 2018/11/2 14:36
 */
@RestController
@RequestMapping(value = "/device/whitelist")
public class DeviceWhitelistController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceWhitelistController.class);

    @Autowired
    private UserCenterService userCenterService;

    /**
     * 查询设备白名单列表
     *
     * @param params
     * @return
     */
   /* @PostMapping(value = "/queryDeviceWhitelist")
    public WebResponseModel queryBlacklist(QueryDeviceWhitelistReq params) {
        logger.info("查询设备白名单列表 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }*/

    /**
     * 添加设备白名单
     *
     * @param params
     * @return
     */
   /* @PostMapping(value = "/saveDeviceWhitelist")
    public WebResponseModel saveBlacklist(SaveDeviceWhitelistReq params) {
        logger.info("添加设备白名单列表 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }*/
}
