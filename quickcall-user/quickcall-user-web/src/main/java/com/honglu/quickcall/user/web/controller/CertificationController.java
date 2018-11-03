package com.honglu.quickcall.user.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.honglu.quickcall.common.api.code.AliYunFilePaths;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.OSS.OSSUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.constants.UserBizConstants;
import com.honglu.quickcall.user.facade.exchange.request.SaveCertificationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveDvVoiceRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveSkillAuditRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserIdCardInfoRequest;
import com.honglu.quickcall.user.web.service.UserCenterService;
import com.honglu.quickcall.user.web.util.AudioUtils;

/**
 * 身份、大V -- 证Controller
 *
 * @author duanjun
 * @date 2018-09-22 20:22
 */
@Controller
@RequestMapping("/certification")
public class CertificationController {

	private static Logger logger = LoggerFactory.getLogger(CertificationController.class);

	@Autowired
	private UserCenterService userCenterService;

	/**
	 * 用户身份认证信息查询接口
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/idCardInfo", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel idCardInfo(UserIdCardInfoRequest params) {
		logger.info("userWeb.certification idCardInfo request data : " + params);
		WebResponseModel response = new WebResponseModel();
		if (params.getCustomerId() == null) {
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
		} else {
			response = userCenterService.execute(params);
		}
		logger.info("userWeb.certification idCardInfo response data : " + response);
		return response;
	}

	/**
	 * 上传身份证认证信息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/identityCertification", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel identityCertification(HttpServletRequest request) {
		logger.info("userWeb.certification idCardImageUpload request data : " + request);
		WebResponseModel response = new WebResponseModel();
		response.setCode(UserBizReturnCode.paramError.code());
		SaveCertificationRequest params = new SaveCertificationRequest();
		String customerId = request.getParameter("customerId");
		if (StringUtils.isBlank(customerId)) {
			response.setMsg("客户Id为空");
			return response;
		}
		params.setCustomerId(Long.valueOf(customerId));
		params.setRealName(request.getParameter("realName"));
		if (StringUtils.isBlank(params.getRealName())) {
			response.setMsg("真实姓名为空");
			return response;
		}
		String credentialsType = request.getParameter("credentialsType");
		if (StringUtils.isBlank(credentialsType)) {
			response.setMsg("证件类型为空");
			return response;
		}
		params.setCredentialsType(Integer.valueOf(credentialsType));
		params.setCredentialsNum(request.getParameter("credentialsNum"));
		if (StringUtils.isBlank(params.getCredentialsNum())) {
			response.setMsg("证件号码为空");
			return response;
		}

		// 保存认证信息
		params.setCertifyType(1);// 身份认证
		response = userCenterService.execute(params);
		logger.info("userWeb.certification idCardImageUpload response data : " + response);
		return response;
	}

	/**
	 * 上传身份证图片
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/idCardImageUpload", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel idCardImageUpload(HttpServletRequest request) {
		logger.info("userWeb.certification idCardImageUpload request data : " + request);
		// 身份证类型：1证明；2反面
		String idCardType = request.getParameter("idCardType");
		String customerId = request.getParameter("customerId");

		if (StringUtils.isBlank(customerId) || StringUtils.isBlank(idCardType)) {
			WebResponseModel response = new WebResponseModel();
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg(UserBizReturnCode.paramError.desc());
			return response;
		}

		WebResponseModel response = uploadFile(request, AliYunFilePaths.IDENTITY_AUTH_ID_CARD_IMAGE);
		if ("000000".equals(response.getCode())) {
			// 保存身份证图片
			SaveCertificationRequest params = new SaveCertificationRequest();
			params.setCustomerId(Long.valueOf(customerId));
			if ("1".equals(idCardType)) {
				params.setFrontPortraitUrl(response.getData());
			} else {
				params.setBackPortraitUrl(response.getData());
			}
			params.setCertifyType(0);// 身份认证
			response = userCenterService.execute(params);
		}
		logger.info("userWeb.certification idCardImageUpload response data : " + response);
		return response;
	}

	/**
	 * 大V身份认证自我介绍音频上传
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/introduceAudioUpload", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel introduceAudioUpload(HttpServletRequest request) {
		logger.info("userWeb.certification introduceAudioUpload request data : " + request);
		String customerId = request.getParameter("customerId");
		if (StringUtils.isBlank(customerId)) {
			WebResponseModel response = new WebResponseModel();
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg("客户ID为空");
			return response;
		}
		WebResponseModel response = uploadFile(request, AliYunFilePaths.BIG_V_INTRODUCE_AUDIO);
		if ("000000".equals(response.getCode())) {
			String voiceTimeStr = request.getParameter("voiceTime");
			String requestType = request.getParameter("requestType");
			if (UserBizConstants.UPLOAD_AUDIT_REQUEST.equals(requestType)) {
				// 大V自己的声音非大V认证声音
				SaveDvVoiceRequest params = new SaveDvVoiceRequest();
				if (StringUtils.isNotBlank(voiceTimeStr)) {
					params.setVoiceTime(new BigDecimal(voiceTimeStr).setScale(1, BigDecimal.ROUND_HALF_UP));
				}
				params.setCustomerId(Long.valueOf(customerId));
				params.setVoiceUrl(response.getData());
				if (StringUtils.isNotBlank(voiceTimeStr)) {
					params.setVoiceTime(new BigDecimal(voiceTimeStr).setScale(1, BigDecimal.ROUND_HALF_UP));
				}
				response = userCenterService.execute(params);
				response.setData(response.getData());
			} else {
				// 大V认证声音上传
				SaveCertificationRequest params = new SaveCertificationRequest();
				if (StringUtils.isNotBlank(voiceTimeStr)) {
					params.setVoiceTime(new BigDecimal(voiceTimeStr).setScale(1, BigDecimal.ROUND_HALF_UP));
				}
				params.setCustomerId(Long.valueOf(customerId));
				params.setVoiceUrl(response.getData());
				params.setCertifyType(2);// 大V认证
				response = userCenterService.execute(params);
			}
		}
		logger.info("userWeb.certification introduceAudioUpload response data : " + request);
		return response;
	}

	/**
	 * 大V技能声音审核
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/skillAuditUpload", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public WebResponseModel skillAuditUpload(HttpServletRequest request) {
		logger.info("userWeb.certification skillAuditUpload request data : " + request);
		String customerId = request.getParameter("customerId");
		String skillItemId = request.getParameter("skillItemId");
		String skillVoiceTime = request.getParameter("skillVoiceTime");
		if (StringUtils.isBlank(customerId)) {
			WebResponseModel response = new WebResponseModel();
			response.setCode(UserBizReturnCode.paramError.code());
			response.setMsg("客户ID为空");
			return response;
		}
		// 上传技能声音文件
		WebResponseModel response = uploadFile(request, AliYunFilePaths.CUSTOMER_SKILL_CERTIFY_AUDIO);
		if ("000000".equals(response.getCode())) {
			SaveSkillAuditRequest params = new SaveSkillAuditRequest();
			params.setCustomerId(Long.valueOf(customerId));
			params.setSkillItemId(Long.valueOf(skillItemId));
			params.setSkillVoiceTime(new BigDecimal(skillVoiceTime).setScale(1, BigDecimal.ROUND_HALF_UP));
			params.setSkillVoiceUrl(response.getData());
			// 调用保存技能认证服务
			response = userCenterService.execute(params);
		}
		logger.info("userWeb.certification skillAuditUpload response data : " + response);

		return response;
	}

	/**
	 * 上传文件
	 *
	 * @param request
	 * @param diskName
	 *            存放磁盘路径
	 * @return
	 */
	private WebResponseModel uploadFile(HttpServletRequest request, String diskName) {
		WebResponseModel response = new WebResponseModel();
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile mfile = multiRequest.getFile("file");

			// 上传图片不能为空
			if (mfile == null || mfile.isEmpty()) {
				response.setCode(UserBizReturnCode.paramError.code());
				response.setMsg("文件为空");
				return response;
			}

			// 提取文件后缀名
			// String fileName = file.getOriginalFilename();
			// String extName = fileName.substring(fileName.indexOf("."));

			// String imageName = UUIDUtils.getUUID() + extName;
			String imageName = UUIDUtils.getUUID();
			File f = (File) mfile;
			// 音频文件统一转MP3格式

			File file = AudioUtils.wavTomp3(f, imageName);
			InputStream input = new FileInputStream(file);
			// 阿里云客户端
			OSSClient ossClient = OSSUtil.getOSSClient();
			// 上传
			boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, input, imageName + ".jpg", diskName);
			// 图片访问路径拼接
			if (flag) {
				response.setCode(UserBizReturnCode.Success.code());
				response.setMsg(UserBizReturnCode.Success.desc());
				response.setData(OSSUtil.ossUrl + "/" + diskName + "/" + imageName + ".jpg");
			} else {
				response.setCode(UserBizReturnCode.Unknown.code());
				response.setMsg("文件上传失败");
			}
			file.delete();
		} catch (Exception e) {
			logger.error("userWeb.certification upload file exception : ", e);
			response.setCode(UserBizReturnCode.Unknown.code());
			response.setMsg("文件上传失败，" + e.getMessage());
		}
		return response;
	}

}
