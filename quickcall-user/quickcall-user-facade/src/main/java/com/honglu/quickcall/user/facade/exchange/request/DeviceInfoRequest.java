package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * @Auther: xieshiqiang
 * @Date: 2018/11/19 17:56
 * @Description: 设备信息
 */
public class DeviceInfoRequest extends UserCenterRequest {

    private static final long serialVersionUID = 2316744011892316460L;

    private Long id;

    private String deviceId;

    private String phoneType;

    private String appVersion;

    private String channel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.ADD_UPDATE_DEVICE_INFO;
    }
}
