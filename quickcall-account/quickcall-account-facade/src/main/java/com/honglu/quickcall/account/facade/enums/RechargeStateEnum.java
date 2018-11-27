package com.honglu.quickcall.account.facade.enums;

/**
 * @author xiangxianjin
 * @date 2018年10月21日 13点29分
 * descrption: 充值状态
 */
public enum RechargeStateEnum {
    PAY_APPLY(1, "申请支付"),
    PAY_SUCCESS(2, "支付成功"),
    PAY_FAIL(3, "支付失败");

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

    RechargeStateEnum(Integer code, String desc) {
        this.type = code;
        this.desc = desc;
    }
}
