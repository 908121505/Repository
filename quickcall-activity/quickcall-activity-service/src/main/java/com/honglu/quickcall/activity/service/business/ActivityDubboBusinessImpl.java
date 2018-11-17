package com.honglu.quickcall.activity.service.business;

import com.honglu.quickcall.activity.facade.code.ActivityFunctionType;
import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponQueryRequest;
import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponReceiveRequest;
import com.honglu.quickcall.activity.facade.exchange.request.BannerRequest;
import com.honglu.quickcall.activity.service.service.ActivityCouponService;
import com.honglu.quickcall.activity.service.service.AppConfigDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.activity.facade.business.ActivityDubboBusiness;
import com.honglu.quickcall.activity.facade.code.ActivityBizReturnCode;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BaseException;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * 活动中心Dubbo服务提供者
 *
 * @author duanjun
 */
@Service("Activity.ActivityDubboBusiness")
public class ActivityDubboBusinessImpl implements ActivityDubboBusiness {

    private static final Logger logger = LoggerFactory.getLogger(ActivityDubboBusinessImpl.class);

    /**
     * App配置数据查询服务
     **/
    @Autowired
    private AppConfigDataService appConfigDataService;
    @Autowired
    private ActivityCouponService activityCouponService;


    @Override
    public CommonResponse excute(AbstractRequest request) {
        logger.info("请求参数{}", request);
        if (request == null) {
            throw new BizException(BizCode.ParamError, BizCode.ParamError.desc());
        }
        CommonResponse response = new CommonResponse();
        try {
            switch (request.getBizCode()) {
               /* case ActivityFunctionType.welcomeGodOfWealth:
                	response=activityService.welcomeGodOfWealth((WelcomeGodOfWealthRequest)request);
                	break;*/

                /** 接口说明：查询Banner信息 **/
                case ActivityFunctionType.QUERY_BANNER:
                    response = appConfigDataService.queryBannerInfo((BannerRequest) request);
                    break;
                /** 接口说明：活动优惠券查询 **/
                case ActivityFunctionType.ACTIVITY_COUPON_QUERY:
                    response = activityCouponService.queryActivityCoupon((ActivityCouponQueryRequest) request);
                    break;
                /** 接口说明：领券查询 **/
                case ActivityFunctionType.ACTIVITY_COUPON_RECEIVE:
                    response = activityCouponService.receiveCoupon((ActivityCouponReceiveRequest) request);
                    break;
                default:
                    throw new BizException(ActivityBizReturnCode.BizFunctionTypeNotMatch, ActivityBizReturnCode.BizFunctionTypeNotMatch.desc());
            }

        } catch (BaseException e) {
            logger.error("接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
            response.setCode(ActivityBizReturnCode.Unknown);
            response.setMessage(e.getMessage());
        }
        logger.info("返回结果{}", response);
        return response;
    }
}
