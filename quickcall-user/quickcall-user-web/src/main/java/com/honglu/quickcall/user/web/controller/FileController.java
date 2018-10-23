package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.code.AliYunFilePaths;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.core.util.StringUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.web.type.AppearanceTypeEnum;
import com.honglu.quickcall.user.web.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 文件操作
 *
 * @author chenpeng
 * @date 2018/10/18 21:24
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping(value = "/upload")
    public WebResponseModel upload(HttpServletRequest request) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("file");
        String fileType = request.getParameter("fileType");
        logger.info("上传文件 fileType = " + fileType + ", fileNames = " + file.getOriginalFilename());

        if (StringUtil.isBlank(fileType)) {
            WebResponseModel response = new WebResponseModel();
            response.setCode(UserBizReturnCode.paramError.code());
            response.setMsg("存放磁盘路径为空");
            return response;
        }
        if (file == null || file.isEmpty()) {
            WebResponseModel response = new WebResponseModel();
            response.setCode(UserBizReturnCode.paramError.code());
            response.setMsg("文件为空");
            return response;
        }

        Integer fileTypeInt = Integer.parseInt(fileType);
        //文件上传到服务器上的路径
        String directory = "";
        if((AppearanceTypeEnum.APPEARANCE.getCode()).equals(fileTypeInt)){
            directory = AliYunFilePaths.USER_APPEARANCE_DIR;
        }else if((AppearanceTypeEnum.HEAD_PORTRAIT.getCode()).equals(fileTypeInt)){
            directory = AliYunFilePaths.USER_UPLOAD_HEAD_IMG;
        }else if((AppearanceTypeEnum.VOICE_IDENTIFICATION_CARD.getCode()).equals(fileTypeInt)){
            directory = AliYunFilePaths.USER_VOICE_IDENTIFICATION_CARD_DIR;
        }
        WebResponseModel response = CommonUtil.uploadFile(request, directory);
        return response;
    }
}
