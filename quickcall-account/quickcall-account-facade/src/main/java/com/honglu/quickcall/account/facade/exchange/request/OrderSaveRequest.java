package com.honglu.quickcall.account.facade.exchange.request;

import java.math.BigDecimal;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：下单入参
 * 
 * @Package: com.honglu.quickcall.account.facade.exchange.request
 * @author: chenliuguang
 * @date: 2018年9月22日 下午3:39:53
 */
public class OrderSaveRequest extends AbstractRequest {
	
	
	
	private static final long serialVersionUID = 5532497627462839177L;
	/** 客户编号 */
	private Long customerId;
	/** 大V编号 */
	private Long serviceId;
	/** 产品ID */
	private Long customerSkillId;
	/** 订单数量 */
	private Integer orderNum;
	/** 下单备注 */
	private String remark;
	/** 预约时间 */
	private String appointTimeStr;
	/** 用户优惠券Id */
	private Integer customerCouponId;
	/** 券面值 */
	private BigDecimal couponPrice;
	
	//虚拟ID，埋点使用
	private String  virUserId ;
	
	

	public String getVirUserId() {
		return virUserId;
	}

	public void setVirUserId(String virUserId) {
		this.virUserId = virUserId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getCustomerSkillId() {
		return customerSkillId;
	}

	public void setCustomerSkillId(Long customerSkillId) {
		this.customerSkillId = customerSkillId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAppointTimeStr() {
		return appointTimeStr;
	}

	public void setAppointTimeStr(String appointTimeStr) {
		this.appointTimeStr = appointTimeStr;
	}

	public Integer getCustomerCouponId() {
		return customerCouponId;
	}

	public void setCustomerCouponId(Integer customerCouponId) {
		this.customerCouponId = customerCouponId;
	}

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}

	@Override
	public String getBizCode() {
		return OrderRequestType.ORDER_SAVE;
	}

	@Override
	public String toString() {
		return "OrderSaveRequest [customerId=" + customerId + ", serviceId=" + serviceId + ", customerSkillId="
				+ customerSkillId + ", orderNum=" + orderNum + ", remark=" + remark + ", appointTimeStr="
				+ appointTimeStr + "]";
	}

}
