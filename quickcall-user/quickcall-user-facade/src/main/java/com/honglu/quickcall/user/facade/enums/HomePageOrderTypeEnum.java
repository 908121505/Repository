package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-10.
 * 首页用户列表类型
 */
public enum HomePageOrderTypeEnum {
    ONLINE(1,"在线列表")
    ,SUPER(2,"明星列表")
    ;


    private Integer type;
    private String  desc;

    HomePageOrderTypeEnum(Integer code, String desc){
        this.type = code;
        this.desc = desc;
    }

    public static HomePageOrderTypeEnum fromValue(Integer value) {
        for (HomePageOrderTypeEnum type : HomePageOrderTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(HomePageOrderTypeEnum type : HomePageOrderTypeEnum.values()) {
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
