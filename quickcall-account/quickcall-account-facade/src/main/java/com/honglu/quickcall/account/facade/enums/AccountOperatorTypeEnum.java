package com.honglu.quickcall.account.facade.enums;

/**
 * Created by len.song on 2017-12-16.
 */
public enum AccountOperatorTypeEnum {
    NORMAL(1,"正常")
    ,ROLLBACK(2,"回滚")
            ;


    private Integer type;
    private String  desc;

    AccountOperatorTypeEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static AccountOperatorTypeEnum fromValue(Integer value) {
        for (AccountOperatorTypeEnum type : AccountOperatorTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(AccountOperatorTypeEnum type : AccountOperatorTypeEnum.values()) {
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
