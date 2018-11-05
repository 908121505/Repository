package com.calf.module.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author xiangping
 * @date 2018-10-24 21:12
 */
public enum SmallOrderStatusEnums {
    jdd("2","接待单"),
    xdqx("4","取消（用户下单后自主取消）"),
    djdqx("6","取消（待接单-大V超时未接）15分钟"),
    yjj("8","已拒绝（大V拒绝接单）"),
    dks("10","待开始（大V接单）"),
    jdqx("12","取消（大V接单，大V同一时间其它订单取消）"),
    zzqx("14","取消（大V接单后用户自主取消）"),
    wfqqx("16","取消（待开始5分钟大V未发起开始服务）"),
    fwdks("18","待开始(大V发起开始服务)"),
    fwzzqx("20","取消（大V发起开始服务用户自主取消）"),
    fwwj("22","取消（大V发起开始服务用户5分钟未接）"),
    djx("24","待进行（大V发起开始服务用户5分钟内同意，叫醒特享）"),
    jxz("26","进行中（大V发起开始服务用户5分钟内同意）"),
    fwjxz("28","进行中（大V发起完成服务，在服务时间内）"),
    qzqx("29","强制取消"),
    ywc("30","已完成（用户同意对方）"),
    fwywc("32","已完成-大V发起已完成服务,12小时客户不响应自动完成"),
    ywcyh("34","已完成（用户发起完成服务）"),
    fuwwc("36","已完成（大V在服务时间外完成）"),
    sfywc("38","已完成（双方12小时内未发起完成服务）"),
    pjywc("40","已完成（用户评价完成）"),
    qzwc("42","强制完成"),
    ;

    private final String value;
    private final String desc;

    SmallOrderStatusEnums(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SmallOrderStatusEnums getSmallOrderStatusEnums(String value) {
        for (SmallOrderStatusEnums orderStatutEnums : SmallOrderStatusEnums.values()) {
            if (StringUtils.isNotBlank(value) && StringUtils.equals(value, orderStatutEnums.getValue())) {
                return orderStatutEnums;
            }
        }
        return null;
    }
}
