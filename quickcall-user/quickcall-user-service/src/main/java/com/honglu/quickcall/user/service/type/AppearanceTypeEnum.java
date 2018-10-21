package com.honglu.quickcall.user.service.type;

/**
 * 对应customer_appearance表中type字段
 * Created by cp on 2018/10/21.
 */
public enum AppearanceTypeEnum {

    APPEARANCE(0,"形象照"),
    HEAD_PORTRAIT(1,"头像"),
    VOICE_IDENTIFICATION_CARD(2,"声鉴卡");

    private final Integer code;
    private final String message;

    AppearanceTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
