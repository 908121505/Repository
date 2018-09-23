package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderVO {
    
	 private Long orderId;

	    
	    private Long tradeId;

	    
	    private Long productId;

	    
	    private Long buyerId;

	   
	    private Long sellerId;

	   
	    private String orderDescribe;

	   
	    private Integer orderNum;

	   
	    private BigDecimal orderAmounts;

	    
	    private Integer paymentType;

	    
	    private Date confirmTime;

	    
	    private Date startTime;

	    
	    private Date endTime;

	   
	    private Date orderTime;

	   
	    private Date finishTime;

	    
	    private Integer orderStatus;

	   
	    private String refundReason;

	    
	    private Date paymentTime;

	    
	    private Date consignTime;

	    
	    private Integer buyerIsEvaluate;

	   
	    private String buyerMessage;

	    
	    private String buyerEvaluate;

	    
	    private Date createTime;

	    
	    private Date modifyTime;

	    
	    private String createMan;

	    
	    private String modifyMan;

	    
	    private String remark;

	    
	    public Long getOrderId() {
	        return orderId;
	    }

	    
	    public void setOrderId(Long orderId) {
	        this.orderId = orderId;
	    }

	    
	    public Long getTradeId() {
	        return tradeId;
	    }

	    
	    public void setTradeId(Long tradeId) {
	        this.tradeId = tradeId;
	    }

	   
	    public Long getProductId() {
	        return productId;
	    }

	    
	    public void setProductId(Long productId) {
	        this.productId = productId;
	    }

	    
	    public Long getBuyerId() {
	        return buyerId;
	    }

	    
	    public void setBuyerId(Long buyerId) {
	        this.buyerId = buyerId;
	    }

	    
	    public Long getSellerId() {
	        return sellerId;
	    }

	    public void setSellerId(Long sellerId) {
	        this.sellerId = sellerId;
	    }

	   
	    public String getOrderDescribe() {
	        return orderDescribe;
	    }

	    
	    public void setOrderDescribe(String orderDescribe) {
	        this.orderDescribe = orderDescribe == null ? null : orderDescribe.trim();
	    }

	    
	    public Integer getOrderNum() {
	        return orderNum;
	    }

	   
	    public void setOrderNum(Integer orderNum) {
	        this.orderNum = orderNum;
	    }

	    
	    public BigDecimal getOrderAmounts() {
	        return orderAmounts;
	    }

	    
	    public void setOrderAmounts(BigDecimal orderAmounts) {
	        this.orderAmounts = orderAmounts;
	    }

	    
	    public Integer getPaymentType() {
	        return paymentType;
	    }

	    
	    public void setPaymentType(Integer paymentType) {
	        this.paymentType = paymentType;
	    }

	    
	    public Date getConfirmTime() {
	        return confirmTime;
	    }

	   
	    public void setConfirmTime(Date confirmTime) {
	        this.confirmTime = confirmTime;
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

	    public Date getFinishTime() {
	        return finishTime;
	    }

	    
	    public void setFinishTime(Date finishTime) {
	        this.finishTime = finishTime;
	    }

	   
	    public Integer getOrderStatus() {
	        return orderStatus;
	    }

	   
	    public void setOrderStatus(Integer orderStatus) {
	        this.orderStatus = orderStatus;
	    }

	   
	    public String getRefundReason() {
	        return refundReason;
	    }

	   
	    public void setRefundReason(String refundReason) {
	        this.refundReason = refundReason == null ? null : refundReason.trim();
	    }

	   
	    public Date getPaymentTime() {
	        return paymentTime;
	    }

	   
	    public void setPaymentTime(Date paymentTime) {
	        this.paymentTime = paymentTime;
	    }

	    
	    public Date getConsignTime() {
	        return consignTime;
	    }

	    
	    public void setConsignTime(Date consignTime) {
	        this.consignTime = consignTime;
	    }

	    
	    public Integer getBuyerIsEvaluate() {
	        return buyerIsEvaluate;
	    }

	   
	    public void setBuyerIsEvaluate(Integer buyerIsEvaluate) {
	        this.buyerIsEvaluate = buyerIsEvaluate;
	    }

	    
	    public String getBuyerMessage() {
	        return buyerMessage;
	    }

	    
	    public void setBuyerMessage(String buyerMessage) {
	        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
	    }

	   
	    public String getBuyerEvaluate() {
	        return buyerEvaluate;
	    }

	   
	    public void setBuyerEvaluate(String buyerEvaluate) {
	        this.buyerEvaluate = buyerEvaluate == null ? null : buyerEvaluate.trim();
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
}