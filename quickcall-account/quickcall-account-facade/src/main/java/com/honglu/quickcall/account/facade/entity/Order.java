package com.honglu.quickcall.account.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单信息
 * @Package: com.honglu.quickcall.user.facade.entity 
 * @author: chenliuguang   
 * @date: 2018年10月22日 上午10:18:06
 */
public class Order {
    private Long orderId;

   
    private Long orderNo;

    
    private Long customerSkillId;
    /**技能ID*/
    private Long skillItemId;

   
    private Long customerId;

    
    private Long serviceId;

    
    private Long tradeId;

    
    private Integer paymentType;

    
    private Integer orderNum;

    
    private String serviceUnit;

   
    private BigDecimal servicePrice;

    
    private BigDecimal orderAmounts;

    
    private BigDecimal discountRate;

    
    private Date custCancelTime;
    
    
    private Date appointTime;

    
    private Date systemCancelTime;

    
    private Date receiveOrderTime;

   
    private Date startServiceTime;

    
    private Date appayEndTime;

    
    private Date startTime;

    
    private Date endTime;
    
    private Date expectEndTime;

    
    private Date orderTime;

    
    private Integer orderStatus;
    /**券使用状态0：不适用券  1：使用券  2：使用券订单取消*/
    private Integer couponFlag;

   
    private Date paymentTime;

    
    private Integer customerIsEvaluate;

    
    private String customerMessage;

    
    private Integer evaluateStart;

    
    private String customerEvaluate;

    
    private String orderDescribe;

    /** 价值评价分（用于计算大V排名） **/
    private BigDecimal valueScore;

    private Date createTime;

    
    private Date modifyTime;

   
    private String createMan;

    
    private String modifyMan;

    
    private String remark;
    /**技能类型*/
    private Integer  skillType;
    
    private String selectReason;
    private String remarkReason;
    
    
    

    
    public Long getSkillItemId() {
		return skillItemId;
	}


	public void setSkillItemId(Long skillItemId) {
		this.skillItemId = skillItemId;
	}


	public String getSelectReason() {
		return selectReason;
	}


	public void setSelectReason(String selectReason) {
		this.selectReason = selectReason;
	}


	public String getRemarkReason() {
		return remarkReason;
	}


	public void setRemarkReason(String remarkReason) {
		this.remarkReason = remarkReason;
	}


	public Integer getSkillType() {
		return skillType;
	}


	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}


	public Long getOrderId() {
        return orderId;
    }

    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    
    public Date getAppointTime() {
		return appointTime;
	}


	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}


	public Long getOrderNo() {
        return orderNo;
    }

   
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    
    public Long getCustomerSkillId() {
        return customerSkillId;
    }

    
    public void setCustomerSkillId(Long customerSkillId) {
        this.customerSkillId = customerSkillId;
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

   
    public Long getTradeId() {
        return tradeId;
    }

    
    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    
    public Integer getPaymentType() {
        return paymentType;
    }

    
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    
    public Integer getOrderNum() {
        return orderNum;
    }

    
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    
    public String getServiceUnit() {
        return serviceUnit;
    }

    
    public void setServiceUnit(String serviceUnit) {
        this.serviceUnit = serviceUnit == null ? null : serviceUnit.trim();
    }

   
    public BigDecimal getServicePrice() {
        return servicePrice;
    }

   
    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    
    public BigDecimal getOrderAmounts() {
        return orderAmounts;
    }

    
    public void setOrderAmounts(BigDecimal orderAmounts) {
        this.orderAmounts = orderAmounts;
    }

   
    public BigDecimal getDiscountRate() {
        return discountRate;
    }


    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }


    public Date getCustCancelTime() {
        return custCancelTime;
    }


    public void setCustCancelTime(Date custCancelTime) {
        this.custCancelTime = custCancelTime;
    }

  
    public Date getSystemCancelTime() {
        return systemCancelTime;
    }


    public void setSystemCancelTime(Date systemCancelTime) {
        this.systemCancelTime = systemCancelTime;
    }

  
    public Date getReceiveOrderTime() {
        return receiveOrderTime;
    }

  
    public void setReceiveOrderTime(Date receiveOrderTime) {
        this.receiveOrderTime = receiveOrderTime;
    }


    public Date getStartServiceTime() {
        return startServiceTime;
    }


    public void setStartServiceTime(Date startServiceTime) {
        this.startServiceTime = startServiceTime;
    }


    public Date getAppayEndTime() {
        return appayEndTime;
    }

 
    public void setAppayEndTime(Date appayEndTime) {
        this.appayEndTime = appayEndTime;
    }


    public Date getStartTime() {
        return startTime;
    }

  
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

 
    public Date getEndTime() {
        return endTime;
    }

  
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public Date getOrderTime() {
        return orderTime;
    }


    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

  
    public Integer getOrderStatus() {
        return orderStatus;
    }


    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Date getPaymentTime() {
        return paymentTime;
    }

 
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

 
    public Integer getCustomerIsEvaluate() {
        return customerIsEvaluate;
    }


    public void setCustomerIsEvaluate(Integer customerIsEvaluate) {
        this.customerIsEvaluate = customerIsEvaluate;
    }


    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage == null ? null : customerMessage.trim();
    }


    public Integer getEvaluateStart() {
        return evaluateStart;
    }

 
    public void setEvaluateStart(Integer evaluateStart) {
        this.evaluateStart = evaluateStart;
    }


    public String getCustomerEvaluate() {
        return customerEvaluate;
    }


    public void setCustomerEvaluate(String customerEvaluate) {
        this.customerEvaluate = customerEvaluate == null ? null : customerEvaluate.trim();
    }


    public String getOrderDescribe() {
        return orderDescribe;
    }

 
    public void setOrderDescribe(String orderDescribe) {
        this.orderDescribe = orderDescribe == null ? null : orderDescribe.trim();
    }

 
    public Date getCreateTime() {
        return createTime;
    }

 
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

   
    public Date getModifyTime() {
        return modifyTime;
    }

 
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

  
    public String getCreateMan() {
        return createMan;
    }


    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

 
    public String getModifyMan() {
        return modifyMan;
    }

   
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

  
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }


	public Date getExpectEndTime() {
		return expectEndTime;
	}


	public void setExpectEndTime(Date expectEndTime) {
		this.expectEndTime = expectEndTime;
	}

    public BigDecimal getValueScore() {
        return valueScore;
    }

    public void setValueScore(BigDecimal valueScore) {
        this.valueScore = valueScore;
    }


	public Integer getCouponFlag() {
		return couponFlag;
	}


	public void setCouponFlag(Integer couponFlag) {
		this.couponFlag = couponFlag;
	}
    
    
    
}