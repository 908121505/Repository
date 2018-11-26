package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 客户申请成为大V请求对象
 *
 * @author duanjun
 * @date 2018-11-07 11:18
 */
public class CustomerApplyBigvRequest extends UserCenterRequest {

    /**
     * 客户ID
     */
    private Long customerId;


    @Override
    public String getBizCode() {
        return UserFunctionType.CUSTOMER_APPLY_BIGV;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
