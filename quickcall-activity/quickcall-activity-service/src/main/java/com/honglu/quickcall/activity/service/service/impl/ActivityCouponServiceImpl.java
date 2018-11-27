package com.honglu.quickcall.activity.service.service.impl;

import com.honglu.quickcall.activity.facade.code.ActivityBizReturnCode;
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
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.entity.Message;
import com.honglu.quickcall.user.facade.entity.MessageCustomer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 活动优惠券查询接口
 *
 * @author wq
 * @date 2018-10-30
 */
@Service
public class ActivityCouponServiceImpl implements ActivityCouponService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityCouponServiceImpl.class);

    //唯一锁值
    private static final String ACTIVITY_DEFAULT_VALUE = "1";
    //唯一锁存在时间
    private static final Integer ACTIVITY_DEFAULT_TIME_OUT = 2;

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

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(d);

        ActivityCouponVo vo = new ActivityCouponVo();
        vo.setActivityName(activityVo.getActivityName());
        vo.setStartTime(activityVo.getStartTimeStr());
        vo.setEndTime(activityVo.getEndTimeStr());
        vo.setNowTime(nowTime);
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
        //设置分布式锁，防止重复领取
        if(JedisUtil.setnx(RedisKeyConstants.ACTIVITY_RECEIVE_COUPON_KEY + request.getCustomerId(), ACTIVITY_DEFAULT_VALUE, ACTIVITY_DEFAULT_TIME_OUT) == 0){
            LOGGER.info("======================用户频繁操作，进行限制=======================");
            //说明已经操作，本次不进行操作
            throw new BizException(ActivityBizReturnCode.ACTIVITY_REPEAT_RECEIVE_COUPON, "请稍后重试");
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

            Map<String,String> mapA = customerCouponMapper.selectActivityNameAndCouponName(Long.parseLong(request.getCouponId()));
            //发送消息
            StringBuilder builder = new StringBuilder();
            builder.append("您有新的抵扣券可用----恭喜您在【");
            builder.append(mapA.get("activityName"));
            builder.append("】活动中获得了“");
            builder.append(mapA.get("couponName"));
            builder.append("”，可在下单时直接抵扣。");
            //"您有新的抵扣券可用----恭喜您在【XXX】活动中获得了“情感咨询1588抵用券”，可在下单时直接抵扣。";
            RongYunUtil.sendActivityMessage(Long.valueOf(request.getCustomerId()),builder.toString());
            //插入消息数据
            String phone = customerCouponMapper.selectPhoneByCustomerId(request.getCustomerId());
            Message m = new Message();
            Long mid = UUIDUtils.getId();
            m.setMessageId(mid);
            m.setMessageContent(builder.toString());
            m.setTitle("活动获取优惠券");
            m.setType(Byte.parseByte("1"));//0=系统通知,1=活动通知,2=通知消息
            customerCouponMapper.insertSelectiveMessage(m);

            MessageCustomer mc = new MessageCustomer();
            Long mcid = UUIDUtils.getId();
            mc.setId(mcid);
            if(StringUtils.isNotBlank(phone)){
                mc.setPhone(Long.valueOf(phone));
            }
            mc.setReceiverId(Long.valueOf(request.getCustomerId()));
            mc.setMessageId(mid);
            customerCouponMapper.insertSelectiveMessageCustomer(mc);
        }else{
            remap.put("code","1");
            remap.put("msg","领取失败，已经领取过了");
        }

        return ResultUtils.resultSuccess(remap);
    }

}
