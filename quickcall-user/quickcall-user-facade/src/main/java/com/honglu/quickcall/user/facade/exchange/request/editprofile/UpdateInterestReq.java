package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 修改个性签名
 *
 * @author chenpeng
 * @date 2018/10/18 17:19
 */
public class UpdateInterestReq extends UserCenterRequest {

    private Long customerId;
    private String interestId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getInterestId() {
        return interestId;
    }

    public void setInterestId(String interestId) {
        this.interestId = interestId;
    }

    @Override
    public String toString() {
        return "UpdateInterestReq{" +
                "customerId=" + customerId +
                ", interestId='" + interestId + '\'' +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.updateInterest;
    }
}
