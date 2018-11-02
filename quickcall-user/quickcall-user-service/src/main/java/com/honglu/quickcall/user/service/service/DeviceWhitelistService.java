package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.QueryDeviceWhitelistReq;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.SaveDeviceWhitelistReq;
/**
 * Created by cp on 2018/11/2.
 */
public interface DeviceWhitelistService {
    CommonResponse queryDeviceWhitelist(QueryDeviceWhitelistReq params);

    CommonResponse saveDeviceWhitelist(SaveDeviceWhitelistReq params);
}
