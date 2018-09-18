package com.honglu.quickcall.user.facade.enums;

/**
 * 公告状态
 */
public enum InformStateEnum {
    Open(1,"开启"),
    Close(1,"关闭");


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

    InformStateEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
