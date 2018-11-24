package com.honglu.quickcall.task.job.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.task.dao.TaskCustomerCouponMapper;
import com.honglu.quickcall.task.entity.CustomerCoupon;
import com.honglu.quickcall.task.vo.CouponOrderVo;
import com.honglu.quickcall.user.facade.entity.Message;
import com.honglu.quickcall.user.facade.entity.MessageCustomer;
/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单完成发券逻辑
 * @Package: com.honglu.quickcall.task.job.service 
 * @author: chenliuguang   
 * @date: 2018年11月14日 上午10:43:08
 */
@Service("couponService")
public class CouponService {
	
	@Autowired
	private  TaskCustomerCouponMapper   taskCustomerCouponMapper;
	
	
	public Integer getCouponInOrder(Long skillItemId, Long customerId){
		try {
			int num = 0;
			CouponOrderVo cvo = getShowTipForActivity(skillItemId.toString(), customerId.toString());
			int tip = cvo.getShowTip();
			if(tip==1){
				//查出券所有信息
				CouponOrderVo vo = showActivityCouponForOrder(skillItemId.toString(),customerId.toString());//customerId这里暂时不用
				String couponId = "";
				if(vo!=null && vo.getCouponId()!=null){
					couponId = vo.getCouponId();

					CustomerCoupon cc = new CustomerCoupon();
					cc.setCouponId(Long.parseLong(couponId));
					cc.setCustomerId(customerId);
					//Subject currentUser = SecurityUtils.getSubject();
					//cc.setCreateMan(currentUser.getPrincipal().toString());
					cc.setCreateMan("admin");
					cc.setCreateTime(new Date());
					num = insertCustomerCoupon(cc);
					if(num > 0){
						//插入消息记录
						sendActivityMessage(couponId,customerId.toString());
					}
				}
			}
			return num;
		} catch (Exception e) {
			e.printStackTrace();
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
		int activityNum = getActivityNum();
		//现在存在活动
		if(activityNum > 0){
		    //查出该券所有信息-customerId这里暂时不用
			cov = showActivityCouponForOrder(skillItemId,customerId);
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
				Map<String,String> map = new HashMap<String,String>();
				map.put("couponId",couponId);
				map.put("customerId",customerId);
				//查出用户与券的关系信息
				//int num = couponDubboService.getCountByCustomerIdAndCouponId(map);
                CustomerCoupon cc = getCountByCustomerIdAndCouponId(map);
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
    
    
    
    public int getActivityNum(){
        return taskCustomerCouponMapper.getActivityNum();
    }
    
    public CustomerCoupon getCountByCustomerIdAndCouponId(Map<String,String> map){
        return taskCustomerCouponMapper.getCountByCustomerIdAndCouponId(map);
    }
    
    
	
	
	public CouponOrderVo showActivityCouponForOrder(String skillItemId, String customerId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("skillItemId",skillItemId);
		map.put("customerId",customerId);
		return taskCustomerCouponMapper.showActivityCouponForOrder(map);
	}
	
	
	public int insertCustomerCoupon(CustomerCoupon customerCoupon){
		return taskCustomerCouponMapper.insertSelective(customerCoupon);
	}
	
	public void sendActivityMessage(String couponId, String customerId){
		Map<String,String> mapA = taskCustomerCouponMapper.selectActivityNameAndCouponName(Long.parseLong(couponId));
		//发送消息
		StringBuilder builder = new StringBuilder();
		builder.append("您有新的抵扣券可用----恭喜您在【");
		builder.append(mapA.get("activityName"));
		builder.append("】活动中获得了“");
		builder.append(mapA.get("couponName"));
		builder.append("”，可在下单时直接抵扣。");
		//"您有新的抵扣券可用----恭喜您在【XXX】活动中获得了“情感咨询1588抵用券”，可在下单时直接抵扣。";
		RongYunUtil.sendActivityMessage(Long.valueOf(customerId),builder.toString());
		//插入消息数据
		String phone = taskCustomerCouponMapper.selectPhoneByCustomerId(customerId);
		Message m = new Message();
		Long mid = UUIDUtils.getId();
		m.setMessageId(mid);
		m.setMessageContent(builder.toString());
		m.setTitle("活动获取优惠券");
		m.setType(Byte.parseByte("1"));//0=系统通知,1=活动通知,2=通知消息
		taskCustomerCouponMapper.insertSelectiveMessage(m);

		MessageCustomer mc = new MessageCustomer();
		Long mcid = UUIDUtils.getId();
		mc.setId(mcid);
		mc.setPhone(Long.valueOf(phone));
		mc.setReceiverId(Long.valueOf(customerId));
		mc.setMessageId(mid);
		taskCustomerCouponMapper.insertSelectiveMessageCustomer(mc);
	}

}
