package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 编辑资料页面，查询用户信息
 *
 * @author chenpeng
 * @date 2018/10/22 11:51
 */
public class QueryUserEditInfoReq extends UserCenterRequest {
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "QueryUserEditInfoReq{" +
                "customerId=" + customerId +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.queryUserEditInfo;
    }
}
