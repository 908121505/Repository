package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-14.
 */
public enum  WomanAuthStatusEnum {
    NOAPPLY(1,"未申请")
    ,APPLYING(2,"申请中")
    ,PASS(3,"申请通过")
    ,REFUSE(4,"拒绝")
    ;


    private Integer type;
    private String  desc;

    WomanAuthStatusEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static WomanAuthStatusEnum fromValue(Integer value) {
        for (WomanAuthStatusEnum type : WomanAuthStatusEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(WomanAuthStatusEnum type : WomanAuthStatusEnum.values()) {
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
