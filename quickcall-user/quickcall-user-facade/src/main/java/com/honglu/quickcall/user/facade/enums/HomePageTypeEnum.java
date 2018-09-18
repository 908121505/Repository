package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-10.
 */
public enum HomePageTypeEnum {
    HOT(1,"热门用户")
    ,NEW(2,"新人用户")
    ;


    private Integer type;
    private String  desc;

    HomePageTypeEnum(Integer code, String desc){
        this.type = code;
        this.desc = desc;
    }

    public static HomePageTypeEnum fromValue(Integer value) {
        for (HomePageTypeEnum type : HomePageTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(HomePageTypeEnum type : HomePageTypeEnum.values()) {
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
