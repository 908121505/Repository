package com.calf.module.activity.service;

import com.calf.cn.service.BaseManager;
import com.calf.module.activity.vo.ActivityCouponAdminVo;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.internal.entity.Message;
import com.calf.module.internal.entity.MessageCustomer;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.facade.vo.CouponOrderVo;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 客户优惠券
 *
 * @author wq
 * @date 2018-11-15
 */
@Service
@Transactional
public class CustomerCouponService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerCouponService.class);

    @Autowired
    private BaseManager baseManager;
    @Autowired
    private CommonUtilService commonUtilService ;


    /**
     * 取消下单返还券接口
     * @param orderId 订单ID
     * @param customerId 客户ID
     * @return -1=参数空异常,0=不成功，1=成功
     *
     */
    public int cancelOrderBackCoupon(Long orderId, Long customerId){
        int num = 0;
        try {
            if(orderId == null){
                return -1;
            }
            if(customerId == null){
                return -1;
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("orderId",orderId);
            map.put("customerId",customerId);

            CustomerCoupon cc = baseManager.get("CustomerCoupon.getCustomerCouponByOrderIdAndCustomerId",map);
            num = baseManager.update("CustomerCoupon.cancelOrderBackCoupon",map);

            try {
                if(cc!=null){
                    logger.info("admin 取消下单返还券接口cancelOrderBackCoupon-JedisUtil:"+customerId);
                    //领取券，加入redis,超时1天
                    JedisUtil.set(RedisKeyConstants.CUSTOMER_COUPON_STATUS+customerId+":"+cc.getCouponId(),"0",3600*24);
                }
            } catch (Exception e) {
                logger.info("admin 取消下单返还券接口cancelOrderBackCoupon-JedisUtil异常");
                e.printStackTrace();
            }
            return num;
        } catch (Exception e) {
            logger.info("admin 取消下单返还券接口cancelOrderBackCoupon异常");
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 下单获取券接口
     * @param skillItemId 技能ID
     * @param customerId 客户ID
     * @return0=不成功，1=成功
     * 修改接口时通知陈留光，代码要同步到task项目中;activity也要改
     */
    @Transactional(rollbackFor = Exception.class)
    public int getCouponInOrder(Long skillItemId, Long customerId){
        try {
            int num = 0;
            CouponOrderVo cvo = getShowTipForActivity(skillItemId.toString(), customerId.toString());
            int tip = cvo.getShowTip();
            if(tip==1){
                //查出券所有信息
                CouponOrderVo vo = this.showActivityCouponForOrder(skillItemId.toString(),customerId.toString());//customerId这里暂时不用
                String couponId = "";
                if(vo!=null && vo.getCouponId()!=null){
                    couponId = vo.getCouponId();

                    CustomerCoupon cc = new CustomerCoupon();
                    cc.setCouponId(Long.parseLong(couponId));
                    cc.setCustomerId(customerId);
                    cc.setCreateMan(commonUtilService.getCurrUser());
                    cc.setCreateTime(new Date());
                    //num = couponDubboService.insertCustomerCoupon(cc);
                    num = baseManager.insert("CustomerCoupon.insertSelective",cc);

                    if(num > 0){
                        //插入消息记录
                        this.sendActivityMessage(couponId,customerId.toString());
                        try {
                            logger.info("admin 下单获取券接口getCouponInOrder-JedisUtil:"+customerId.toString());
                            //领取券，加入redis,超时1天
                            JedisUtil.set(RedisKeyConstants.CUSTOMER_COUPON_STATUS+customerId+":"+couponId,"0",3600*24);
                        } catch (Exception e) {
                            logger.info("admin 下单获取券接口getCouponInOrder-JedisUtil异常");
                            e.printStackTrace();
                        }

                    }
                }
            }
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("admin 下单获取券接口getCouponInOrder异常");
            return 0;
        }
    }

    /**
     * 查询是否显示活动优惠券提示
     * @param skillItemId
     * @param customerId
     * @return
     *
     */
    public CouponOrderVo getShowTipForActivity(String skillItemId, String customerId){
        CouponOrderVo cov = new CouponOrderVo();
        //0=不展示，1=展示
        int showTip = 0;
        int activityNum = this.getActivityNum();
        //现在存在活动
        if(activityNum > 0){
            //查出该券所有信息-customerId这里暂时不用
            cov = this.showActivityCouponForOrder(skillItemId,customerId);
            //String couponId = couponDubboService.getCouponIdBySkillItemId(skillItemId);
            String couponId = "";
            if(cov!=null && cov.getCouponId()!=null){
                couponId = cov.getCouponId();
            }else{
                cov = new CouponOrderVo();
            }
            //空-没查到，不显示
            if(StringUtils.isBlank(couponId)){
                showTip = 0;
            }else{
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("couponId",couponId);
                map.put("customerId",customerId);
                //查出用户与券的关系信息
                //int num = couponDubboService.getCountByCustomerIdAndCouponId(map);
                CustomerCoupon cc = this.getCountByCustomerIdAndCouponId(map);
                //customer_coupon没查到,显示
                if(cc == null){
                    showTip = 1;
                    //cov.setCouponPrice(vo.getCouponPrice());
                }
            }
        }
        cov.setShowTip(showTip);
        return cov;
    }

    /**
     * 下单页数据展示优惠券接口用
     * @param skillItemId 技能ID
     * @param customerId 客户ID
     */
    public CouponOrderVo showActivityCouponForOrder(String skillItemId, String customerId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("skillItemId",skillItemId);
        map.put("customerId",customerId);
        //return customerCouponMapper.showActivityCouponForOrder(map);
        return baseManager.get("CustomerCoupon.showActivityCouponForOrder", map);

    }

    /**
     * 发送消息
     * @param couponId
     * @param customerId
     */
    public void sendActivityMessage(String couponId, String customerId){
        //Map<String,String> mapA = customerCouponMapper.selectActivityNameAndCouponName(Long.parseLong(couponId));
        ActivityCouponAdminVo acv = new ActivityCouponAdminVo();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("couponId",couponId);
        acv = baseManager.get("CustomerCoupon.selectActivityNameAndCouponName",map);
        //发送消息
        StringBuilder builder = new StringBuilder();
        builder.append("您有新的抵扣券可用----恭喜您在【");
        //builder.append(mapA.get("activityName"));
        builder.append(acv.getActivityName());
        builder.append("】活动中获得了“");
        //builder.append(mapA.get("couponName"));
        builder.append(acv.getCouponName());
        builder.append("”，可在下单时直接抵扣。");
        //"您有新的抵扣券可用----恭喜您在【XXX】活动中获得了“情感咨询1588抵用券”，可在下单时直接抵扣。";
        RongYunUtil.sendActivityMessage(Long.valueOf(customerId),builder.toString());
        //插入消息数据
        //String phone = customerCouponMapper.selectPhoneByCustomerId(customerId);
        Map<String,Object> mapB = new HashMap<String,Object>();
        mapB.put("customerId",customerId);
        String phone = baseManager.get("CustomerCoupon.selectPhoneByCustomerId",mapB);
        Message m = new Message();
        Long mid = UUIDUtils.getId();
        m.setMessageId(mid.toString());
        m.setMessageContent(builder.toString());
        m.setTitle("活动获取优惠券");
        //0=系统通知,1=活动通知,2=通知消息
        m.setType(Byte.parseByte("1"));
        //customerCouponMapper.insertSelectiveMessage(m);

        MessageCustomer mc = new MessageCustomer();
        Long mcid = UUIDUtils.getId();
        mc.setId(mcid.toString());
        if(StringUtils.isNotBlank(phone)){
            mc.setPhone(Long.valueOf(phone));
        }
        mc.setReceiverId(Long.valueOf(customerId));
        mc.setMessageId(mid);
        //customerCouponMapper.insertSelectiveMessageCustomer(mc);
        baseManager.insert("MessageMapper.insertSelective",m);
        baseManager.insert("MessageCustomerMapper.insertSelective",mc);
    }

    /**
     * 判断现在时间是否有活动
     * @return
     */
    public int getActivityNum(){
        return baseManager.get("CustomerCoupon.getActivityNum",new HashMap<String, Object>());
    }

    /**
     * 根据客户ID和券ID查询用户券关系数量
     * @return
     */
    public CustomerCoupon getCountByCustomerIdAndCouponId(Map<String,Object> map){
        return baseManager.get("CustomerCoupon.getCountByCustomerIdAndCouponId", map);
    }


}
