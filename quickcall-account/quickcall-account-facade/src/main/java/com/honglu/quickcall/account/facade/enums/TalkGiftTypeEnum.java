package com.honglu.quickcall.account.facade.enums;

/**
 * Created by len.song on 2017-12-25.
 */
public enum TalkGiftTypeEnum {
    all(1,"所有女神")
    ,attention(2,"关注女神")
    ;


    private Integer type;
    private String  desc;

    TalkGiftTypeEnum(Integer code, String desc){
        this.type = code;
        this.desc = desc;
    }

    public static TalkGiftTypeEnum fromValue(Integer value) {
        for (TalkGiftTypeEnum type : TalkGiftTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(TalkGiftTypeEnum type : TalkGiftTypeEnum.values()) {
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
