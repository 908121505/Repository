package com.honglu.quickcall.account.facade.enums;

public enum TradeTypeEnum {
    Pay(1, "支付"),
    Withdraw(2, "提现");

    private Integer type;
    private String desc;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    TradeTypeEnum(Integer code, String desc) {
        this.type = code;
        this.desc = desc;
    }
}
