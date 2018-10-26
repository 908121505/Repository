package com.honglu.quickcall.common.api.code;

/**
 * Created by len.song on 2017-12-07.
 */
public class MyServiceCode extends ServiceCode {
    public static final MyServiceCode USER = new MyServiceCode("user", "001", "用户中心");
    public static final MyServiceCode ACCOUNT = new MyServiceCode("account", "002", "账户中心");
    public static final MyServiceCode PAIR = new MyServiceCode("pair", "003", "视频闪配一对一中心");
    public static final MyServiceCode IM = new MyServiceCode("im", "004", "聊天中心");
    public static final MyServiceCode ACTIVITY = new MyServiceCode("activity", "005", "活动中心");
    public static final MyServiceCode LIVE = new MyServiceCode("live", "006", "直播中心");

    public MyServiceCode() {
    }

    public MyServiceCode(String name, String code, String desc) {
        super(name, code, desc);
    }
}
