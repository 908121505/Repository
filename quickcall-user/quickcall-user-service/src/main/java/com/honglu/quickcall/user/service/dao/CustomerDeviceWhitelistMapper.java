package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.CustomerDeviceWhitelist;
import com.honglu.quickcall.user.facade.vo.CustomerDeviceWhitelistVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cp on 2018/11/2.
 */
public interface CustomerDeviceWhitelistMapper {

    List<CustomerDeviceWhitelistVO> selectListByCustomerId(@Param("customerId") Long customerId);

    int selectCountByCusIdAndDeviceId(@Param("customerId") Long customerId, @Param("deviceId") String deviceId);

    int insertSelective(CustomerDeviceWhitelist customerDeviceWhitelist);
}
