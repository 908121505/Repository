package com.honglu.quickcall.user.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.OSS.OSSUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.exchange.request.*;
import com.honglu.quickcall.user.web.service.UserCenterService;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


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
     * @param params
     * @return
     */
    @RequestMapping(value = "/regUserExist",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel regUserExist( IsPhoneExistsRequest params) {
    	logger.info("userWeb.user.regUserExist.request.data : " + JSONObject.toJSONString(params));
        WebResponseModel response = userCenterService.execute(params);
        logger.info("userWeb.user.regUserExist.response.data : " + JSONObject.toJSONString(response));
        return response;
    }
    /**
     * 检查用户是否存在
     * @param params
     * @return
     */
    @RequestMapping(value = "/register",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel register( UserRegisterRequest params) {
        WebResponseModel response =null;
        return response;
    }
    
    /**
     * 登录
     * @param params
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel login(UserLoginRequest params) {
    	
    	 WebResponseModel response=new WebResponseModel();
    	 return response;
    }
    /**
     * 设置密码
     * @param params
     * @return
     */
    @RequestMapping(value = "/setPwd",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel setpwd( SetPwdRequest params) {
        WebResponseModel response = new WebResponseModel();
        return response;
    }
    
    
    /**
     * 找回密码
     * @param params
     * @return
     */
    @RequestMapping(value = "/setHeardUrl",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel setHeardUrl( SetHeardUrlRequest params) {
        WebResponseModel response = new WebResponseModel();
        return response;
    }
    
   /* *//**
     * 退出登录
     * @param params
     * @return
     *//*
    @RequestMapping(value = "/loginout",  method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel loginout(LoginOutRequest params) {
        WebResponseModel response = userCenterService.execute(params);
        return response; 
    }*/
    
    /**@Title 上传图片
     * @modify liuyinkai
     * @param type、request
     * @return 
     */
    @RequestMapping(value = "/image", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel imageUpload(HttpServletRequest request){

        logger.info("userweb.user.image.request.data : " +request);
        String imgFolder = "user/photo";
        WebResponseModel response=new WebResponseModel();

        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            //上传图片不能为空
            if (file.isEmpty()) {
                logger.error("userweb.user.image.exception.data : 文件为空");
                response.setCode(UserBizReturnCode.paramError.code());
                response.setMsg("文件为空");
                return response;
            }

            String imageName = UUIDUtils.getUUID() + "." + "jpg";
            //阿里云客户端
            OSSClient ossClient = OSSUtil.getOSSClient();
            //上传
            boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, imgFolder);
            //图片访问路径拼接
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
    
    
    
    
    
}
