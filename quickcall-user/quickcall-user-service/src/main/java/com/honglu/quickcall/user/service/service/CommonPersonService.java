package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.*;

/**
 * Created by len.song on 2017-12-07.
 */
public interface CommonPersonService {
	 /**
     * 根据用户id 获取用户详情
     *
     * @param request
     * @return
     */
    CommonResponse regUserExist( IsPhoneExistsRequest params);
    
    /**
     * 注册
     *
     * @param request
     * @return
     */
    CommonResponse register(UserRegisterRequest request);
    
    /**
     * 登录
     * @param params
     * @return
     */
    CommonResponse login(UserLoginRequest params);
    /**
     * 设置密码
     * @param params
     * @return
     */
    CommonResponse setpwd( SetPwdRequest params);
    
    /**
     * 设置用户头像，昵称
     * @param params
     * @return
     */
    CommonResponse setHeardUrl( SetHeardUrlRequest params);
    
    /**
     * 获取验证码
     * @param params
     * @return
     */
    CommonResponse getSmsCode(GetSmsCodeRequest params);

    /**
     * 查询用户身份认证信息
     * @param request
     * @return
     */
    CommonResponse queryUserIdCardCertificationInfo(UserIdCardInfoRequest request);

    /**
     * 保存用户认证信息
     * @param request
     * @return
     */
    CommonResponse saveUserCertificationInfo(SaveCertificationRequest request);
}
