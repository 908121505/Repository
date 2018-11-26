package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.*;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 个人中心 -> 编辑资料
 *
 * @author chenpeng
 * @date 2018/10/18 10:26
 */
@RestController
@RequestMapping(value = "/personalCenter")
public class EditProfileController {

    private static final Logger logger = LoggerFactory.getLogger(EditProfileController.class);

    @Autowired
    private UserCenterService userCenterService;

    /**
     * 修改昵称
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/updateNickName")
    public WebResponseModel updateNickName(UpdateNickNameReq params) {
        logger.info("修改昵称 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 修改头像
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/updateHeadPortrait")
    public WebResponseModel updateHeadPortrait(UpdateHeadPortraitReq params) {
        logger.info("修改头像 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 修改个性签名
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/updateSignName")
    public WebResponseModel updateSignName(UpdateSignNameReq params) {
        logger.info("修改个性签名 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 修改星座
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/updateStarSign")
    public WebResponseModel updateStarSign(UpdateStarSignReq params) {
        logger.info("修改星座 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 查询兴趣列表
     *
     * @return
     */
    @RequestMapping(value = "/queryInterestList")
    public WebResponseModel queryInterestList() {
        QueryInterestListReq params = new QueryInterestListReq();
        logger.info("查询兴趣列表");
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 修改兴趣
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/updateInterest")
    public WebResponseModel updateInterest(UpdateInterestReq params) {
        logger.info("修改兴趣 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 修改形象照
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/updateAppearance")
    public WebResponseModel updateAppearance(UpdateAppearanceReq params) {
        logger.info("修改形象照 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 删除形象照
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/removeAppearance")
    public WebResponseModel removeAppearance(RemoveAppearanceReq params) {
        logger.info("删除形象照 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
   /**
     * 修改声鉴卡
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/updateVoiceIdentificationCard")
    public WebResponseModel updateVoiceIdentificationCard(UpdateVoiceIdentificationCardReq params) {
        logger.info("修改声鉴卡 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 删除声鉴卡
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/removeVoiceIdentificationCard")
    public WebResponseModel removeVoiceIdentificationCard(RemoveVoiceIdentificationCardReq params) {
        logger.info("删除声鉴卡 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }

    /**
     * 修改性别
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/updateGender")
    public WebResponseModel updateGender(UpdateGenderReq params) {
        logger.info("修改性别 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 修改年龄
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/updateBirthday")
    public WebResponseModel updateBirthday(UpdateBirthdayReq params) {
        logger.info("修改年龄 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 编辑资料页面，查询用户信息
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/queryUserEditInfo")
    public WebResponseModel queryUserEditInfo(QueryUserEditInfoReq params) {
        logger.info("编辑资料页面，查询用户信息 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }


}
