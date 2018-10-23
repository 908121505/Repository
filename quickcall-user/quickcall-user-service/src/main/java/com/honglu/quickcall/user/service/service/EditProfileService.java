package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.*;

/**
 * Created by cp on 2018/10/18.
 */
public interface EditProfileService {

    /**
     * 修改昵称
     * @param params
     * @return
     */
    CommonResponse updateNickName(UpdateNickNameReq params);
    /**
     * 修改头像
     * @param params
     * @return
     */
    CommonResponse updateHeadPortrait(UpdateHeadPortraitReq params);
    /**
     * 修改个性签名
     * @param params
     * @return
     */
    CommonResponse updateSignName(UpdateSignNameReq params);
    /**
     * 修改星座
     * @param params
     * @return
     */
    CommonResponse updateStarSign(UpdateStarSignReq params);
    /**
     * 修改形象照
     * @param params
     * @return
     */
    CommonResponse updateAppearance(UpdateAppearanceReq params);
    /**
     * 查询兴趣列表
     * @param params
     * @return
     */
    CommonResponse queryInterestList(QueryInterestListReq params);
    /**
     * 修改兴趣
     * @param params
     * @return
     */
    CommonResponse updateInterest(UpdateInterestReq params);
    /**
     * 删除形象照
     * @param params
     * @return
     */
    CommonResponse removeAppearance(RemoveAppearanceReq params);
    /**
     * 修改声鉴卡
     * @param params
     * @return
     */
    CommonResponse updateVoiceIdentificationCard(UpdateVoiceIdentificationCardReq params);
    /**
     * 删除声鉴卡
     * @param params
     * @return
     */
    CommonResponse removeVoiceIdentificationCard(RemoveVoiceIdentificationCardReq params);


}
