package com.honglu.quickcall.common.third.pay.apple;

public class ApplePayRecieptModel {
    private String transaction_id;//交易ID
    private String product_id;//产品ID
    private String purchase_date;//购买日期

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }
}
