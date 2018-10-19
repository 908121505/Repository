package com.honglu.quickcall.user.service.service.impl;

import cn.jiguang.commom.utils.StringUtils;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.core.util.Detect;
import com.honglu.quickcall.common.third.rongyun.models.CodeSuccessReslut;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerInterest;
import com.honglu.quickcall.user.facade.entity.SensitivityWord;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.*;
import com.honglu.quickcall.user.service.dao.CustomerInterestMapper;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.InterestMapper;
import com.honglu.quickcall.user.service.dao.SensitivityWordMapper;
import com.honglu.quickcall.user.service.service.CustomerRedisManagement;
import com.honglu.quickcall.user.service.service.EditProfileService;
import com.honglu.quickcall.user.service.util.CommonUtil;
import com.honglu.quickcall.user.service.util.JsonParseUtil;
import com.honglu.quickcall.user.service.util.RedisKeyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 个人中心 -> 编辑资料
 *
 * @author chenpeng
 * @date 2018/10/18 13:44
 */
@Service
public class EditProfileServiceImpl implements EditProfileService {

    private static final Logger logger = LoggerFactory.getLogger(PersonInfoServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private SensitivityWordMapper sensitivityWordMapper;
    @Autowired
    private CustomerRedisManagement customerRedisManagement;
    @Autowired
    private CustomerInterestMapper customerInterestMapper;

    @Override
    public CommonResponse updateNickName(UpdateNickNameReq params) {
        CommonResponse commonResponse = new CommonResponse();
        Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
        String newNickname = params.getNickName();

        if (StringUtils.isNotEmpty(newNickname)) {
            String oldNickname = customer.getNickName();
            customer.setNickName(newNickname);
            if (newNickname.equals(oldNickname)) {

            } else {
                // 字符格式校验
                Boolean  formatCheckResult = CommonUtil.checkNickName(newNickname);
                if(!formatCheckResult){
                    throw new RemoteException(UserBizReturnCode.paramError, "用户名不符合规则");
                }
                // 敏感词校验
                List<SensitivityWord> sensitivityList = sensitivityWordMapper.querySensitiveName();
                if (Detect.notEmpty(sensitivityList)) {
                    for (SensitivityWord obj : sensitivityList) {
                        if (newNickname.contains(obj.getContent())) {
                            logger.info("昵称包含敏感词！");
                            throw new RemoteException(UserBizReturnCode.nickNameSensitive, "您输入的昵称包含敏感字，请重新输入！");
                        }
                    }
                }
                // 昵称重复校验
                int ifRepeat = customerMapper.selectCountByNickNameAndId(newNickname,params.getCustomerId().toString());
                if (ifRepeat > 0) {
                    throw new RemoteException(UserBizReturnCode.paramError, "您输入的昵称已存在，请重新输入！");
                }

            }
        }

        int result = customerMapper.updateByPrimaryKeySelective(customer);
        logger.info("修改昵称 updateNickName,更新数量：" + result);
        if (result > 0) {

            // 刷新融云用户信息
            CodeSuccessReslut reslut = RongYunUtil.refreshUser(String.valueOf(customer.getCustomerId()),
                    customer.getNickName(), customer.getHeadPortraitUrl());
            if (reslut.getCode() != 200) {
                logger.error("刷新融云用户信息失败，用户id为：" + String.valueOf(customer.getCustomerId()) + "失败原因为："
                        + reslut.getErrorMessage());
            } else {
                logger.info("刷新融云用户信息成功！");
            }

            JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(), JsonParseUtil.castToJson(customer));
            commonResponse.setData(customer);
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
        }
    }

    @Override
    public CommonResponse updateHeadPortrait(UpdateHeadPortraitReq params) {
        CommonResponse commonResponse = new CommonResponse();
        Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());

        if (StringUtils.isNotEmpty(params.getHeadPortraitUrl())) {
            customer.setHeadPortraitUrl(params.getHeadPortraitUrl());
        }
        //新上传头像状态默认为未审核
        customer.setHeadPortraitStatus("0");
        int result = customerMapper.updateByPrimaryKeySelective(customer);
        logger.info("修改头像 updateHeadPortrait,更新数量" + result);
        if (result > 0) {

            // 刷新融云用户信息
            CodeSuccessReslut reslut = RongYunUtil.refreshUser(String.valueOf(customer.getCustomerId()),
                    customer.getNickName(), customer.getHeadPortraitUrl());
            // 刷新失败
            if (reslut.getCode() != 200) {
                logger.error("刷新融云用户信息失败，用户id为：" + String.valueOf(customer.getCustomerId()) + "失败原因为："
                        + reslut.getErrorMessage());
            } else {
                logger.info("刷新融云用户信息成功！");
            }

            // 头像上传要审核，先不放在redis中
//            JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(), JsonParseUtil.castToJson(customer));

            commonResponse.setData(customer);
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
        }
    }

    @Override
    public CommonResponse updateSignName(UpdateSignNameReq params) {
        CommonResponse commonResponse = new CommonResponse();
        Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
        // 获取新签名
        String newSign = params.getSignName();
        // 获取旧签名
        String oldSign = customer.getSignName();
        customer.setSignName(newSign);
        if (newSign.equals(oldSign)) {

        } else {
            if (StringUtils.isNotEmpty(newSign)) {
                // 字符格式校验
                Boolean  formatCheckResult = CommonUtil.checkSignName(newSign);
                if(!formatCheckResult){
                    throw new RemoteException(UserBizReturnCode.paramError, "个性签名不符合规则");
                }
                // 敏感词校验
                List<SensitivityWord> sensitivityList = sensitivityWordMapper.querySensitiveName();
                for (SensitivityWord sensitiveWord : sensitivityList) {
                    if (StringUtils.isNotEmpty(newSign) && newSign.contains(sensitiveWord.getContent())) {
                        throw new RemoteException(UserBizReturnCode.nickNameSensitive, "您输入的签名包含敏感字，请重新输入！");
                    }
                }
            }
        }
        int result = customerMapper.updateByPrimaryKeySelective(customer);
        logger.info("修改签名 updateSignName,更新数量" + result);
        if (result > 0) {
            JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
                    JsonParseUtil.castToJson(customer));
            commonResponse.setData(customer);
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
        }
    }

    @Override
    public CommonResponse updateStarSign(UpdateStarSignReq params) {
        CommonResponse commonResponse = new CommonResponse();
        Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
        // 获取新星座
        String newStarSign = params.getStarSign();
        // 获取旧星座
//        String oldStarSign = customer.getSignName();
        customer.setSignName(newStarSign);

        int result = customerMapper.updateByPrimaryKeySelective(customer);
        logger.info("修改星座 updateStarSign,更新数量" + result);
        if (result > 0) {
            JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
                    JsonParseUtil.castToJson(customer));
            commonResponse.setData(customer);
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
        }
    }

    @Override
    public CommonResponse updateAppearance(UpdateAppearanceReq params) {
        return null;
    }

    @Override
    public CommonResponse updateInterest(UpdateInterestReq params) {
        CommonResponse commonResponse = new CommonResponse();
        CustomerInterest customerInterest = new CustomerInterest();
        customerInterest.setCustomerId(params.getCustomerId());

        Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
        String interests = params.getInterestId();
        String[] interest = interests.split(",");
        if (null != customer) {
            // 更新customer_interest表
            try {
                // 如果customer_interest中间表有该用户，先删除该用户在此表的数据
                customerInterestMapper.deleteByCustomerId(params.getCustomerId());

                for (String str : interest) {
                    customerInterest.setInterestId(Integer.parseInt(str));
                    customerInterestMapper.insertSelective(customerInterest);
                }
                commonResponse.setData(customerInterest);
                commonResponse.setCode(UserBizReturnCode.Success);
                commonResponse.setMessage(UserBizReturnCode.Success.desc());
                return commonResponse;
            } catch (Exception e) {
                logger.error("修改兴趣 异常",e);
                throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
            }
        } else {
            throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
        }
    }


}
