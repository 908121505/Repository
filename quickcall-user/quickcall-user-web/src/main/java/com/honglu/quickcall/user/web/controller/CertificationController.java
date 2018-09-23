package com.honglu.quickcall.user.web.controller;

import com.aliyun.oss.OSSClient;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.OSS.OSSUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 用户身份证图片存放路径
     **/
    private static final String ID_CARD_IMAGE_FOLDER = "user/idcard";
    /**
     * 大V认证介绍录音文件存放路径
     **/
    private static final String BIG_V_INTRODUCE_AUDIO = "user/audio";

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
        WebResponseModel response = uploadFile(request, ID_CARD_IMAGE_FOLDER);
        logger.info("userWeb.certification idCardImageUpload response data : " + request);
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
        WebResponseModel response = uploadFile(request, BIG_V_INTRODUCE_AUDIO);
        logger.info("userWeb.certification introduceAudioUpload response data : " + request);
        return response;
    }

    /**
     * 上传文件
     *
     * @param request
     * @param diskName 存放磁盘路径
     * @return
     */
    private WebResponseModel uploadFile(HttpServletRequest request, String diskName) {
        WebResponseModel response = new WebResponseModel();
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            //上传图片不能为空
            if (file == null || file.isEmpty()) {
                response.setCode(UserBizReturnCode.emptyError.code());
                response.setMsg("文件为空");
                return response;
            }
            // 提取文件后缀名
            String fileName = file.getOriginalFilename();
            String extName = fileName.substring(fileName.indexOf("."));

            String imageName = UUIDUtils.getUUID() + extName;
            //阿里云客户端
            OSSClient ossClient = OSSUtil.getOSSClient();
            //上传
            boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, diskName);
            //图片访问路径拼接
            if (flag) {
                response.setCode(UserBizReturnCode.Success.code());
                response.setMsg(UserBizReturnCode.Success.desc());
                response.setData(OSSUtil.ossUrl + "/" + diskName + "/" + imageName);
            } else {
                response.setCode(UserBizReturnCode.Unknown.code());
                response.setMsg("文件上传失败");
            }
        } catch (Exception e) {
            logger.error("userWeb.certification upload file exception : ", e);
            response.setCode(UserBizReturnCode.Unknown.code());
            response.setMsg("文件上传失败，" + e.getMessage());
        }
        return response;
    }


}
