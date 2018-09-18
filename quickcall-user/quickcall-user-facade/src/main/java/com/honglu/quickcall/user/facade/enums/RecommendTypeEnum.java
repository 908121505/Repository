package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-10.
 * 首页用户推荐类型
 */
public enum RecommendTypeEnum {
    DAYS(2,"每日推荐")
    ,NEW_PERSON(1,"新用户推荐")
    ;


    private Integer type;
    private String  desc;

    RecommendTypeEnum(Integer code, String desc){
        this.type = code;
        this.desc = desc;
    }

    public static RecommendTypeEnum fromValue(Integer value) {
        for (RecommendTypeEnum type : RecommendTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(RecommendTypeEnum type : RecommendTypeEnum.values()) {
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
