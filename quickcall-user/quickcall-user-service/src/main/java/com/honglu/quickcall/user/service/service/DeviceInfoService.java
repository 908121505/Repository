package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.DeviceInfoRequest;

/**
 * @Auther: xieshiqiang
 * @Date: 2018/11/19 18:22
 * @Description:
 */
public interface DeviceInfoService {
    /**
     *  添加或者修改设备信息
     * @param params
     * @return
     */
    CommonResponse saveOrUpdateDeviceInfo(DeviceInfoRequest params);
}
