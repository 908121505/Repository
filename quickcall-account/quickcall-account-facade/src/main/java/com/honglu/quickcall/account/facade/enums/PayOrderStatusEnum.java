package com.honglu.quickcall.account.facade.enums;

public enum PayOrderStatusEnum {
    Wait_Pay(1, "待支付"),
    Success_Pay(2, "支付成功"),
    Fail_Pay(3, "支付失败"),
    Close_Pay(4, "支付关闭"),
    Refund_Close_Pay(5, "退款完成关闭订单"),
    Wait_Abnormal_Order(6,"待处理异常订单");

    Integer type;
    String code;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    PayOrderStatusEnum(Integer type, String code) {
        this.type = type;
        this.code = code;
    }
}
