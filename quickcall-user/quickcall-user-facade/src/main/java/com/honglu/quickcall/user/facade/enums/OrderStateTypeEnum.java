package com.honglu.quickcall.user.facade.enums;

/**
 * Created by len.song on 2018-02-07.
 * 订单状态变更类型
 */
public enum OrderStateTypeEnum {
    created(1,"订单创建")
    ,pay_success(2,"订单支付完成")
    ,pay_fail(3,"订单支付失败")
    ,pay_success_business_fail(4,"订单支付成功/业务失败")
    ;


    private Integer type;
    private String  desc;

    OrderStateTypeEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static OrderStateTypeEnum fromValue(Integer value) {
        for (OrderStateTypeEnum type : OrderStateTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(OrderStateTypeEnum type : OrderStateTypeEnum.values()) {
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
