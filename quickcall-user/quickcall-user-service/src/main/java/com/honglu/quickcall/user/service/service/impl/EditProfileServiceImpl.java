package com.honglu.quickcall.user.service.service.impl;

import cn.jiguang.commom.utils.StringUtils;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.core.util.Detect;
import com.honglu.quickcall.common.core.util.StringUtil;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.models.CodeSuccessReslut;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerAppearance;
import com.honglu.quickcall.user.facade.entity.CustomerInterest;
import com.honglu.quickcall.user.facade.entity.SensitivityWord;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.*;
import com.honglu.quickcall.user.facade.vo.BlacklistVo;
import com.honglu.quickcall.user.facade.vo.InterestVO;
import com.honglu.quickcall.user.service.dao.*;
import com.honglu.quickcall.user.service.service.CustomerRedisManagement;
import com.honglu.quickcall.user.service.service.EditProfileService;
import com.honglu.quickcall.user.service.type.AppearanceTypeEnum;
import com.honglu.quickcall.user.service.util.CommonUtil;
import com.honglu.quickcall.user.service.util.JsonParseUtil;
import com.honglu.quickcall.user.service.util.RedisKeyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 个人中心 -> 编辑资料
 *
 * @author chenpeng
 * @date 2018/10/18 13:44
 */
@Service
@Transactional
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
    @Autowired
    private CustomerAppearanceMapper customerAppearanceMapper;
    @Autowired
    private InterestMapper interestMapper;

    @Override
    public CommonResponse updateNickName(UpdateNickNameReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getCustomerId() == null){
            throw new BizException(UserBizReturnCode.paramError, "customerId不能为空");
        }
        if(StringUtil.isBlank(params.getNickName())){
            throw new BizException(UserBizReturnCode.paramError, "nickName不能为空");
        }

        Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
        String newNickname = params.getNickName();
        customer.setNickName(newNickname);

        // 字符格式校验
        Boolean formatCheckResult = CommonUtil.checkNickName(newNickname);
        if (!formatCheckResult) {
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
        int ifRepeat = customerMapper.selectCountByNickNameAndId(newNickname, params.getCustomerId().toString());
        if (ifRepeat > 0) {
            throw new RemoteException(UserBizReturnCode.paramError, "您输入的昵称已存在，请重新输入！");
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
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            throw new RemoteException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }
    }

    @Override
    public CommonResponse updateSignName(UpdateSignNameReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getCustomerId() == null){
            throw new BizException(UserBizReturnCode.paramError, "customerId不能为空");
        }
        if(StringUtil.isBlank(params.getSignName())){
            throw new BizException(UserBizReturnCode.paramError, "signName不能为空");
        }

        Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
        String newSign = params.getSignName();
        customer.setSignName(newSign);

        // 字符格式校验
        Boolean formatCheckResult = CommonUtil.checkSignName(newSign);
        if (!formatCheckResult) {
            throw new RemoteException(UserBizReturnCode.paramError, "个性签名不符合规则");
        }
        // 敏感词校验
        List<SensitivityWord> sensitivityList = sensitivityWordMapper.querySensitiveName();
        for (SensitivityWord sensitiveWord : sensitivityList) {
            if (StringUtils.isNotEmpty(newSign) && newSign.contains(sensitiveWord.getContent())) {
                throw new RemoteException(UserBizReturnCode.nickNameSensitive, "您输入的签名包含敏感字，请重新输入！");
            }
        }

        int result = customerMapper.updateByPrimaryKeySelective(customer);
        logger.info("修改签名 updateSignName,更新数量" + result);
        if (result > 0) {
            JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(), JsonParseUtil.castToJson(customer));

            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            throw new RemoteException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }
    }

    @Override
    public CommonResponse updateStarSign(UpdateStarSignReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getCustomerId() == null){
            throw new BizException(UserBizReturnCode.paramError, "customerId不能为空");
        }
        if(StringUtil.isBlank(params.getStarSign())){
            throw new BizException(UserBizReturnCode.paramError, "starSign不能为空");
        }

        Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
        String newStarSign = params.getStarSign();
        customer.setStarSign(newStarSign);

        int result = customerMapper.updateByPrimaryKeySelective(customer);
        logger.info("修改星座 updateStarSign,更新数量" + result);
        if (result > 0) {
            JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(), JsonParseUtil.castToJson(customer));

            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            throw new RemoteException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }
    }

    @Override
    public CommonResponse updateInterest(UpdateInterestReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getCustomerId() == null){
            throw new BizException(UserBizReturnCode.paramError, "customerId不能为空");
        }
        if(StringUtil.isBlank(params.getInterestId())){
            throw new BizException(UserBizReturnCode.paramError, "interestId不能为空");
        }

        CustomerInterest customerInterest = new CustomerInterest();
        customerInterest.setCustomerId(params.getCustomerId());

        Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
        String interests = params.getInterestId();
        String[] interest = interests.split(",");
        if (null != customer) {
            // 更新customer_interest表
            customerInterestMapper.deleteByCustomerId(params.getCustomerId());

            for (String str : interest) {
                customerInterest.setInterestId(Integer.parseInt(str));
                customerInterestMapper.insertSelective(customerInterest);
            }
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            throw new BizException(UserBizReturnCode.paramError, "参数错误，customer不存在，修改失败");
        }
    }

    @Override
    public CommonResponse updateHeadPortrait(UpdateHeadPortraitReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getCustomerId() == null){
            throw new BizException(UserBizReturnCode.paramError, "customerId不能为空");
        }
        if(StringUtil.isBlank(params.getHeadPortraitUrl())){
            throw new BizException(UserBizReturnCode.paramError, "headPortraitUrl不能为空");
        }

        //先判断表中是否存在这个用户头像记录 存在则更新，不存在则插入
        int result = 0;
        CustomerAppearance customerAppearance = customerAppearanceMapper.selectByCustomerIdAndType(params.getCustomerId(), AppearanceTypeEnum.HEAD_PORTRAIT.getCode());
        if (null != customerAppearance) {
            customerAppearance.setAuditAppearance(params.getHeadPortraitUrl());

            result = customerAppearanceMapper.updateAppearance(customerAppearance);
            logger.info("修改头像 updateHeadPortrait,更新数量" + result);

        } else {
            CustomerAppearance customerAppearanceNew = new CustomerAppearance();
            customerAppearanceNew.setId(UUIDUtils.getId());
            customerAppearanceNew.setAuditAppearance(params.getHeadPortraitUrl());
            customerAppearanceNew.setCustomerId(params.getCustomerId());
            customerAppearanceNew.setType(AppearanceTypeEnum.HEAD_PORTRAIT.getCode());
            result = customerAppearanceMapper.insertAppearance(customerAppearanceNew);

            logger.info("修改头像 updateHeadPortrait,插入数量" + result);
        }

        if (result > 0) {
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            throw new RemoteException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }
    }

    @Override
    public CommonResponse updateAppearance(UpdateAppearanceReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getCustomerId() == null){
            throw new BizException(UserBizReturnCode.paramError, "customerId不能为空");
        }
        if(StringUtil.isBlank(params.getAppearance())){
            throw new BizException(UserBizReturnCode.paramError, "appearance不能为空");
        }

        CustomerAppearance customerAppearance = new CustomerAppearance();
        customerAppearance.setId(UUIDUtils.getId());
        customerAppearance.setCustomerId(params.getCustomerId());
        customerAppearance.setAuditAppearance(params.getAppearance());
        customerAppearance.setType(AppearanceTypeEnum.APPEARANCE.getCode());

        int result = customerAppearanceMapper.insertAppearance(customerAppearance);
        if (result > 0) {
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            logger.error("修改形象照 异常");
            throw new BizException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }

    }

    @Override
    public CommonResponse removeAppearance(RemoveAppearanceReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getId() == null){
            throw new BizException(UserBizReturnCode.paramError, "id不能为空");
        }

        int result = customerAppearanceMapper.deleteEntity(params.getId());
        logger.info("删除形象照 removeAppearance,更新数量" + result);
        if (result > 0) {
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            logger.error("删除形象照 异常");
            throw new BizException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }
    }

    @Override
    public CommonResponse updateVoiceIdentificationCard(UpdateVoiceIdentificationCardReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getCustomerId() == null){
            throw new BizException(UserBizReturnCode.paramError, "customerId不能为空");
        }
        if(StringUtil.isBlank(params.getVoiceIdentificationCard())){
            throw new BizException(UserBizReturnCode.paramError, "voiceIdentificationCard不能为空");
        }

        //先判断表中是否存在这个用户声鉴卡记录，存在则更新，不存在则插入
        int result = 0;
        CustomerAppearance customerAppearance = customerAppearanceMapper.selectByCustomerIdAndType(params.getCustomerId(), AppearanceTypeEnum.VOICE_IDENTIFICATION_CARD.getCode());
        if (null != customerAppearance) {
            customerAppearance.setAuditAppearance(params.getVoiceIdentificationCard());
// TODO: 2018/10/23 照片更新的时候记得指定审核状态，还有其他几个地方 created by chenpeng
            result = customerAppearanceMapper.updateAppearance(customerAppearance);
            logger.info("修改声鉴卡 updateVoiceIdentificationCard,更新数量" + result);

        } else {
            CustomerAppearance customerAppearanceNew = new CustomerAppearance();
            customerAppearanceNew.setId(UUIDUtils.getId());
            customerAppearanceNew.setAuditAppearance(params.getVoiceIdentificationCard());
            customerAppearanceNew.setCustomerId(params.getCustomerId());
            customerAppearanceNew.setType(AppearanceTypeEnum.VOICE_IDENTIFICATION_CARD.getCode());
            result = customerAppearanceMapper.insertAppearance(customerAppearanceNew);

            logger.info("修改声鉴卡 updateVoiceIdentificationCard,插入数量" + result);
        }
        if (result > 0) {
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            logger.error("修改声鉴卡异常");
            throw new RemoteException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }
    }

    @Override
    public CommonResponse removeVoiceIdentificationCard(RemoveVoiceIdentificationCardReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getId() == null){
            throw new BizException(UserBizReturnCode.paramError, "id不能为空");
        }

        int result = customerAppearanceMapper.deleteEntity(params.getId());
        logger.info("删除声鉴卡 removeVoiceIdentificationCard,更新数量" + result);
        if (result > 0) {
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            logger.error("删除声鉴卡 异常");
            throw new BizException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }

    }

    @Override
    public CommonResponse queryInterestList(QueryInterestListReq params) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<InterestVO> interestList = interestMapper.selectInterestList();

            commonResponse.setData(interestList);
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
        } catch (Exception e) {
            logger.error("查询兴趣列表异常，异常信息：", e);
        }
        return commonResponse;
    }
}
