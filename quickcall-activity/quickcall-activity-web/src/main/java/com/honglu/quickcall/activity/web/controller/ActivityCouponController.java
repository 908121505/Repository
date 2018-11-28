package com.honglu.quickcall.activity.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponQueryRequest;
import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponReceiveRequest;
import com.honglu.quickcall.activity.facade.exchange.request.CacheCouponQueryRequest;
import com.honglu.quickcall.activity.web.service.ActivityCenterService;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;

import java.util.ArrayList;
import java.util.List;

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
     * 活动优惠券查询接口
     * @date 2018-10-30
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryCacheCoupon", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryCacheCoupon(CacheCouponQueryRequest params) {
        logger.info("activityWeb cacheCoupon queryCacheCoupon request data : " + JSONObject.toJSONString(params));
        List<String> status = new ArrayList<>();
        for (String couponId : params.getCouponId()) {
        	couponId = couponId.replace("[", "");
        	couponId = couponId.replace("]", "");
        	couponId = couponId.replaceAll("\"","");
        	String s = JedisUtil.get(RedisKeyConstants.CUSTOMER_COUPON_STATUS+params.getCustomerId()+":"+couponId);
        	status.add(s);
		}
        WebResponseModel response = new WebResponseModel();
        CommonResponse $response = ResultUtils.resultSuccess(status);
        response.setCode($response.getCode().code());
		response.setMsg($response.getMessage());
		response.setData(JSON.toJSONString($response.getData()));
        logger.info("activityWeb cacheCoupon queryCacheCoupon response data : " + JSONObject.toJSONString(response));
        
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
