package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetHeardUrlRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetPwdRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserLoginRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserRegisterRequest;

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
     * 
     * @param params
     * @return
     */
    CommonResponse setHeardUrl( SetHeardUrlRequest params);
    
}
