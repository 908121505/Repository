package com.honglu.quickcall.account.facade.enums;

/**
 * Created by len.song on 2017-12-16.
 * 账户流水记录操作状态
 */
public enum AccountOperatorStatusEnum {
    OPERTING(1,"操作中")
    ,SUCCESS(2,"操作成功")
    ,FAILURE(3,"操作失败")
    ;


    private Integer type;
    private String  desc;

    AccountOperatorStatusEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static AccountOperatorStatusEnum fromValue(Integer value) {
        for (AccountOperatorStatusEnum type : AccountOperatorStatusEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(AccountOperatorStatusEnum type : AccountOperatorStatusEnum.values()) {
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
