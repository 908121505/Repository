package com.honglu.quickcall.common.third.pay.alipay.model;

import java.io.Serializable;

public class AlipayTransferModel implements Serializable {
    String billNo;
    String buyerAccountNo;
    String buyerAccountName;
    String payAmount;
    String remark;
    Integer userId;
    Integer withdrawType;//提现类型 1:个人提现  2:家族提现

    public AlipayTransferModel() {
    }

    public AlipayTransferModel(String billNo, String buyerAccountNo, String buyerAccountName, String payAmount, String remark) {
        this.billNo = billNo;
        this.buyerAccountNo = buyerAccountNo;
        this.buyerAccountName = buyerAccountName;
        this.payAmount = payAmount;
        this.remark = remark;
    }

    public Integer getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(Integer withdrawType) {
        this.withdrawType = withdrawType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBuyerAccountNo() {
        return buyerAccountNo;
    }

    public void setBuyerAccountNo(String buyerAccountNo) {
        this.buyerAccountNo = buyerAccountNo;
    }

    public String getBuyerAccountName() {
        return buyerAccountName;
    }

    public void setBuyerAccountName(String buyerAccountName) {
        this.buyerAccountName = buyerAccountName;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
