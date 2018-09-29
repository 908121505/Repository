package com.honglu.quickcall.account.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Recharge {
    private Integer id;

    private Long customerId;

    private Date createDate;

    private Date finishDate;

    private Integer tokenPackage;

    private BigDecimal amount;

    private Integer type;

    private String alipayordersn;

    private String ordersn;

    private Integer state;

    private Integer rechargeType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getTokenPackage() {
        return tokenPackage;
    }

    public void setTokenPackage(Integer tokenPackage) {
        this.tokenPackage = tokenPackage;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAlipayordersn() {
        return alipayordersn;
    }

    public void setAlipayordersn(String alipayordersn) {
        this.alipayordersn = alipayordersn == null ? null : alipayordersn.trim();
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn == null ? null : ordersn.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(Integer rechargeType) {
        this.rechargeType = rechargeType;
    }
}