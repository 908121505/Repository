package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 个人中心请求对象
 *
 * @author duanjun
 * @date 2018-10-20 11:18
 */
public class CustomerCenterRequest extends UserCenterRequest {

    private static final long serialVersionUID = 3883427321472184400L;
    /**
     * 客户ID
     */
    private Long customerId;

    @Override
    public String getBizCode() {
        return UserFunctionType.CUSTOMER_CENTER;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
