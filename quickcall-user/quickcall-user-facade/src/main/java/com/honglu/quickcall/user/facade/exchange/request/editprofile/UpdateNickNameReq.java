package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 修改昵称
 *
 * @author chenpeng
 * @date 2018/10/18 11:31
 */
public class UpdateNickNameReq extends UserCenterRequest {

    private Long customerId;
    private String nickName;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "UpdateNickNameReq{" +
                "customerId=" + customerId +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.updateNickname;
    }
}
