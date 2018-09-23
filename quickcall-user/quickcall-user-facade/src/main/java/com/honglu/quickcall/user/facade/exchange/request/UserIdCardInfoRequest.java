package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * 用户身份认证信息查询请求对象
 *
 * @author duanjun
 * @date 2018-09-23 12:10
 */
public class UserIdCardInfoRequest extends UserCenterRequest {

    /**
     * 登录用户id
     */
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.USER_ID_CARD_CERTIFY_INFO;
    }
}
