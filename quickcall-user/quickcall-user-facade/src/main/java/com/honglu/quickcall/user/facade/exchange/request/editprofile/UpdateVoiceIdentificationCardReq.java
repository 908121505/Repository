package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 修改声鉴卡
 *
 * @author chenpeng
 * @date 2018/10/18 17:19
 */
public class UpdateVoiceIdentificationCardReq extends UserCenterRequest {

    private Long customerId;
    private String voiceIdentificationCard;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getVoiceIdentificationCard() {
        return voiceIdentificationCard;
    }

    public void setVoiceIdentificationCard(String voiceIdentificationCard) {
        this.voiceIdentificationCard = voiceIdentificationCard;
    }

    @Override
    public String toString() {
        return "UpdateVoiceIdentificationCardReq{" +
                "customerId=" + customerId +
                ", voiceIdentificationCard='" + voiceIdentificationCard + '\'' +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.updateVoiceIdentificationCard;
    }
}
