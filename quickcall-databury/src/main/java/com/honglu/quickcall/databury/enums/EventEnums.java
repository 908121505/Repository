package com.honglu.quickcall.databury.enums;

import com.honglu.quickcall.databury.util.StringUtils;

/**
 * @author xiangping
 * @date 2018-10-24 21:12
 */
public enum EventEnums {
    /**web浏览页面**/
    EVENT_PageView("pageview","Web 浏览页面"),
    /**Web 元素点击**/
    EVENT_WebClick("WebClick","Web 元素点击"),


    ;

    private final String value;
    private final String desc;

    EventEnums(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static EventEnums getOrderStautsEnums(String value) {
        for (EventEnums orderStautsEnums : EventEnums.values()) {
            if (StringUtils.isNotBlank(value) && StringUtils.equals(value, orderStautsEnums.getValue())) {
                return orderStautsEnums;
            }
        }
        return null;
    }

}
