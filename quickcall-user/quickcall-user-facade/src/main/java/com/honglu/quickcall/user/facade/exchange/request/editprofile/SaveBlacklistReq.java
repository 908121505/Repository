package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 添加黑名单
 *
 * @author chenpeng
 * @date 2018/10/22 10:03
 */
public class SaveBlacklistReq extends UserCenterRequest {

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
    public String getBizCode() {
        return UserFunctionType.saveBlacklist;
    }
}
