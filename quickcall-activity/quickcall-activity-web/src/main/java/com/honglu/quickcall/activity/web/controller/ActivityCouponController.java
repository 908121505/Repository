package com.honglu.quickcall.activity.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponQueryRequest;
import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponReceiveRequest;
import com.honglu.quickcall.activity.web.service.ActivityCenterService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 活动优惠券查询Controller
 *
 * @author wq
 * @date 2018-10-30
 */
@Controller
@RequestMapping("/activityCoupon")
public class ActivityCouponController {
    private final static Logger logger = LoggerFactory.getLogger(ActivityCouponController.class);

    @Autowired
    private ActivityCenterService activityCenterService;

    /**
     * 活动优惠券查询接口
     * @date 2018-10-30
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryActivityCoupon", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryActivityCoupon(ActivityCouponQueryRequest params) {
        logger.info("activityWeb activityCoupon queryActivityCoupon request data : " + JSONObject.toJSONString(params));
        WebResponseModel response = activityCenterService.execute(params);
        logger.info("activityWeb activityCoupon queryActivityCoupon response data : " + JSONObject.toJSONString(response));
        return response;
    }

    /**
     * 领券接口
     * @date 2018-10-30
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/receiveCoupon", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel receiveCoupon(ActivityCouponReceiveRequest params) {
        logger.info("activityWeb activityCoupon receiveCoupon request data : " + JSONObject.toJSONString(params));
        WebResponseModel response = activityCenterService.execute(params);
        logger.info("activityWeb activityCoupon receiveCoupon response data : " + JSONObject.toJSONString(response));
        return response;
    }

}
