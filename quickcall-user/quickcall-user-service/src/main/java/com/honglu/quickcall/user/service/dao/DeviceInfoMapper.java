package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.DeviceInfo;

/**
 * @Auther: xieshiqiang
 * @Date: 2018/11/19 18:24
 * @Description:
 */
public interface DeviceInfoMapper {

    int queryDeviceInfoByDdeviceId(Long deviceId);

    int insertDeviceInfo(DeviceInfo deviceInfo);

    int updateDeviceInfo(DeviceInfo deviceInfo);
}
