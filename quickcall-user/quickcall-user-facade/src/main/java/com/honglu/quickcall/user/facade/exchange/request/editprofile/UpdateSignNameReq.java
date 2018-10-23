package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 修改个性签名
 *
 * @author chenpeng
 * @date 2018/10/18 17:19
 */
public class UpdateSignNameReq extends UserCenterRequest {

    private Long customerId;
    private String signName;

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "UpdateSignNameReq{" +
                "customerId=" + customerId +
                ", signName='" + signName + '\'' +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.updateSignName;
    }
}
