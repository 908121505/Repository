package com.honglu.quickcall.account.facade.enums;

public enum WithdrawTypeEnum {
    Personal(1, "个人提现"),
    Family(2, "家族提现");

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

    WithdrawTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
