package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerDeviceWhitelist;
import com.honglu.quickcall.user.facade.vo.CustomerDeviceWhitelistVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by cp on 2018/11/2.
 */
public interface CustomerDeviceWhitelistMapper {

    List<CustomerDeviceWhitelistVO> selectListByCustomerId(@Param("customerId") String customerId);

    int selectCountByCusIdAndDeviceId(@Param("customerId") Long customerId, @Param("deviceId") String deviceId);

    int insertSelective(CustomerDeviceWhitelist customerDeviceWhitelist);

    List<Map<String, Object>> queryCustomerByPhone(@Param("loginId") String loginId);

    List<Map<String, Object>> queryCustomerByWechatOpenId(@Param("loginId") String loginId);

    List<Map<String, Object>> queryCustomerByQQOpenId(@Param("loginId") String loginId);

    List<Map<String, Object>> queryCustomerByMicroblogOpenId(@Param("loginId") String loginId);
}
