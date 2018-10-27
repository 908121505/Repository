package com.honglu.quickcall.common.third.pay.apple;

public class ApplePayBuyerModel {
    private String status;
    private ApplePayRecieptModel receipt;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ApplePayRecieptModel getReceipt() {
        return receipt;
    }

    public void setReceipt(ApplePayRecieptModel receipt) {
        this.receipt = receipt;
    }
}
