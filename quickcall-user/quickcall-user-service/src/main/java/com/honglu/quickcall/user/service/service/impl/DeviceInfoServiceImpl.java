package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.user.facade.entity.DeviceInfo;
import com.honglu.quickcall.user.facade.exchange.request.DeviceInfoRequest;
import com.honglu.quickcall.user.facade.vo.DelateVO;
import com.honglu.quickcall.user.service.dao.DeviceInfoMapper;
import com.honglu.quickcall.user.service.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: xieshiqiang
 * @Date: 2018/11/19 18:23
 * @Description:
 */
@Service
@Transactional
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    /**
     * 添加或修改设备信息
     * @param params
     * @return
     */
    @Override
    public CommonResponse saveOrUpdateDeviceInfo(DeviceInfoRequest params) {
        String deviceId = params.getDeviceId();
        String appVersion = params.getAppVersion();
        String channel = params.getChannel();
        String phoneType = params.getPhoneType();
        if(deviceId == null){
            throw new BizException(BizCode.ParamError, "设备id不能为空");
        }
        int count = deviceInfoMapper.queryDeviceInfoByDdeviceId(deviceId);
        //查询数据库中是否已存在
        if(count == 0){
            //不存在则添加
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setAppVersion(appVersion);
            deviceInfo.setChannel(channel);
            deviceInfo.setDeviceId(deviceId);
            deviceInfo.setPhoneType(phoneType);
            deviceInfoMapper.insertDeviceInfo(deviceInfo);
        }else{
            //存在则更新版本和渠道信息
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setAppVersion(appVersion);
            deviceInfo.setChannel(channel);
            deviceInfo.setPhoneType(phoneType);
            deviceInfo.setDeviceId(deviceId);
            deviceInfoMapper.updateDeviceInfo(deviceInfo);
        }
        return ResultUtils.resultSuccess();
    }
}
