package com.honglu.quickcall.activity.service.service.impl;

import com.honglu.quickcall.activity.facade.entity.Activity;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponQueryRequest;
import com.honglu.quickcall.activity.facade.exchange.request.ActivityCouponReceiveRequest;
import com.honglu.quickcall.activity.facade.vo.ActivityCouponVo;
import com.honglu.quickcall.activity.facade.vo.ActivityVo;
import com.honglu.quickcall.activity.facade.vo.CouponVo;
import com.honglu.quickcall.activity.service.dao.ActivityMapper;
import com.honglu.quickcall.activity.service.dao.CouponMapper;
import com.honglu.quickcall.activity.service.dao.CustomerCouponMapper;
import com.honglu.quickcall.activity.service.service.ActivityCouponService;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动优惠券查询接口
 *
 * @author wq
 * @date 2018-10-30
 */
@Service
public class ActivityCouponServiceImpl implements ActivityCouponService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private  CustomerCouponMapper customerCouponMapper;

    @Override
    public CommonResponse queryActivityCoupon(ActivityCouponQueryRequest request) {
        if (request == null) {
            return ResultUtils.resultParamEmpty();
        }
        if (request.getActivityCode() == null) {
            return ResultUtils.resultParamEmpty("活动编码必传");
        }
        ActivityVo activityVo = activityMapper.queryActivityByActivityCode(request.getActivityCode());

        Map<String,String> map = new HashMap<String,String>();
        map.put("activityId",activityVo.getActivityId());
        map.put("customerId",request.getCustomerId());
        List<CouponVo> tList = couponMapper.queryCouponByActiveIdAndCid(map);

        ActivityCouponVo vo = new ActivityCouponVo();
        vo.setActivityName(activityVo.getActivityName());
        vo.setStartTime(activityVo.getStartTimeStr());
        vo.setEndTime(activityVo.getEndTimeStr());
        vo.setCoupons(tList);

        return ResultUtils.resultSuccess(vo);
    }

    @Override
    public CommonResponse receiveCoupon(ActivityCouponReceiveRequest request) {
        if (request == null) {
            return ResultUtils.resultParamEmpty();
        }
        if (request.getCustomerId() == null) {
            return ResultUtils.resultParamEmpty("客户编号必传");
        }
        if (request.getCouponId() == null) {
            return ResultUtils.resultParamEmpty("券编号必传");
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("couponId",request.getCouponId());
        map.put("customerId",request.getCustomerId());
        int num = customerCouponMapper.selectByCustomerIdAndCouponId(map);
        Map<String,String> remap = new HashMap<String,String>();
        if(num == 0){
            CustomerCoupon customerCoupon = new CustomerCoupon();
            customerCoupon.setCouponId(Long.parseLong(request.getCouponId()));
            customerCoupon.setCustomerId(Long.parseLong(request.getCustomerId()));
            customerCouponMapper.insertSelective(customerCoupon);
            remap.put("code","0");
            remap.put("msg","领取成功");
        }else{
            remap.put("code","1");
            remap.put("msg","领取失败，已经领取过了");
        }
        return ResultUtils.resultSuccess(remap);
    }

   /*
    @Override
    public CommonResponse queryBannerInfo(BannerRequest request) {
        if (request == null) {
            return ResultUtils.resultParamEmpty();
        }
        if (request.getBannerType() == null) {
            return ResultUtils.resultParamEmpty("Banner类型必传");
        }
        Banner bannerParms = new Banner();
        bannerParms.setBannerType(request.getBannerType());
        bannerParms.setDeviceType(request.getDeviceType());
        bannerParms.setAppVersionRule(request.getAppVersionRule());
        bannerParms.setAppVersion(request.getAppVersion());

        List<Banner> list = bannerMapper.queryBannerInfo(bannerParms);
        return ResultUtils.resultSuccess(list);
    }*/
}
