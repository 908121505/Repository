package com.honglu.quickcall.user.facade.enums;

/**
 * 公告类型
 */
public enum InformTypeEnum {
    ActivityInform(1,"活动公告"),
    SystemInform(2,"系统公告");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    InformTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
