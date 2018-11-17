package com.honglu.quickcall.activity.service.service.impl;

import com.honglu.quickcall.activity.facade.vo.CouponOrderVo;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.entity.Message;
import com.honglu.quickcall.user.facade.entity.MessageCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.service.dao.CouponMapper;
import com.honglu.quickcall.activity.service.dao.CustomerCouponMapper;
import com.honglu.quickcall.activity.service.service.CouponDubboService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponDubboServiceImpl implements CouponDubboService{
	
	@Autowired
	private CustomerCouponMapper customerCouponMapper;
	@Autowired
	private CouponMapper couponMapper;
	
	@Override
	public Coupon getCouponByCustomerCouponId(int id) {
		CustomerCoupon customerCoupon = customerCouponMapper.selectByPrimaryKey(id);
		Coupon coupon = couponMapper.selectByPrimaryKey(customerCoupon.getCouponId());
		return coupon;
	}

	@Override
	public int updateCustomerCouponById(CustomerCoupon customerCoupon) {
		return customerCouponMapper.updateByPrimaryKeySelective(customerCoupon);
	}

	@Override
	public CustomerCoupon queryCustomerCouponByCustomerIdAndOrderId(Long customerId, Long orderId) {
		return customerCouponMapper.queryCustomerCouponByCustomerIdAndOrderId(customerId,orderId);
	}

	@Override
	public int cancelUpdateCustomerCoupon(Integer id) {
		return customerCouponMapper.cancelUpdateCustomerCoupon(id);
	}

    /**
     * 根据订单ID查询客户优惠券
     * @return
     */
	@Override
	public Map<String,String> getCustomerCouponByOrderId(Long orderId){
		return customerCouponMapper.getCustomerCouponByOrderId(orderId);
	}

/*	@Override
	public String getCouponIdBySkillItemId(List<String> statusList){
		return customerCouponMapper.getCouponIdBySkillItemId(statusList);
	}

	@Override
	public int getShowTip(String couponId ,String customerId){
		return customerCouponMapper.getShowTip(couponId,customerId);
	}*/
    /**
     * 判断现在时间是否有活动
     * @return
     */
	@Override
    public int getActivityNum(){
        return customerCouponMapper.getActivityNum();
    }

    /**
     * 根据技能ID查询券ID
     * @return
     */
	@Override
    public String getCouponIdBySkillItemId(String skillItemId){
        return customerCouponMapper.getCouponIdBySkillItemId(skillItemId);
    }

    /**
     * 根据客户ID和券ID查询用户券关系数量
     * @return
     */
	@Override
    public CustomerCoupon getCountByCustomerIdAndCouponId(Map<String,String> map){
        return customerCouponMapper.getCountByCustomerIdAndCouponId(map);
    }

	/**
	 * 下单页数据展示优惠券接口用
	 * @param skillItemId 技能ID
	 * @param customerId 客户ID
	 */
	@Override
	public CouponOrderVo showActivityCouponForOrder(String skillItemId, String customerId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("skillItemId",skillItemId);
		map.put("customerId",customerId);
		return customerCouponMapper.showActivityCouponForOrder(map);
	}

	/**
	 * 获取可抵扣的优惠券
	 * @param skillItemId 技能ID
	 * @param customerId 客户ID
	 */
	@Override
	public CouponOrderVo getDeductCoupon(String skillItemId, String customerId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("skillItemId",skillItemId);
		map.put("customerId",customerId);
		return customerCouponMapper.getDeductCoupon(map);
	}

	/**
	 * 下单获取券接口用
	 * @param customerCoupon 客户券
	 * @return0=不成功，1=成功
	 *
	 */
	@Override
	public int insertCustomerCoupon(CustomerCoupon customerCoupon){
		return customerCouponMapper.insertSelective(customerCoupon);
	}

	/**
	 * 发送消息
	 * @param couponId
	 * @param customerId
	 */
	@Override
	public void sendActivityMessage(String couponId, String customerId){
		Map<String,String> mapA = customerCouponMapper.selectActivityNameAndCouponName(Long.parseLong(couponId));
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
		String phone = customerCouponMapper.selectPhoneByCustomerId(customerId);
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
		mc.setPhone(Long.valueOf(phone));
		mc.setReceiverId(Long.valueOf(customerId));
		mc.setMessageId(mid);
		customerCouponMapper.insertSelectiveMessageCustomer(mc);
	}

}