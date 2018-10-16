package com.honglu.quickcall.user.service.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.MD5;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.AliyunSms.enums.SmsTemplateEnum;
import com.honglu.quickcall.common.third.AliyunSms.utils.AliyunSmsCodeUtil;
import com.honglu.quickcall.common.third.AliyunSms.utils.MandaoSmsCodeUtil;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.constants.UserBizConstants;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.exchange.request.GetSmsCodeRequest;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveCertificationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveDvVoiceRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetHeardUrlRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetPwdRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserIdCardInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserLoginRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserRegisterRequest;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.integration.AccountDubboIntegrationService;
import com.honglu.quickcall.user.service.service.CommonPersonService;

/**
 * Created by len.song on 2017-12-07.
 */
@Service
public class CommonPersonServiceImpl implements CommonPersonService {

	private final static Logger logger = LoggerFactory.getLogger(CommonPersonServiceImpl.class);

	// 默认图片
	private static String defaultImg = ResourceBundle.getBundle("thirdconfig").getString("defaultImg");
	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private AccountDubboIntegrationService accountDubboIntegrationService;

	private static String resendexpire = ResourceBundle.getBundle("thirdconfig").getString("resend.expire");
	private static String resendexpirehour = ResourceBundle.getBundle("thirdconfig").getString("resend.expire.hour");
	private static String onehourfreq = ResourceBundle.getBundle("thirdconfig").getString("one.hour.freq");
	private static String onedayfreq = ResourceBundle.getBundle("thirdconfig").getString("one.day.freq");
	private static String smscodeexpire = ResourceBundle.getBundle("thirdconfig").getString("smscode.expire");

	@Override
	public CommonResponse regUserExist(IsPhoneExistsRequest params) {
		CommonResponse response = new CommonResponse();
		Customer customer = null;
		Customer param = new Customer();
		if (StringUtils.isNotBlank(params.getTel())) {
			Pattern p = Pattern.compile("^((1[0-9]))\\d{9}$");
			Matcher m = p.matcher(params.getTel());
			if (!m.matches()) {
				throw new BizException(BizCode.ParamError, "手机号格式不正确");
			}
			param.setPhone(params.getTel());
		} else if (StringUtils.isNotBlank(params.getMicroblogOpenId())) {
			param.setMicroblogOpenId(params.getMicroblogOpenId());
		} else if (StringUtils.isNotBlank(params.getQqOpenId())) {
			param.setQqOpenId(params.getQqOpenId());

		} else if (StringUtils.isNotBlank(params.getWechatOpenId())) {
			param.setWechatOpenId(params.getWechatOpenId());
		}
		customer = customerMapper.login(param);
		if (customer != null) {
			response.setData(1);
		} else {
			response.setData(0);
		}
		response.setCode(BizCode.Success);
		response.setMessage(BizCode.Success.desc());
		return response;
	}

	@Override
	public CommonResponse login(UserLoginRequest params) {
		CommonResponse response = new CommonResponse();
		Customer customer = null;
		Customer param = new Customer();
		// 手机号登录 验证码登录
		if (StringUtils.isNotBlank(params.getTel())) {
			Pattern p = Pattern.compile("^((1[0-9]))\\d{9}$");
			Matcher m = p.matcher(params.getTel());
			if (!m.matches()) {
				throw new BizException(BizCode.ParamError, "手机号格式不正确");
			}
			param.setPhone(params.getTel());
		} // 手机号 密码登录
		/*
		 * if (StringUtils.isNotBlank(params.getPassWord())) {
		 * param.setCustPassword(MD5.md5(params.getPassWord())); }
		 */ // 微博登录
		else if (StringUtils.isNotBlank(params.getMicroblogOpenId())) {
			param.setMicroblogOpenId(params.getMicroblogOpenId());
		} // QQ登录
		else if (StringUtils.isNotBlank(params.getQqOpenId())) {
			param.setQqOpenId(params.getQqOpenId());

		} // 微信登录
		else if (StringUtils.isNotBlank(params.getWechatOpenId())) {
			param.setWechatOpenId(params.getWechatOpenId());
		}
		customer = customerMapper.login(param);
		if (customer == null) {
			logger.info("用户不存在");
			throw new BizException(BizCode.ParamError, "用戶不存在");
		} else {
			if (StringUtils.isNotBlank(params.getPassWord())) {
				if (!MD5.md5(params.getPassWord()).equals(customer.getCustPassword())) {
					logger.info("密码错误");
					throw new BizException(BizCode.ParamError, "密码错误");
				}
			}
		}

		// 修改登录时间 补 融云token
		Customer login = new Customer();
		if (StringUtils.isBlank(customer.getTokenCode())) {
			String rongyunToken = RongYunUtil.getToken(String.valueOf(customer.getCustomerId()), customer.getNickName(),
					defaultImg);
			if (rongyunToken == null || "".equals(rongyunToken)) {
				logger.error("用户获取融云token失败。用户ID为：" + customer.getCustomerId());
			}
			login.setTokenCode(rongyunToken);
		}
		login.setCustomerId(customer.getCustomerId());

		login.setModifyTime(new Date());
		customerMapper.updateByPrimaryKeySelective(login);

		return ResultUtils.resultSuccess(customer);
	}

	@Override
	public CommonResponse setpwd(SetPwdRequest params) {
		CommonResponse response = new CommonResponse();
		int row = customerMapper.customerSetPwd(params.getTel(), MD5.md5(params.getPassWord()));// MD5加密);
		if (row <= 0) {
			throw new BizException(BizCode.ParamError, "设置密码失败");
		}
		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse setHeardUrl(SetHeardUrlRequest params) {
		// TODO Auto-generated method stub
		String rongyunToken = null;
		if (StringUtils.isNotBlank(params.getNickName()) && params.getCustomerId() != null
				&& StringUtils.isNotBlank(params.getHeadPortraitUrl())) {
			rongyunToken = RongYunUtil.getToken(String.valueOf(params.getCustomerId()), params.getNickName(),
					params.getHeadPortraitUrl());
			if (rongyunToken == null || "".equals(rongyunToken)) {
				logger.error("用户获取融云token失败。用户ID为：" + params.getCustomerId());
			}
		}

		int row = customerMapper.customerSetHeardUrl(params.getTel(), params.getHeadPortraitUrl(), params.getNickName(),
				rongyunToken);
		if (row <= 0) {
			throw new BizException(BizCode.ParamError, "设置昵称头像失败");
		}
		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse register(UserRegisterRequest request) {
		// TODO Auto-generated method stub
		Customer customer = saveUser(request);
		return ResultUtils.resultSuccess(customer);
	}

	/**
	 * 保存用户，并生成融云token
	 *
	 * @param request
	 * @return
	 */
	private Customer saveUser(UserRegisterRequest request) {
		Customer param = new Customer();
		Customer customer = new Customer();
		// 手机号登录 验证码登录
		if (StringUtils.isNotBlank(request.getTel())) {
			Pattern p = Pattern.compile("^((1[0-9]))\\d{9}$");
			Matcher m = p.matcher(request.getTel());
			if (!m.matches()) {
				throw new BizException(BizCode.ParamError, "手机号格式不正确");
			}
			param.setPhone(request.getTel());
		} // 手机号 密码登录
			// 微博登录
		else if (StringUtils.isNotBlank(request.getMicroblogOpenId())) {
			param.setMicroblogOpenId(request.getMicroblogOpenId());
		} // QQ登录
		else if (StringUtils.isNotBlank(request.getQqOpenId())) {
			param.setQqOpenId(request.getQqOpenId());

		} // 微信登录
		else if (StringUtils.isNotBlank(request.getWechatOpenId())) {
			param.setWechatOpenId(request.getWechatOpenId());
		}
		customer = customerMapper.login(param);
		if (customer != null) {
			logger.info("用户已存在");
			throw new BizException(BizCode.ParamError, "用戶已存在");
		}
		customer = new Customer();
		customer.setCustomerId(UUIDUtils.getId());
		customer.setCreateTime(new Date());
		customer.setMicroblogOpenId(request.getMicroblogOpenId());
		customer.setQqOpenId(request.getQqOpenId());
		customer.setWechatOpenId(request.getWechatOpenId());
		customer.setPhone(request.getTel());
		customer.setNickName(request.getNickName());

		if (StringUtils.isNotBlank(request.getHeardUrl())) {
			defaultImg = request.getHeardUrl();
		}
		customer.setHeadPortraitUrl(defaultImg);
		if (StringUtils.isNotBlank(customer.getNickName())) {
			String rongyunToken = RongYunUtil.getToken(String.valueOf(customer.getCustomerId()), customer.getNickName(),
					defaultImg);
			if (rongyunToken == null || "".equals(rongyunToken)) {
				logger.error("用户获取融云token失败。用户ID为：" + customer.getCustomerId());
			}
			customer.setTokenCode(rongyunToken);
		}
		int row = customerMapper.insertSelective(customer);
		if (row <= 0) {
			throw new BizException(UserBizReturnCode.exceedError, "用户未注冊");
		}
		// 创建账户
		accountDubboIntegrationService.createAccount(customer.getCustomerId());
		customer = customerMapper.selectByPrimaryKey(customer.getCustomerId());
		return customer;
	}

	public int randomFour() {
		Random rand = new Random();
		int num = rand.nextInt(9999) + 1000;
		return num;
	}

	@Override
	public CommonResponse getSmsCode(GetSmsCodeRequest params) {
		// TODO Auto-generated method stub
		Customer customer = null;
		Customer param = new Customer();
		if (StringUtils.isNotBlank(params.getTel())) {
			param.setPhone(params.getTel());
			Pattern p = Pattern.compile("^((1[0-9]))\\d{9}$");
			Matcher m = p.matcher(params.getTel());
			if (!m.matches()) {
				throw new BizException(BizCode.ParamError, "手机号格式不正确");
			}
		}
		customer = customerMapper.login(param);
		if (customer == null) {
			if (!"2".equals(params.getCodeType())) {
				throw new BizException(UserBizReturnCode.exceedError, "用户未注冊");
			}
		} else {
			if ("2".equals(params.getCodeType())) {
				throw new BizException(UserBizReturnCode.exceedError, "用户已注冊");
			}
		}
		// 四位随机数
		int random = (int) ((Math.random() * 9 + 1) * 1000);
		String randomCode = random + "";

		return sendSms(params.getTel(), randomCode, params.getCodeType());
	}

	public static CommonResponse sendSms(String phoneNum, String code, String codeType) {
		CommonResponse response = new CommonResponse();
		// 查一分钟
		if (JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_M + phoneNum) == null) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_M + phoneNum, "1", Integer.parseInt(resendexpire));
		} else {
			throw new BizException(UserBizReturnCode.exceedError, "请勿重复发送验证码");
		}

		// 查一小时
		if (StringUtils.isBlank(JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum))) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum, "1", Integer.parseInt(resendexpirehour));
		} else {
			String count = JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum);
			if (Integer.parseInt(count) >= Integer.parseInt(onehourfreq)) {
				throw new BizException(UserBizReturnCode.exceedError, "验证码每小时只能获取五次");
			}
			String total = String.valueOf(Integer.parseInt(count) + 1);
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum, total,
					JedisUtil.ddlTimes(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum));
		}

		// 查一天
		if (StringUtils.isBlank(JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum))) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum, "1", DateUtils.getZeroTimestamp());
		} else {
			String count = JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum);
			if (Integer.parseInt(count) >= Integer.parseInt(onedayfreq)) {
				throw new BizException(UserBizReturnCode.exceedError, "验证码日发送次数已达到上限");
			}
			String total = String.valueOf(Integer.parseInt(count) + 1);
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum, total,
					JedisUtil.ddlTimes(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum));
		}

		SendSmsResponse codeResponse;
		try {
			codeResponse = AliyunSmsCodeUtil.sendSms(phoneNum, code);

			if (codeResponse != null && "OK".equals(codeResponse.getCode())) {
				JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code,
						Integer.parseInt(smscodeexpire));
				logger.info("手机号:" + phoneNum + " 手机验证码:" + code);
				logger.info("将验证码存入redis中的key值为:{},失效时间为:{}", (RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType),
						Integer.parseInt(smscodeexpire));
				return ResultUtils.resultSuccess();
			} else {
				// 阿里云短信异常,用 "漫道"短信通道 发送
				String mdResult = MandaoSmsCodeUtil.mdSmsSendSimple(phoneNum,
						MessageFormat.format(SmsTemplateEnum.login_auth_code_template.getContent(), code));
				if (mdResult.startsWith("-") || mdResult.equals("")) {
					throw new BizException(UserBizReturnCode.DBError, "验证码发送失败，请检查手机号是否正常");
				} else {
					logger.info("漫道发送验证码手机号:" + phoneNum + " 手机验证码:" + code);
					JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code,
							Integer.parseInt(smscodeexpire));
					return ResultUtils.resultSuccess();
				}
			}
		} catch (ClientException e) {
			// 阿里云短信异常,用 "漫道"短信通道 发送
			String mdResult = MandaoSmsCodeUtil.mdSmsSendSimple(phoneNum,
					MessageFormat.format(SmsTemplateEnum.login_auth_code_template.getContent(), code));
			if (mdResult.startsWith("-") || mdResult.equals("")) {
				throw new BizException(UserBizReturnCode.DBError, "验证码发送失败，请检查手机号是否正常");
			} else {
				logger.info("漫道发送验证码手机号:" + phoneNum + " 手机验证码:" + code);
				JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code,
						Integer.parseInt(smscodeexpire));
				return ResultUtils.resultSuccess();
			}
		}

	}

	@Override
	public CommonResponse queryUserIdCardCertificationInfo(UserIdCardInfoRequest request) {
		Customer customer = customerMapper.queryUserIdCardCertificationInfo(request.getCustomerId());
		if (customer == null) {
			return ResultUtils.resultDataNotExist("用户数据不存在");
		}

		if (customer.getIdentityStatus() == null) {
			customer.setIdentityStatus(0);// 身份认证状态为空的时候，默认复制为未认证
		}
		// 身份证号中间打***
		String credentialsNum = customer.getCredentialsNum();
		if (StringUtils.isNotBlank(credentialsNum) && credentialsNum.length() > 10) {
			StringBuilder idNo = new StringBuilder(credentialsNum.substring(0, 3));
			for (int i = 0; i < credentialsNum.length() - 7; i++) {
				idNo.append("*");
			}
			idNo.append(credentialsNum.substring(credentialsNum.length() - 4));
			customer.setCredentialsNum(idNo.toString());
		}

		return ResultUtils.resultSuccess(customer);
	}

	@Override
	public CommonResponse saveUserCertificationInfo(SaveCertificationRequest request) {
		Customer customer = customerMapper.selectByPrimaryKey(request.getCustomerId());
		if (customer == null) {
			return ResultUtils.resultDataNotExist("用户数据不存在");
		}

		Customer certifyCustomer = new Customer();
		// 身份认证上传照片 || 提交身份认证时 -- 校验状态
		if (Objects.equals(request.getCertifyType(), 0) || Objects.equals(request.getCredentialsType(), 1)) {
			if (Objects.equals(customer.getIdentityStatus(), 1)) {
				return ResultUtils.resultDuplicateOperation("身份认证正在审核中");
			}
			if (Objects.equals(customer.getIdentityStatus(), 2)) {
				return ResultUtils.resultDuplicateOperation("身份认证已通过");
			}
			certifyCustomer.setFrontPortraitUrl(request.getFrontPortraitUrl());
			certifyCustomer.setBackPortraitUrl(request.getBackPortraitUrl());
		}
		// 提交身份认证时 -- 判断身份证照片是否上传完整
		if (Objects.equals(request.getCertifyType(), 1)) {
			// 身份认证 -- 判断数据身份证身份已上传
			if (StringUtils.isBlank(customer.getFrontPortraitUrl())) {
				return ResultUtils.resultDataNotExist("请上传身份证正面照片");
			}
			if (StringUtils.isBlank(customer.getBackPortraitUrl())) {
				return ResultUtils.resultDataNotExist("请上传身份证反面照片");
			}
			certifyCustomer.setIdentityStatus(1);// 更新状态为：审核中
			certifyCustomer.setRealName(request.getRealName());
			certifyCustomer.setCredentialsType(request.getCredentialsType());
			certifyCustomer.setCredentialsNum(request.getCredentialsNum());
		}

		// 大V认证时 -- 判断大V身份认证状态
        if (Objects.equals(request.getCertifyType(), 2)) {
            if (!Objects.equals(customer.getIdentityStatus(), 2)) {
                if (Objects.equals(customer.getIdentityStatus(), 1)) {
                    return ResultUtils.resultDuplicateOperation("身份认证正在审核中，请等待审核通过后再进行大V认证");
                }
                return ResultUtils.resultDuplicateOperation("请先进行身份认证");
            }

            // 申请大V不校验是否在审核中
            if (Objects.equals(customer.getvStatus(), 1)) {
                return ResultUtils.resultDuplicateOperation("大V认证正在审核中");
            }
            if (Objects.equals(customer.getvStatus(), 2)) {
                return ResultUtils.resultDuplicateOperation("大V认证已通过");
            }
            certifyCustomer.setvStatus(1);// 更新状态为：审核中
            certifyCustomer.setVoiceUrl(request.getVoiceUrl());
        }

		// 如果声音时长为null，sql不会更新
		certifyCustomer.setVoiceTime(request.getVoiceTime());
		certifyCustomer.setCustomerId(request.getCustomerId());
		customerMapper.updateByPrimaryKeySelective(certifyCustomer);

		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse saveDvVoiceInfo(SaveDvVoiceRequest request) {
		Long   customerId =  request.getCustomerId();
		Customer customer = customerMapper.selectByPrimaryKey(customerId);
		if (customer == null) {
			return ResultUtils.resultDataNotExist("用户数据不存在");
		}
		Customer record = new Customer();
		record.setCustomerId(customerId);
		record.setvVoiceUrlTmp(request.getVoiceUrl());
		record.setvVoiceTimeTmp(request.getVoiceTime());
		record.setVoiceStatus(UserBizConstants.VOICE_STATUS_UN_APPROVE);
		customerMapper.updateByPrimaryKeySelective(record );
		return ResultUtils.resultSuccess();
	}
}
