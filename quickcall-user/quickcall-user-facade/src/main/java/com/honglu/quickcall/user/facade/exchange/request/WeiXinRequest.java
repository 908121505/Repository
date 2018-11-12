package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 微信请求
 *
 * @author xiangping
 * @date 2018-11-08
 */
public class WeiXinRequest extends UserCenterRequest {
    private static final long serialVersionUID = -5124283956163159830L;

    private String code;

    private String phone;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.WEIXIN;
    }
}
