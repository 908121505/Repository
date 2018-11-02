package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 查询设备白名单列表
 *
 * @author chenpeng
 * @date 2018/10/22 11:51
 */
public class QueryDeviceWhitelistReq extends UserCenterRequest {
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "QueryDeviceWhitelistReq{" +
                "customerId=" + customerId +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.queryDeviceWhitelist;
    }
}
