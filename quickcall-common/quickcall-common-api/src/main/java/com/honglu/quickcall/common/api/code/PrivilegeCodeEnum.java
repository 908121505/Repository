package com.honglu.quickcall.common.api.code;

/**
 * 特权code枚举
 */
public enum PrivilegeCodeEnum {

    // APP登录验证码检验，判断是否白名单且有该特权
    NO_NEED_VALIDATE_CODE("NO_NEED_VALIDATE_CODE", "APP登录验证码检验，判断是否白名单且有该特权"),

    // 点击VIP视频、女神主页私密视频接口判断是否白名单且有该特权
    VIP_PRIVATE_VIDEO("VIP_PRIVATE_VIDEO", "点击VIP视频、女神主页私密视频接口判断是否白名单且有该特权"),

    // 首页是否禁止显示vip视频框
    IS_FORBID_VIP_VIDEO("IS_FORBID_VIP_VIDEO", "首页是否禁止显示vip视频框"),

    SIGN_DEVICE_NO_LIMIT("SIGN_DEVICE_NO_LIMIT", "设备号注册时不限次数");

    PrivilegeCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     *  特权唯一code
     */
    String code;
    /**
     *  特权描述
     */
    String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
