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
    private String loginId;
    private String type;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QueryDeviceWhitelistReq{" +
                "loginId='" + loginId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.queryDeviceWhitelist;
    }
}
