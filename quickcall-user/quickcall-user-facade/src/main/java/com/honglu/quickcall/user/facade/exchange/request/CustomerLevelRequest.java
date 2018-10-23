package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 我的等级页面 -- 请求对象
 *
 * @author duanjun
 * @date 2018-10-20 11:18
 */
public class CustomerLevelRequest extends UserCenterRequest {

    private static final long serialVersionUID = 7197264269962415046L;
    /**
     * 客户ID
     */
    private Long customerId;

    @Override
    public String getBizCode() {
        return UserFunctionType.CUSTOMER_LEVEL;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
