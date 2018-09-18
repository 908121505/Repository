package com.honglu.quickcall.account.facade.exchange.response;

public class UnifiedOrderResponse {
    private String payOrderNo;
    private String payAmount;
    private String createTime;

    public UnifiedOrderResponse(String payOrderNo, String payAmount, String createTime) {
        this.payOrderNo = payOrderNo;
        this.payAmount = payAmount;
        this.createTime = createTime;
    }

    public UnifiedOrderResponse() {
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
