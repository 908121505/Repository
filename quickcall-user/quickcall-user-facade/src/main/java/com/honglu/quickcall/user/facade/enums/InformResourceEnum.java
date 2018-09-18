package com.honglu.quickcall.user.facade.enums;

/**
 * 公告来源
 */
public enum InformResourceEnum {
    AppInform(1,"App消息"),
    AdminInform(1,"后台消息");


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

    InformResourceEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
