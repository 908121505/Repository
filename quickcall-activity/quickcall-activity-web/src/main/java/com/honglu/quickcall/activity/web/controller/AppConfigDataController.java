package com.honglu.quickcall.activity.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.activity.facade.exchange.request.BannerRequest;
import com.honglu.quickcall.activity.web.service.ActivityCenterService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * App配置数据查询Controller
 *
 * @author duanjun
 * @date 2018-09-21 18:31
 */
@RestController("/appConfigData")
public class AppConfigDataController {
    private final static Logger logger = LoggerFactory.getLogger(AppConfigDataController.class);

    @Autowired
    private ActivityCenterService activityCenterService;

    /**
     * Banner查询接口
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/bannerQuery", method = RequestMethod.POST)
    public WebResponseModel bannerQuery(@RequestBody BannerRequest params) {
        logger.info("activityWeb appConfigData bannerQuery request data : " + JSONObject.toJSONString(params));
        WebResponseModel response = activityCenterService.execute(params);
        logger.info("activityWeb appConfigData bannerQuery response data : " + JSONObject.toJSONString(response));
        return response;
    }
}
