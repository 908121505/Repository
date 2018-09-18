package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-22.
 */
public enum  IsBlackTypeEnums {
    blacked(1,"我把朋友拉黑了")
    ,beBlacked(2,"朋友把我拉黑了")
    ,friend(3,"任然是朋友")
    ;


    private Integer type;
    private String  desc;

    IsBlackTypeEnums(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static IsBlackTypeEnums fromValue(Integer value) {
        for (IsBlackTypeEnums type : IsBlackTypeEnums.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(IsBlackTypeEnums type : IsBlackTypeEnums.values()) {
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
