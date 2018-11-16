package com.honglu.quickcall.databury.core.enums;


import com.honglu.quickcall.databury.core.utils.StringUtils;

/**
 * 事件
 *
 * @author xiangping
 * @date 2018-10-29 13:13
 */
public enum EventEnums {
    /**获取验证码**/
    EVENT_getCode("getCode","获取验证码"),

    /**注册成功**/
    EVENT_Sign_up_result("Sign_up_result","注册成功"),

    /**登陆成功**/
    EVENT_User_id_login_result("vc_user_id_login_result","登陆成功"),

    /**接单按钮**/
    EVENT_Order_button("Order_button","接单按钮"),

    /**接单按钮**/
    EVENT_My_followers("My_followers","接单按钮"),

    /**我的粉丝数**/
    EVENT_My_fans("My_fans","我的粉丝数"),

    /**提交订单**/
    EVENT_submitorder("submitorder","提交订单"),

    /**下单**/
    EVENT_Make_Order("Make_order","下单"),

    /**首次充值**/
    EVENT_First_Charge("first_recharge","首次充值"),

    /**设置密码页面**/
    EVENT_Set_Password_Duration("Set_password_duration","设置密码页面"),
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
