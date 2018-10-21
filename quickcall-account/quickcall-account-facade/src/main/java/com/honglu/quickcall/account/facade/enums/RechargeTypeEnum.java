package com.honglu.quickcall.account.facade.enums;

/**
 * @author xiangxianjin
 * @date 2018年10月21日 13点29分
 * descrption: 充值类型
 */
public enum RechargeTypeEnum {
    ALIPAY(1, "支付宝"),
    WECHAT(2, "微信"),
    APPLE_PAY(3, "苹果内购");

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

    RechargeTypeEnum(Integer code, String desc) {
        this.type = code;
        this.desc = desc;
    }
}
