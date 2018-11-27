package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 修改头像
 *
 * @author chenpeng
 * @date 2018/10/18 17:19
 */
public class UpdateHeadPortraitReq extends UserCenterRequest {

    private Long customerId;
    private String headPortraitUrl;

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "UpdateHeadPortraitReq{" +
                "customerId=" + customerId +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.updateHeadPortrait;
    }
}
