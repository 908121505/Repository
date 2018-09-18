package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-31.
 */
public enum PersionRelationEnum {
    friend(1,"好友")
    ,attention(2,"关注")
    ,fans(3,"粉丝")
    ;


    private Integer type;
    private String  desc;

    PersionRelationEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static PersionRelationEnum fromValue(Integer value) {
        for (PersionRelationEnum type : PersionRelationEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(PersionRelationEnum type : PersionRelationEnum.values()) {
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
