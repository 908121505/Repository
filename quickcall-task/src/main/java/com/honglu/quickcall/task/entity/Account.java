package com.honglu.quickcall.task.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Account  implements Serializable{
    private Long accountId;

    private String name;

    private Byte type;

    private String accountDescribe;

    private Long customerId;

    private BigDecimal rechargeAmounts;

    private BigDecimal remainderAmounts;

    private BigDecimal usableAmounts;

    private BigDecimal frozenAmounts;

    private Date createTime;

    private Date modifyTime;

    private String createMan;

    private String modifyMan;

    private String remark;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getAccountDescribe() {
        return accountDescribe;
    }

    public void setAccountDescribe(String accountDescribe) {
        this.accountDescribe = accountDescribe == null ? null : accountDescribe.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getRechargeAmounts() {
        return rechargeAmounts;
    }

    public void setRechargeAmounts(BigDecimal rechargeAmounts) {
        this.rechargeAmounts = rechargeAmounts;
    }

    public BigDecimal getRemainderAmounts() {
        return remainderAmounts;
    }

    public void setRemainderAmounts(BigDecimal remainderAmounts) {
        this.remainderAmounts = remainderAmounts;
    }

    public BigDecimal getUsableAmounts() {
        return usableAmounts;
    }

    public void setUsableAmounts(BigDecimal usableAmounts) {
        this.usableAmounts = usableAmounts;
    }

    public BigDecimal getFrozenAmounts() {
        return frozenAmounts;
    }

    public void setFrozenAmounts(BigDecimal frozenAmounts) {
        this.frozenAmounts = frozenAmounts;
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
    public Account() {
    	
    }
    public Account(Long accountId,Long customerId) {
    	this.accountId=accountId;
    	this.customerId=customerId;
    }
    
}