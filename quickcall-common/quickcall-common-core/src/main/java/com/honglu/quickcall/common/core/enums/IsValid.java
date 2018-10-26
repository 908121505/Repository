package com.honglu.quickcall.common.core.enums;

/**
 * Created by len.song on 2017-12-11.
 * 通用的是否有效  0：false 即无效   1：true 即有效
 */
public enum IsValid {
    INVALID(0,"无效")
    ,VALID(1,"有效")
    ;


    private Integer type;
    private String  desc;

    IsValid(Integer code, String desc){
        this.type = code;
        this.desc = desc;
    }

    public static IsValid fromValue(Integer value) {
        for (IsValid type : IsValid.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(IsValid type : IsValid.values()) {
            if (type.getType().equals(value)) {
                return type.getDesc();
            }
        }
        return "";
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
