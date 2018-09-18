package com.honglu.quickcall.account.facade.enums;

public enum MonitorNoticeTypeEnum {
    RechargeError(1, "充值异常"),
    NotAccountBalance(2, "账户余额不足");

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    MonitorNoticeTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
