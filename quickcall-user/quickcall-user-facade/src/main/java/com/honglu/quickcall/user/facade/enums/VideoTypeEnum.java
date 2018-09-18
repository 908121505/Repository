package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2018-02-01.
 */
public enum VideoTypeEnum {
    cover(1,"封面视频")
    ,open(2,"公开视频")
    ,secret(3,"私密视频")
    ;


    private Integer type;
    private String  desc;

    VideoTypeEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static VideoTypeEnum fromValue(Integer value) {
        for (VideoTypeEnum type : VideoTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(VideoTypeEnum type : VideoTypeEnum.values()) {
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
