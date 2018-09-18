package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-10.
 */
public enum SexEnum {
    MAN(1,"男性")
    ,WOMAN(2,"女性")
    ,DEFAULT(0,"默认未选择性别")
    ;


    private Integer type;
    private String  desc;

    SexEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static SexEnum fromValue(Integer value) {
        for (SexEnum type : SexEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(SexEnum type : SexEnum.values()) {
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
