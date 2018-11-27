package com.honglu.quickcall.user.facade.vo;

/**
 * Description: 查询设备白名单
 *
 * @author chenpeng
 * @date 2018/11/2 15:58
 */
public class CustomerDeviceWhitelistVO {

    private Long id;
    private Long customerId;
    private String deviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "CustomerDeviceWhitelistVO{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
