package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 修改星座
 *
 * @author chenpeng
 * @date 2018/10/18 17:19
 */
public class UpdateStarSignReq extends UserCenterRequest {

    private Long customerId;
    private String starSign;

    public String getStarSign() {
        return starSign;
    }

    public void setStarSign(String starSign) {
        this.starSign = starSign;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "UpdateStarSignReq{" +
                "customerId=" + customerId +
                ", starSign='" + starSign + '\'' +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.updateStarSign;
    }
}
