package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-31.
 * 用户Im状态
 */
public enum ImStateEnum {
    on_free(1,"空闲")
    ,on_busy(2,"忙碌")
    ,no_disturb(3,"勿扰")
    ,off_line(4,"离线")
    ;


    private Integer type;
    private String  desc;

    ImStateEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static ImStateEnum fromValue(Integer value) {
        for (ImStateEnum type : ImStateEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(ImStateEnum type : ImStateEnum.values()) {
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
