package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerDeviceWhitelist;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.QueryDeviceWhitelistReq;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.SaveDeviceWhitelistReq;
import com.honglu.quickcall.user.facade.vo.CustomerDeviceWhitelistVO;
import com.honglu.quickcall.user.service.dao.CustomerDeviceWhitelistMapper;
import com.honglu.quickcall.user.service.service.DeviceWhitelistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: 设备白名单
 *
 * @author chenpeng
 * @date 2018/11/2 15:26
 */
@Service
public class DeviceWhitelistServiceImpl implements DeviceWhitelistService{
    private static final Logger logger = LoggerFactory.getLogger(DeviceWhitelistServiceImpl.class);

    @Autowired
    private CustomerDeviceWhitelistMapper customerDeviceWhitelistMapper;

    @Override
    public CommonResponse queryDeviceWhitelist(QueryDeviceWhitelistReq params) {
        CommonResponse commonResponse = new CommonResponse();
        String loginId = params.getLoginId();
        String type = params.getType();
        if (loginId == null || "".equals(loginId)) {
            throw new BizException(UserBizReturnCode.paramError, "loginId不能为空");
        }
        if (type == null || "".equals(type)) {
            throw new BizException(UserBizReturnCode.paramError, "type不能为空");
        }
        if (!"0".equals(type) && !"1".equals(type) && !"2".equals(type) && !"3".equals(type)) {
            throw new BizException(UserBizReturnCode.paramError, "type参数错误");
        }

        //通过loginId查询获取用户CustomerId
        List<Map<String, Object>> customerList = null;

        //type “0”-手机号 “1”-微信opengid “2”-QQ openid “3”-微博openid
        if("0".equals(type)){
            customerList = customerDeviceWhitelistMapper.queryCustomerByPhone(loginId);
        }else if("1".equals(type)){
            customerList = customerDeviceWhitelistMapper.queryCustomerByWechatOpenId(loginId);
        }else if("2".equals(type)){
            customerList = customerDeviceWhitelistMapper.queryCustomerByQQOpenId(loginId);
        }else if("3".equals(type)){
            customerList = customerDeviceWhitelistMapper.queryCustomerByMicroblogOpenId(loginId);
        }

        if(customerList.size() == 0){
            throw new BizException(UserBizReturnCode.paramError, "用户不存在");
        }

        String customerId = customerList.get(0).get("customerId").toString();
        //通过CustomerId查询用户设备白名单信息
        List<CustomerDeviceWhitelistVO> customerDeviceWhitelist = customerDeviceWhitelistMapper.selectListByCustomerId(customerId);

        commonResponse.setData(customerDeviceWhitelist);
        commonResponse.setCode(UserBizReturnCode.Success);
        commonResponse.setMessage(UserBizReturnCode.Success.desc());
        return commonResponse;
    }

    @Override
    public CommonResponse saveDeviceWhitelist(SaveDeviceWhitelistReq params) {
        CommonResponse commonResponse = new CommonResponse();
        if (params.getCustomerId() == null) {
            throw new BizException(UserBizReturnCode.paramError, "customerId不能为空");
        }
        if (params.getDeviceId() == null) {
            throw new BizException(UserBizReturnCode.paramError, "deviceId不能为空");
        }
        //先查询数据库中是否已存在
        int count = customerDeviceWhitelistMapper.selectCountByCusIdAndDeviceId(params.getCustomerId(), params.getDeviceId());
        if(count > 0){
            throw new BizException(UserBizReturnCode.paramError, "设备白名单已存在");
        }

        CustomerDeviceWhitelist customerDeviceWhitelist = new CustomerDeviceWhitelist();
        Long id = UUIDUtils.getId();
        customerDeviceWhitelist.setId(id);
        customerDeviceWhitelist.setCustomerId(params.getCustomerId());
        customerDeviceWhitelist.setDeviceId(params.getDeviceId());
        customerDeviceWhitelist.setStatus(1);
        customerDeviceWhitelist.setCreateTime(new Date());

        int result = customerDeviceWhitelistMapper.insertSelective(customerDeviceWhitelist);
        logger.info("添加设备白名单 saveDeviceWhitelist,更新数量：" + result + customerDeviceWhitelist.toString());
        if (result > 0) {
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            logger.error("添加设备白名单 异常");
            throw new BizException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }
    }
}
