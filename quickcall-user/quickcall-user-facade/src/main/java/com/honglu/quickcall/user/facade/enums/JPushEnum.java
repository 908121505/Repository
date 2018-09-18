package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-30.
 */
public enum JPushEnum {
	//此处type先使用1-10，若需要更高同步确认admin中的BizConstants类里面不要重复
    attention(1,"关注推送")
    ,present(2,"送礼推送")
    ,friend(0,"成为朋友推送")
    ,onLine(4,"女神上线推送")
    ;


    private Integer type;
    private String  desc;

    JPushEnum(Integer code, String desc){
        this.type = code;
        this.desc = desc;
    }

    public static JPushEnum fromValue(Integer value) {
        for (JPushEnum type : JPushEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(JPushEnum type : JPushEnum.values()) {
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
