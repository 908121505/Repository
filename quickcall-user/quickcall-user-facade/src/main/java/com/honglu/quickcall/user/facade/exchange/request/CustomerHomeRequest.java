package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 客户主页请求对象
 *
 * @author duanjun
 * @date 2018-10-20 11:18
 */
public class CustomerHomeRequest extends UserCenterRequest {

    private static final long serialVersionUID = -6636255063712668875L;
    /**
     * 登录客户ID
     */
    private Long loginCustomerId;

    /**
     * 浏览客户ID
     */
    private Long viewCustomerId;

    @Override
    public String getBizCode() {
        return UserFunctionType.CUSTOMER_HOME;
    }

    public Long getLoginCustomerId() {
        return loginCustomerId;
    }

    public void setLoginCustomerId(Long loginCustomerId) {
        this.loginCustomerId = loginCustomerId;
    }

    public Long getViewCustomerId() {
        return viewCustomerId;
    }

    public void setViewCustomerId(Long viewCustomerId) {
        this.viewCustomerId = viewCustomerId;
    }
}
