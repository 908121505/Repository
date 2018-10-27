package com.honglu.quickcall.account.facade.enums;

/**
 * Created by len.song on 2017-12-16.
 */
public enum UserAccountStatusEnum {
    VALID(1,"可用状态")
    ,LOCK(2,"锁定状态")
    ;


    private Integer type;
    private String  desc;

    UserAccountStatusEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static UserAccountStatusEnum fromValue(Integer value) {
        for (UserAccountStatusEnum type : UserAccountStatusEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(UserAccountStatusEnum type : UserAccountStatusEnum.values()) {
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
