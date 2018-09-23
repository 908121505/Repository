package com.honglu.quickcall.user.web.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.honglu.quickcall.common.api.util.ResponseUtils;
import com.honglu.quickcall.common.core.util.Assertion;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.OSS.OSSUtil;

/**
 * Created by bruce on 2017/6/8.
 */
@Controller
@RequestMapping("/upload")
public class ImgUploadController {

    private static Logger logger = LoggerFactory.getLogger(ImgUploadController.class);
    /**@Title 上传图片
     * @modify liuyinkai
     * @param type、request
     * @return 
     */
    @RequestMapping(value = "/image", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> imageUpload(HttpServletRequest request, Integer type){

        logger.info("communitycenterweb.upload.image.request.data : " + type);
        String imgFolder;
        HashMap<String, Object> map = new HashMap<>();

        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            //上传图片不能为空
            if (file.isEmpty()) {
                logger.error("communitycenterweb.upload.image.exception.data : 文件为空");
                return ResponseUtils.getFailedData("图片不能为空");
            }
            //type=1 社区头像  2社区发帖
            Assertion.isPositive(type, "类型不能为空");

            switch (type) {
                case 1:
                    imgFolder = "community/photo";
                    break;
                case 2:
                    imgFolder = "community/post";
                    break;
                default:
                    return ResponseUtils.getFailedData("类型错误");
            }

            String imageName = UUIDUtils.getUUID() + "." + "jpg";
            //阿里云客户端
            OSSClient ossClient = OSSUtil.getOSSClient();
            //上传
            boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, imgFolder);
            //图片访问路径拼接
            if (flag) {
                map.put("url", OSSUtil.ossUrl + "/" + imgFolder + "/" + imageName);
                logger.info("communitycenterweb.upload.image.response.data : " + JSON.toJSONString(map));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("communitycenterweb.upload.image.exception.data : " + e.getMessage());
            return ResponseUtils.getFailedData("图片上传失败");
        }
        return ResponseUtils.getSuccessData("图片上传成功", map);
    }

}
