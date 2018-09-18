package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2017-12-10.
 */
public enum RankingEnum {
	CHARM(1,"魅力")
    ,ROOKIE(2,"新人")
    ,RICH(3,"富豪")
    ,DAY(11,"日榜")
    ,WEEK(12,"周榜")
    ,MONTH(13,"月榜")
    ,TOTAL(14,"总榜")
    ;


    private Integer type;
    private String  desc;

    RankingEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static RankingEnum fromValue(Integer value) {
        for (RankingEnum type : RankingEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(RankingEnum type : RankingEnum.values()) {
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
