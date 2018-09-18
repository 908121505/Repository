package com.honglu.quickcall.account.facade.enums;

public enum AbnormalOrderStatusEnum {
    Wait(1, "待处理"),
    Success(2, "已处理"),
    Fail(3, "处理失败");

    private int code;
    private String type;

    AbnormalOrderStatusEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
