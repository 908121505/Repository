package com.calf.module.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author xiangping
 * @date 2018-10-24 21:12
 */
public enum OrderStatusEnums {
    Waiting_List("1","待接单","2"),
    Waiting_Start("2","待开始","18,10,24"),
    Processing("3","进行中","26,28"),
    Complete("4","已完成","30,32,34,36,38,40,42"),
    Refused("5","已拒绝","8,29"),
    Cancel("6","已取消","4,6,12,14,16,20,22,23,29"),

    ;

    private final String value;
    private final String desc;
    private final String subset;

    OrderStatusEnums(String value, String desc, String subset) {
        this.value = value;
        this.desc = desc;
        this.subset = subset;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String getSubset() {
        return subset;
    }

    public static OrderStatusEnums getOrderStautsEnums(String value) {
        for (OrderStatusEnums orderStautsEnums : OrderStatusEnums.values()) {
            if (StringUtils.isNotBlank(value) && StringUtils.equals(value, orderStautsEnums.getValue())) {
                return orderStautsEnums;
            }
        }
        return null;
    }

    public static OrderStatusEnums getOrderStautsEnum(String subset) {
        for (OrderStatusEnums orderStatusEnums : OrderStatusEnums.values()) {
            if (StringUtils.isNotBlank(subset) && orderStatusEnums.getSubset().indexOf(subset)!=-1) {
                return orderStatusEnums;
            }
        }
        return null;
    }

}
