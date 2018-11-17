package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 删除黑名单
 *
 * @author chenpeng
 * @date 2018/10/18 17:19
 */
public class RemoveBlacklistReq extends UserCenterRequest {

    private Long customerId;
    private Long blackCustomerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBlackCustomerId() {
        return blackCustomerId;
    }

    public void setBlackCustomerId(Long blackCustomerId) {
        this.blackCustomerId = blackCustomerId;
    }

    @Override
    public String toString() {
        return "RemoveBlacklistReq{" +
                "customerId=" + customerId +
                ", blackCustomerId=" + blackCustomerId +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.removeBlacklist;
    }
}
