package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
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
        String diskUrl = request.getParameter("diskUrl");
        logger.info("上传文件 diskUrl = " + diskUrl + ", fileNames = " + file.getOriginalFilename());

        WebResponseModel response = CommonUtil.uploadFile(request, diskUrl);
        return response;
    }
}
