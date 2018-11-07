package com.honglu.quickcall.user.service.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.CommonUtil;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.Detect;
import com.honglu.quickcall.common.core.util.MD5;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.AliyunSms.utils.SendSmsUtil;
import com.honglu.quickcall.common.third.rongyun.models.CodeSuccessReslut;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.producer.facade.business.DataDuriedPointBusiness;
import com.honglu.quickcall.producer.facade.req.databury.DataBuriedPointGetCodeReq;
import com.honglu.quickcall.producer.facade.req.databury.DataBuriedPointLoginReq;
import com.honglu.quickcall.producer.facade.req.databury.DataBuriedPointRegistReq;
import com.honglu.quickcall.user.facade.constants.UserBizConstants;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.SensitivityWord;
import com.honglu.quickcall.user.facade.enums.CustomerCusStateEnum;
import com.honglu.quickcall.user.facade.exchange.request.AddSystemUserRequest;
import com.honglu.quickcall.user.facade.exchange.request.BindVXorQQRequest;
import com.honglu.quickcall.user.facade.exchange.request.GetSmsCodeRequest;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.LoginOutRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveCertificationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveDvVoiceRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetHeardUrlRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetPwdRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserIdCardInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserLoginRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserRegisterRequest;
import com.honglu.quickcall.user.service.dao.BigvPhoneMapper;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.SensitivityWordMapper;
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
	private static String defaultWoManImg = ResourceBundle.getBundle("thirdconfig").getString("defaultWoManImg");
	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private AccountDubboIntegrationService accountDubboIntegrationService;
	@Autowired
	private SensitivityWordMapper sensitivityWordMapper;

	@Autowired

	private DataDuriedPointBusiness dataDuriedPointBusiness;

	private BigvPhoneMapper bigvPhoneMapper;

	private static String resendexpire = ResourceBundle.getBundle("thirdconfig").getString("resend.expire");
	private static String resendexpirehour = ResourceBundle.getBundle("thirdconfig").getString("resend.expire.hour");
	private static String onehourfreq = ResourceBundle.getBundle("thirdconfig").getString("one.hour.freq");
	private static String onedayfreq = ResourceBundle.getBundle("thirdconfig").getString("one.day.freq");
	private static String smscodeexpire = ResourceBundle.getBundle("thirdconfig").getString("smscode.expire");
	/**
	 * 中文、英文、数字、下划线校验 4-24位
	 */
	private final static Pattern CH_EN_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-z\\d_]{4,24}$");

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
		} else if (StringUtils.isNotBlank(params.getNickName())) {
			param.setNickName(params.getNickName());
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
		/**
		 * 神策埋点
		 */
		DataBuriedPointLoginReq req = new DataBuriedPointLoginReq();

		CommonResponse response = new CommonResponse();
		Customer customer = null;
		Customer param = new Customer();
		// 手机号登录 验证码登录
		if (StringUtils.isNotBlank(params.getTel())) {
			Pattern p = Pattern.compile("^((1[0-9]))\\d{9}$");
			Matcher m = p.matcher(params.getTel());
			if (!m.matches()) {
				// throw new BizException(BizCode.ParamError, "手机号格式不正确");
				response.setCode(BizCode.CustomerError);
				response.setMessage("手机号格式不正确");
				return response;
			}
			param.setPhone(params.getTel());
			req.setLoginmethod("手机号");
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
			req.setLoginmethod("QQ");

		} // 微信登录
		else if (StringUtils.isNotBlank(params.getWechatOpenId())) {
			param.setWechatOpenId(params.getWechatOpenId());
			req.setLoginmethod("微信");
		}
		customer = customerMapper.login(param);
		if (customer == null) {
			logger.info("用户不存在");
			response.setCode(BizCode.CustomerError);
			response.setMessage("用户不存在");
			return response;
		} else {
			if (StringUtils.isNotBlank(params.getPassWord())) {
				if (!MD5.md5(params.getPassWord()).equals(customer.getCustPassword())) {
					logger.info("密码错误");
					response.setCode(BizCode.CustomerError);
					response.setMessage("密码错误");
					return response;
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
		boolean isBlock = false;
		// 用户是否被封禁
		if (customer.getCustStatus() != null) {
			if (customer.getCustStatus() == -1 || customer.getCustStatus() == 8) {
				isBlock = true;
				if (customer.getCustStatus() == 8) {
					// 到了封禁时间 解封
					if (customer.getBlockEndTime() != null && customer.getBlockEndTime().before(new Date())) {
						login.setCustStatus(1);
						isBlock = false;
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
					Date date = customer.getBlockEndTime();
					response.setCode(BizCode.CustomerClosure);
					response.setMessage(df.format(date));
					return response;

				}
			}
		}
		// 账户被封
		if (isBlock) {
			response.setCode(BizCode.CustomerClosure);
			response.setMessage("永久封禁");
			return response;
		}

		// 更新登录信息
		login.setCustomerId(customer.getCustomerId());

		login.setModifyTime(new Date());
		login.setGtClientId(params.getGtClientId());
		login.setCustState(CustomerCusStateEnum.ON_LINE.getType());
		customerMapper.updateByPrimaryKeySelective(login);
		customer = customerMapper.selectByPrimaryKey(customer.getCustomerId());
		JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + customer.getCustomerId(),
				customer == null ? "" : JSON.toJSONString(customer));

		req.setPhoneNumber(customer.getPhone());
		req.setUser_id(customer.getCustomerId() + "");
		return ResultUtils.resultSuccess(customer);
	}

	@Override
	public CommonResponse setpwd(SetPwdRequest params) {
		CommonResponse response = new CommonResponse();
		int row = customerMapper.customerSetPwd(params.getTel(), MD5.md5(params.getPassWord()));// MD5加密);
		if (row <= 0) {
			response.setCode(BizCode.CustomerError);
			response.setMessage("设置密码失败");
			return response;
		}
		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse setHeardUrl(SetHeardUrlRequest params) {
		CommonResponse response = new CommonResponse();
		if (params.getCustomerId() == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		String rongyunToken = null;
		String img = params.getHeadPortraitUrl();
		if (StringUtils.isNotBlank(params.getNickName()) && params.getCustomerId() != null) {

			if (StringUtils.isBlank(img)) { // 手机号注册没有传头像，使用默认头像
				if (params.getSex() == 0) {// 性別是女
					img = defaultWoManImg;
				} else {// 性別是男
					img = defaultImg;
				}
			}
			// 字符格式校验
			Boolean formatCheckResult = CommonUtil.checkNickName(params.getNickName());
			if (!formatCheckResult) {
				response.setCode(BizCode.CustomerError);
				response.setMessage("用户名不符合规则");
				return response;

			}
			// 敏感词校验
			List<SensitivityWord> sensitivityList = sensitivityWordMapper.querySensitiveName();
			if (Detect.notEmpty(sensitivityList)) {
				for (SensitivityWord obj : sensitivityList) {
					if (params.getNickName().contains(obj.getContent())) {
						logger.info("昵称包含敏感词！");
						response.setCode(BizCode.CustomerError);
						response.setMessage("您输入的昵称包含敏感字，请重新输入!");
						return response;
					}
				}
			}
			// 昵称重复校验
			int ifRepeat = customerMapper.selectCountByNickNameAndId(params.getNickName(),
					params.getCustomerId().toString());
			if (ifRepeat > 0) {
				response.setCode(BizCode.CustomerError);
				response.setMessage("您输入的昵称已存在，请重新输入!");
				return response;
			}

			/*
			 * rongyunToken = RongYunUtil.getToken(String.valueOf(params.getCustomerId()),
			 * params.getNickName(), params.getHeadPortraitUrl()); if (rongyunToken == null
			 * || "".equals(rongyunToken)) { logger.error("用户获取融云token失败。用户ID为：" +
			 * params.getCustomerId()); }
			 */
			// 刷新融云用户信息
			CodeSuccessReslut reslut = RongYunUtil.refreshUser(String.valueOf(params.getCustomerId()),
					params.getNickName(), img);
			// 刷新失败
			// if (reslut.getCode() != 200) {
			// logger.error("刷新融云用户信息失败，用户id为：" + String.valueOf(params.getCustomerId()));
			// } else {
			// logger.info("刷新融云用户信息成功！");
			// }

		}

		int row = customerMapper.customerSetHeardUrl(params.getTel(), img, params.getNickName(), params.getSex());
		if (row <= 0) {
			response.setCode(BizCode.CustomerError);
			response.setMessage("设置失败");
			return response;
		}
		return ResultUtils.resultSuccess();
	}

	/**
	 * 昵称规则校验
	 *
	 * @modify liuyinkai
	 * @param nickName
	 *            用户昵称
	 * @return 0 - 通过，1 - 敏感词，2 - 中英文
	 */
	private Integer checkNickName(String nickName) {
		try {
			// 中文、英文、数字、下划线校验 4-24位
			Matcher m = CH_EN_PATTERN.matcher(nickName);
			if (!m.matches()) {
				return 2;
			}
			// 昵称敏感词校验
			List<SensitivityWord> sensitivityList = sensitivityWordMapper.querySensitiveName();
			if (Detect.notEmpty(sensitivityList)) {
				for (SensitivityWord obj : sensitivityList) {
					if (nickName.contains(obj.getContent())) {
						logger.info("昵称包含敏感词！");
						return 1;
					}
				}
			}
		} catch (Exception e) {
			logger.error("用户修改昵称校验异常！异常信息:{}", e.getMessage(), e);
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	@Override
	public CommonResponse register(UserRegisterRequest request) {
		// TODO Auto-generated method stub
		CommonResponse response = new CommonResponse();
		if (StringUtils.isNotBlank(request.getTel())) {
			Pattern p = Pattern.compile("^((1[0-9]))\\d{9}$");
			Matcher m = p.matcher(request.getTel());
			if (!m.matches()) {
				logger.info("手机号格式不正确");
				response.setCode(BizCode.CustomerError);
				response.setMessage("手机号格式不正确");
				return response;
			}
		}
		return saveUser(request);
	}

	/**
	 * 保存用户，并生成融云token
	 *
	 * @param request
	 * @return
	 */
	private CommonResponse saveUser(UserRegisterRequest request) {
		CommonResponse response = new CommonResponse();
		Customer param = new Customer();
		Customer customer = new Customer();
		// 手机号登录 验证码登录
		if (StringUtils.isNotBlank(request.getTel())) {
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
			response.setCode(BizCode.CustomerError);
			response.setMessage("手机号格式不正确");
			return response;
		}
		customer = new Customer();
		customer.setAppChannelName(request.getAppChannelName());
		customer.setDeviceId(request.getDeviceNo());
		customer.setCustomerId(UUIDUtils.getId());
		customer.setAppId(randomAppId());
		customer.setCreateTime(new Date());
		customer.setGtClientId(request.getGtClientId());
		customer.setMicroblogOpenId(request.getMicroblogOpenId());
		customer.setQqOpenId(request.getQqOpenId());
		customer.setWechatOpenId(request.getWechatOpenId());
		customer.setPhone(request.getTel());
		customer.setAppChannelName(request.getAppChannelName());
		customer.setDeviceId(request.getDeviceNo());
		customer.setSource(request.getScource());
		customer.setNickName(
				StringUtils.isNotBlank(request.getNickName()) ? request.getNickName() : "轻音_" + randomFour());
		String img = request.getHeardUrl();
		if (StringUtils.isBlank(img)) {
			img = defaultImg;
		}
		customer.setHeadPortraitUrl(img);
		if (StringUtils.isNotBlank(customer.getNickName())) {
			String rongyunToken = RongYunUtil.getToken(String.valueOf(customer.getCustomerId()), customer.getNickName(),
					img);
			if (rongyunToken == null || "".equals(rongyunToken)) {
				logger.error("用户获取融云token失败。用户ID为：" + customer.getCustomerId());
			}
			customer.setTokenCode(rongyunToken);
		}

		/**
		 * 声优白名单
		 */
		if (bigvPhoneMapper.queryOneByPhone(request.getTel()) != null) {
			customer.setIdentityStatus(2);
			customer.setvStatus(2);
		}

		int row = customerMapper.insertSelective(customer);
		if (row <= 0) {
			response.setCode(BizCode.CustomerError);
			response.setMessage("注册失败");
			return response;
		}

		DataBuriedPointRegistReq req = new DataBuriedPointRegistReq();
		req.setPhoneNumber(request.getTel());
		req.setRegistDate(new Date());
		req.setRegistSource(request.getAppChannelName());
		req.setUser_id("17356985474");
		dataDuriedPointBusiness.burySignUpResultData(req);
		// 创建账户
		accountDubboIntegrationService.createAccount(customer.getCustomerId());
		customer = customerMapper.selectByPrimaryKey(customer.getCustomerId());
		JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + customer.getCustomerId(),
				customer == null ? "" : JSON.toJSONString(customer));
		return ResultUtils.resultSuccess(customer);
	}

	/**
	 * 随机四位数
	 * 
	 * @return
	 */
	private int randomFour() {
		Random rand = new Random();
		int num = rand.nextInt(9999) + 1000;
		return num;
	}

	/**
	 * 随机八-十位 appId 并排除已有appId
	 * 
	 * @return
	 */
	private String randomAppId() {
		String[] appIdList = { "13140000", "131400000", "1314000000", "52000000", "520000000", "5200000000", "66666666",
				"666666666", "6666666666", "88888888", "888888888", "8888888888" };
		String num = getRandomNum(8);
		// 数据库不存在相同appId 且排除以上靓号
		while (customerMapper.selectByAppId(num) != null || Arrays.asList(appIdList).contains(num)) {
			num = getRandomNum(8);
		}
		return num;
	}

	/**
	 * 数字随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNum(int length) {
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		sb.append(random.nextInt(9) + 1);
		for (int i = 0; i < length - 1; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	@Override
	public CommonResponse getSmsCode(GetSmsCodeRequest params) {
		// TODO Auto-generated method stub
		CommonResponse response = new CommonResponse();
		Customer customer = null;
		Customer param = new Customer();
		if (StringUtils.isNotBlank(params.getTel())) {
			param.setPhone(params.getTel());
			Pattern p = Pattern.compile("^((1[0-9]))\\d{9}$");
			Matcher m = p.matcher(params.getTel());
			if (!m.matches()) {
				response.setCode(BizCode.CustomerError);
				response.setMessage("手机号格式不正确");
				return response;
			}
		}
		customer = customerMapper.login(param);
		if (customer == null) {
			if (!"2".equals(params.getCodeType())) {
				response.setCode(BizCode.CustomerError);
				response.setMessage("用户未注册");
				return response;
			}
		} else {
			if ("2".equals(params.getCodeType())) {
				response.setCode(BizCode.CustomerError);
				response.setMessage("用户已注册");
				return response;
			}
		}
		// 四位随机数
		int random = (int) ((Math.random() * 9 + 1) * 1000);
		String randomCode = random + "";

		return sendSms(params.getTel(), randomCode, params.getCodeType());
	}

	public CommonResponse sendSms(String phoneNum, String code, String codeType) {
		CommonResponse response = new CommonResponse();
		// 查一分钟
		if (JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_M + phoneNum) == null) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_M + phoneNum, "1", Integer.parseInt(resendexpire));
		} else {
			response.setCode(BizCode.CustomerError);
			response.setMessage("请勿重复发送验证码");
			return response;
		}

		// 查一小时
		if (StringUtils.isBlank(JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum))) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum, "1", Integer.parseInt(resendexpirehour));
		} else {
			String count = JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum);
			if (Integer.parseInt(count) >= Integer.parseInt(onehourfreq)) {
				response.setCode(BizCode.CustomerError);
				response.setMessage("验证码每小时只能获取五次");
				return response;
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
				response.setCode(BizCode.CustomerError);
				response.setMessage("验证码日发送次数已达到上限");
				return response;
			}
			String total = String.valueOf(Integer.parseInt(count) + 1);
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum, total,
					JedisUtil.ddlTimes(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum));
		}
		// 埋点
		DataBuriedPointGetCodeReq req = new DataBuriedPointGetCodeReq();
		req.setPhone(phoneNum);
		String smsStr = SendSmsUtil.sendSms(UUIDUtils.getUUID(), phoneNum, 1, code);
		if ("发送成功".equals(smsStr)) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code,
					Integer.parseInt(smscodeexpire));
			logger.info("手机号:" + phoneNum + " 手机验证码:" + code);
			logger.info("将验证码存入redis中的key值为:{},失效时间为:{}", (RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType),
					Integer.parseInt(smscodeexpire));
			req.setSuccess(true);
		} else {
			req.setSuccess(false);

		}
		logger.info("手机号:" + phoneNum + "发送状态:" + smsStr);
		// 埋点
		dataDuriedPointBusiness.buryGetCodeData(req);

		return ResultUtils.resultSuccess(smsStr);

		/*
		 * SendSmsResponse codeResponse; try { codeResponse =
		 * AliyunSmsCodeUtil.sendSms(phoneNum, code);
		 * 
		 * if (codeResponse != null && "OK".equals(codeResponse.getCode())) {
		 * JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code,
		 * Integer.parseInt(smscodeexpire)); logger.info("手机号:" + phoneNum + " 手机验证码:" +
		 * code); logger.info("将验证码存入redis中的key值为:{},失效时间为:{}",
		 * (RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType),
		 * Integer.parseInt(smscodeexpire)); return ResultUtils.resultSuccess(); } else
		 * { // 阿里云短信异常,用 "漫道"短信通道 发送 String mdResult =
		 * MandaoSmsCodeUtil.mdSmsSendSimple(phoneNum,
		 * MessageFormat.format(SmsTemplateEnum.login_auth_code_template.getContent(),
		 * code)); if (mdResult.startsWith("-") || mdResult.equals("")) { throw new
		 * BizException(UserBizReturnCode.DBError, "验证码发送失败，请检查手机号是否正常"); } else {
		 * logger.info("漫道发送验证码手机号:" + phoneNum + " 手机验证码:" + code);
		 * JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code,
		 * Integer.parseInt(smscodeexpire)); return ResultUtils.resultSuccess(); } } }
		 * catch (ClientException e) { // 阿里云短信异常,用 "漫道"短信通道 发送 String mdResult =
		 * MandaoSmsCodeUtil.mdSmsSendSimple(phoneNum,
		 * MessageFormat.format(SmsTemplateEnum.login_auth_code_template.getContent(),
		 * code)); if (mdResult.startsWith("-") || mdResult.equals("")) { throw new
		 * BizException(UserBizReturnCode.DBError, "验证码发送失败，请检查手机号是否正常"); } else {
		 * logger.info("漫道发送验证码手机号:" + phoneNum + " 手机验证码:" + code);
		 * JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code,
		 * Integer.parseInt(smscodeexpire)); return ResultUtils.resultSuccess(); } }
		 */
	}

	@Override
	public CommonResponse queryUserIdCardCertificationInfo(UserIdCardInfoRequest request) {
		if (request.getCustomerId() == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		Customer customer = customerMapper.queryUserIdCardCertificationInfo(request.getCustomerId());
		if (customer == null) {
			return ResultUtils.resultDataNotExist("用户数据不存在");
		}

		if (customer.getIdentityStatus() == null) {
			customer.setIdentityStatus(0);// 身份认证状态为空的时候，默认复制为未认证
		}

		// 姓名 -- 姓氏打*
		if (StringUtils.isNotBlank(customer.getRealName())) {
			customer.setRealName("*" + customer.getRealName().substring(1));
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
		if (request.getCustomerId() == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
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
		if (request.getCustomerId() == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		Long customerId = request.getCustomerId();
		Customer customer = customerMapper.selectByPrimaryKey(customerId);
		if (customer == null) {
			return ResultUtils.resultDataNotExist("用户数据不存在");
		}
		Customer record = new Customer();
		record.setCustomerId(customerId);
		record.setvVoiceUrlTmp(request.getVoiceUrl());
		record.setvVoiceTimeTmp(request.getVoiceTime());
		record.setVoiceStatus(UserBizConstants.VOICE_STATUS_UN_APPROVE);
		customerMapper.updateByPrimaryKeySelective(record);
		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse bindVXorQQ(BindVXorQQRequest request) {
		if (request.getCustomerId() == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		Customer param = new Customer();
		Customer customer = new Customer();

		// QQ判断
		if (StringUtils.isNotBlank(request.getQqOpenId())) {
			param.setQqOpenId(request.getQqOpenId());

		} // 微信判断
		else if (StringUtils.isNotBlank(request.getWechatOpenId())) {
			param.setWechatOpenId(request.getWechatOpenId());
		}
		// 判断是否已存在用户
		customer = customerMapper.login(param);
		param.setCustomerId(request.getCustomerId());
		if (customer != null) {
			logger.info("绑定账号已存在，不能绑定");
			throw new BizException(BizCode.ParamError, "绑定账号已存在，不能绑定");
		}
		int row = customerMapper.updateByPrimaryKeySelective(param);
		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse loginOut(LoginOutRequest request) {
		if (request.getCustomerId() == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		Long customerId = request.getCustomerId();
		Customer customer = customerMapper.selectByPrimaryKey(customerId);
		if (customer == null) {
			return ResultUtils.resultDataNotExist("用户数据不存在");
		}
		Customer cus = new Customer();
		cus.setCustomerId(customerId);
		cus.setCustState(CustomerCusStateEnum.OFF_LINE.getType());
		int row = customerMapper.updateByPrimaryKeySelective(cus);
		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse addSystemUser(AddSystemUserRequest request) {
		if (request.getCustomerId() == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		String rongyunToken = RongYunUtil.getToken(String.valueOf(request.getCustomerId()), request.getNickName(),
				request.getPhoto());
		Customer customer = new Customer();
		customer.setCustomerId(request.getCustomerId());
		customer.setHeadPortraitUrl(request.getPhoto());
		customer.setNickName(request.getNickName());
		customer.setTokenCode(rongyunToken);
		customer.setAppId(request.getAppId());
		int row = customerMapper.insertSelective(customer);
		return ResultUtils.resultSuccess();

	}

}
