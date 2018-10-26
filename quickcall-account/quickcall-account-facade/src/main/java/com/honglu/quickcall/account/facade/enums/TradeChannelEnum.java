package com.honglu.quickcall.account.facade.enums;

public enum TradeChannelEnum {
    Wechat_MWEB(1, "微信H5支付"),
    Wechat_App(2, "微信App支付"),
    Alipay_Wap(3, "支付宝Wap支付"),
    Alipay_App(4, "支付宝App支付"),
    ApplePay(5,"苹果支付");


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


    TradeChannelEnum(Integer code, String desc) {
        this.type = code;
        this.desc = desc;
    }

}
