package com.honglu.quickcall.user.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.honglu.quickcall.common.api.code.AliYunFilePaths;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.OSS.OSSUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.exchange.request.*;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by len.song on 2017-12-08.
 */
@Controller
@RequestMapping("/user")
public class UserCommonController {
	private final static Logger logger = LoggerFactory.getLogger(UserCommonController.class);

	@Autowired
	private UserCenterService userCenterService;

	/**
	 * 检查用户是否存在
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/regUserExist", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel regUserExist(IsPhoneExistsRequest params) {
		logger.info("userWeb.user.regUserExist.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (params.getTel() == null && StringUtils.isBlank(params.getQqOpenId())
				&& StringUtils.isBlank(params.getWechatOpenId()) && StringUtils.isBlank(params.getMicroblogOpenId())
				&& StringUtils.isBlank(params.getNickName())) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.regUserExist.response.data : " + JSONObject.toJSONString(response));
		return response;
	}

	/**
	 * 注册
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel register(UserRegisterRequest params) {
		logger.info("userWeb.user.register.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (StringUtils.isBlank(params.getTel()) && StringUtils.isBlank(params.getQqOpenId())
				&& StringUtils.isBlank(params.getWechatOpenId()) && StringUtils.isBlank(params.getMicroblogOpenId())) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		if (params.getVerifyCode() != null && params.getCodeType() != null && !"".equals(params.getVerifyCode())
				&& !"".equals(params.getCodeType())) {
			if (StringUtils.isBlank(
					JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE + params.getTel() + params.getCodeType()))) {
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("验证码失效请重新获取");
				return response;
			}
			if (!params.getVerifyCode().equals(
					JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE + params.getTel() + params.getCodeType()))) {
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("验证码输入不正确");
				return response;
			}
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.register.response.data : " + JSONObject.toJSONString(response));
		return response;
	}

	/**
	 * 登录
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel login(UserLoginRequest params) {

		logger.info("userWeb.user.login.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (StringUtils.isBlank(params.getTel()) && StringUtils.isBlank(params.getQqOpenId())
				&& StringUtils.isBlank(params.getWechatOpenId()) && StringUtils.isBlank(params.getMicroblogOpenId())) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		if (params.getVerifyCode() != null && params.getCodeType() != null && !"".equals(params.getVerifyCode())
				&& !"".equals(params.getCodeType())) {
			if (StringUtils.isBlank(
					JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE + params.getTel() + params.getCodeType()))) {
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("验证码失效请重新获取");
				return response;
			}
			if (!params.getVerifyCode().equals(
					JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE + params.getTel() + params.getCodeType()))) {
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("验证码输入不正确");
				return response;
			}
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.login.response.data : " + JSONObject.toJSONString(response));
		return response;
	}

	/**
	 * 设置密码
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/setPwd", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel setpwd(SetPwdRequest params) {

		logger.info("userWeb.user.setpwd.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (StringUtils.isBlank(params.getTel()) || StringUtils.isBlank(params.getPassWord())) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		if (params.getVerifyCode() != null && params.getCodeType() != null && !"".equals(params.getVerifyCode())
				&& !"".equals(params.getCodeType())) {
			if (StringUtils.isBlank(
					JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE + params.getTel() + params.getCodeType()))) {
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("验证码失效请重新获取");
				return response;
			}
			if (!params.getVerifyCode().equals(
					JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE + params.getTel() + params.getCodeType()))) {
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("验证码输入不正确");
				return response;
			}
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.setpwd.response.data : " + JSONObject.toJSONString(response));
		return response;
	}

	/**
	 * 设置头像 昵称
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/setHeardUrl", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel setHeardUrl(SetHeardUrlRequest params) {
		logger.info("userWeb.user.setHeardUrl.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (StringUtils.isBlank(params.getTel())) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}

		response = userCenterService.execute(params);
		logger.info("userWeb.user.setHeardUrl.response.data : " + JSONObject.toJSONString(response));
		return response;
	}

	/**
	 * 退出登录
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel loginout(LoginOutRequest params) {
		WebResponseModel response = new WebResponseModel();
		if (params.getCustomerId() == null) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		response = userCenterService.execute(params);
		return response;
	}

	/**
	 * @Title 上传用户头像
	 * @modify liuyinkai
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadHeadImage", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel imageUpload(HttpServletRequest request) {
		logger.info("userweb.user.image.req.data : " + request);

		WebResponseModel response = new WebResponseModel();

		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multiRequest.getFile("file");
			// 上传图片不能为空
			if (file.isEmpty()) {
				logger.error("userweb.user.image.exception.data : 文件为空");
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("文件为空");
				return response;
			}

			String imageName = UUIDUtils.getUUID() + "." + "jpg";
			String imgFolder = AliYunFilePaths.USER_UPLOAD_HEAD_IMG;
			// 阿里云客户端
			OSSClient ossClient = OSSUtil.getOSSClient();
			// 上传
			boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, imgFolder);
			// 图片访问路径拼接
			if (flag) {
				response.setData(OSSUtil.ossUrl + "/" + imgFolder + "/" + imageName);
				logger.info("userweb.user.image.response.data : " + response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("communitycenterweb.upload.image.exception.data : " + e.getMessage());
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg("图片上传失败");
			return response;
		}
		response.setCode(UserBizReturnCode.Success.code());
		response.setMsg(UserBizReturnCode.Success.desc());
		return response;
	}

	/**
	 * 发送短信
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/getSmsCode", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel getSmsCode(GetSmsCodeRequest params) {

		logger.info("userWeb.user.getSmsCode.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (StringUtils.isBlank(params.getTel()) || StringUtils.isBlank(params.getCodeType())) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.getSmsCode.response.data : " + JSONObject.toJSONString(response));
		return response;

	}

	/**
	 * 个人中心接口
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/customerCenter", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel customerCenter(CustomerCenterRequest params) {
		logger.info("userWeb.user.customerCenter.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (params.getCustomerId() == null) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.customerCenter.response.data : " + JSONObject.toJSONString(response));
		return response;

	}

	/**
	 * 客户个人主页接口
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/customerHome", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel customerHome(CustomerHomeRequest params) {
		logger.info("userWeb.user.customerHome.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (params.getViewCustomerId() == null) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.customerHome.response.data : " + JSONObject.toJSONString(response));
		return response;

	}

	/**
	 * 我的等级页面 -- 接口
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/customerLevel", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel customerLevel(CustomerLevelRequest params) {
		logger.info("userWeb.user.customerLevel.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (params.getCustomerId() == null) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.customerLevel.response.data : " + JSONObject.toJSONString(response));
		return response;

	}

	/**
	 * 绑定微信或者QQ
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/bindVXorQQ", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel bindVXorQQ(BindVXorQQRequest params) {

		logger.info("userWeb.user.bindVXorQQ.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (params.getCustomerId() == null) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.bindVXorQQ.response.data : " + JSONObject.toJSONString(response));
		return response;

	}

	/**
	 * 绑定微信或者QQ
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/addSystemUser", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel addSystemUser(AddSystemUserRequest params) {

		logger.info("userWeb.user.addSystemUser.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (params.getCustomerId() == null) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}
		response = userCenterService.execute(params);
		logger.info("userWeb.user.addSystemUser.response.data : " + JSONObject.toJSONString(response));
		return response;

	}

	/**
	 * 绑定微信或者QQ
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/checkVerifyCode", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel checkVerifyCode(SetPwdRequest params) {

		logger.info("userWeb.user.checkVerifyCode.req.data : " + JSONObject.toJSONString(params));
		WebResponseModel response = new WebResponseModel();
		if (params.getVerifyCode() != null && params.getCodeType() != null && !"".equals(params.getVerifyCode())
				&& !"".equals(params.getCodeType())) {
			if (StringUtils.isBlank(
					JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE + params.getTel() + params.getCodeType()))) {
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("验证码失效请重新获取");
				return response;
			}
			if (!params.getVerifyCode().equals(
					JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE + params.getTel() + params.getCodeType()))) {
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("验证码输入不正确");
				return response;
			}
		}
		response.setCode(UserBizReturnCode.Success.code());
		response.setMsg("验证成功");

		logger.info("userWeb.user.checkVerifyCode.response.data : " + JSONObject.toJSONString(response));
		return response;

	}
}
