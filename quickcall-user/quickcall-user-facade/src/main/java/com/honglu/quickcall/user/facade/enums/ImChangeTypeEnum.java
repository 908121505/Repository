package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2018-01-11.
 */
public enum ImChangeTypeEnum {
    doOnline(1,"上线")
    ,offLine(2,"离线")
    ,inCall(3,"通话中")
    ,offCall(4,"通话结束")
    ;


    private Integer type;
    private String  desc;

    ImChangeTypeEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static ImChangeTypeEnum fromValue(Integer value) {
        for (ImChangeTypeEnum type : ImChangeTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(ImChangeTypeEnum type : ImChangeTypeEnum.values()) {
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
