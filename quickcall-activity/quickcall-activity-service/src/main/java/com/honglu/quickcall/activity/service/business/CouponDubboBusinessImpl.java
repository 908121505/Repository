package com.honglu.quickcall.activity.service.business;

import com.honglu.quickcall.activity.facade.vo.CouponOrderVo;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.entity.Message;
import com.honglu.quickcall.user.facade.entity.MessageCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.activity.facade.business.CouponDubboBusiness;
import com.honglu.quickcall.activity.facade.entity.Coupon;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.service.service.CouponDubboService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import javax.security.auth.Subject;
import java.util.Date;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

@Service("Activity.CouponDubboBusiness")
public class CouponDubboBusinessImpl implements CouponDubboBusiness{
	
	@Autowired
	private CouponDubboService couponDubboService;

	@Override
	public Coupon getCouponByCustomerCouponId(int id) {
		return couponDubboService.getCouponByCustomerCouponId(id);
	}

	@Override
	public int updateCustomerCouponById(CustomerCoupon customerCoupon) {
		return couponDubboService.updateCustomerCouponById(customerCoupon);
	}

	/**
	 * 查询用户此订单是否使用优惠券
	 * @param customerId
	 * @param orderId
	 * @return
	 */
	@Override
	public CustomerCoupon queryCustomerCouponByCustomerIdAndOrderId(Long customerId, Long orderId){
		return couponDubboService.queryCustomerCouponByCustomerIdAndOrderId(customerId, orderId);
	}

	/**
	 * 退回优惠券
	 * @param id
	 * @return
	 */
	@Override
	public int cancelUpdateCustomerCoupon(Integer id){
		return couponDubboService.cancelUpdateCustomerCoupon(id);
	}

	/**
	 * 根据订单ID查询客户优惠券
	 * @return
	 */
	@Override
	public Map<String,String> getCustomerCouponByOrderId(Long orderId){
		return couponDubboService.getCustomerCouponByOrderId(orderId);
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
		int activityNum = couponDubboService.getActivityNum();
		//现在存在活动
		if(activityNum > 0){
		    //查出该券所有信息-customerId这里暂时不用
			cov = couponDubboService.showActivityCouponForOrder(skillItemId,customerId);
			//String couponId = couponDubboService.getCouponIdBySkillItemId(skillItemId);
            String couponId = "";
            if(cov!=null && cov.getCouponId()!=null){
                couponId = cov.getCouponId();
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
                CustomerCoupon cc = couponDubboService.getCountByCustomerIdAndCouponId(map);
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
     * 获取可抵扣的优惠券
     * @param skillItemId
     * @param customerId
     * @return
     *
     */
    public CouponOrderVo getDeductCoupon(String skillItemId, String customerId){
        CouponOrderVo vo = couponDubboService.getDeductCoupon(skillItemId,customerId);
        return vo;
    }

	/**
	 * 下单页数据展示优惠券接口用
	 * @param skillItemId 技能ID
	 * @param customerId 客户ID
	 */
	@Override
	public CouponOrderVo showActivityCouponForOrder(String skillItemId, String customerId){
		//CouponOrderVo vo = couponDubboService.showActivityCouponForOrder(skillItemId, customerId);
		//int showTip = getShowTipForActivity(skillItemId, customerId);
		//vo.setShowTip(showTip);
		/*String str = "<a><font color=\"#FFFFFF\" font-size=\"12pt\" line-height=\"16.5pt\">下单即可获得</font>" +
                "<font color=\"#FFFFFF\" font-size=\"18pt\" line-height=\"22pt\">"+vo.getCouponPrice()+"</font>" +
                "<font color=\"#FFFFFF\" font-size=\"12pt\" line-height=\"16.5pt\">音符</font>" +
                "<font color=\"#FFFFFF\" font-size=\"12pt\" line-height=\"16.5pt\">抵扣券</font><a>";*/
		//vo.setTipHtml(str);
        CouponOrderVo vo = getShowTipForActivity(skillItemId, customerId);
        CouponOrderVo dvo = getDeductCoupon(skillItemId, customerId);
        if(dvo != null){
            vo.setCustomerCouponId(dvo.getCustomerCouponId());
            //vo.setCouponId(dvo.getCouponId());
            //vo.setCouponName(dvo.getCouponName());
            vo.setCouponDeductPrice(dvo.getCouponDeductPrice());
        }
		return vo;
	}

	/**
	 * 下单获取券接口用
	 * @param skillItemId 技能ID
	 * @param customerId 客户ID
	 * @return0=不成功，1=成功
	 *
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int getCouponInOrder(Long skillItemId, Long customerId){
		try {
			int num = 0;
			CouponOrderVo cvo = getShowTipForActivity(skillItemId.toString(), customerId.toString());
			int tip = cvo.getShowTip();
			if(tip==1){
				//查出券所有信息
				CouponOrderVo vo = couponDubboService.showActivityCouponForOrder(skillItemId.toString(),customerId.toString());//customerId这里暂时不用
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
					num = couponDubboService.insertCustomerCoupon(cc);
					if(num > 0){
						//插入消息记录
						couponDubboService.sendActivityMessage(couponId,customerId.toString());
					}
				}
			}
			return num;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
