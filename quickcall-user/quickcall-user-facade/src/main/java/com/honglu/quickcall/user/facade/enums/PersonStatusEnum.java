package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-14.
 */
public enum PersonStatusEnum {
    USING(1,"开启")
    ,FORBIDDEN(2,"禁用")
    ;


    private Integer type;
    private String  desc;

    PersonStatusEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static PersonStatusEnum fromValue(Integer value) {
        for (PersonStatusEnum type : PersonStatusEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(PersonStatusEnum type : PersonStatusEnum.values()) {
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
