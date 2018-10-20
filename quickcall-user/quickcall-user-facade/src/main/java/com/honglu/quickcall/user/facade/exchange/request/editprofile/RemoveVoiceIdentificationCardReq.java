package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 删除声鉴卡
 *
 * @author chenpeng
 * @date 2018/10/18 17:19
 */
public class RemoveVoiceIdentificationCardReq extends UserCenterRequest {

    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "RemoveVoiceIdentificationCardReq{" +
                "customerId=" + customerId +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.removeVoiceIdentificationCard;
    }
}
