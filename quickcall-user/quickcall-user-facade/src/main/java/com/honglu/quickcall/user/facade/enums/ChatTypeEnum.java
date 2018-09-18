package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-10.
 */
public enum ChatTypeEnum {
    ALL(0,"所有类型")
    ,VIDEO(1,"视频类型")
    ,VOICE(2,"语音")
    ;


    private Integer type;
    private String  desc;

    ChatTypeEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static ChatTypeEnum fromValue(Integer value) {
        for (ChatTypeEnum type : ChatTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(ChatTypeEnum type : ChatTypeEnum.values()) {
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
