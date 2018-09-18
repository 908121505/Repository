package com.honglu.quickcall.account.facade.enums;

/**
 * Created by len.song on 2017-12-25.
 */
public enum  AccountInOutEnum {
    in(1,"入账")
    ,out(2,"出账")
    ;


    private Integer type;
    private String  desc;

    AccountInOutEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static AccountInOutEnum fromValue(Integer value) {
        for (AccountInOutEnum type : AccountInOutEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(AccountInOutEnum type : AccountInOutEnum.values()) {
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
