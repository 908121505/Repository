package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.AddSystemUserRequest;
import com.honglu.quickcall.user.facade.exchange.request.BindVXorQQRequest;
import com.honglu.quickcall.user.facade.exchange.request.GetSmsCodeRequest;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.LoginOutRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveCertificationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveDvVoiceRequest;
import com.honglu.quickcall.user.facade.exchange.request.SearchPersonByPhoneRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetHeardUrlRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetPwdRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserIdCardInfoRequest;
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
	CommonResponse regUserExist(IsPhoneExistsRequest params);

	/**
	 * 注册
	 *
	 * @param request
	 * @return
	 */
	CommonResponse register(UserRegisterRequest request);

	/**
	 * 登录
	 * 
	 * @param params
	 * @return
	 */
	CommonResponse login(UserLoginRequest params);

	/**
	 * 设置密码
	 * 
	 * @param params
	 * @return
	 */
	CommonResponse setpwd(SetPwdRequest params);

	/**
	 * 设置用户头像，昵称
	 * 
	 * @param params
	 * @return
	 */
	CommonResponse setHeardUrl(SetHeardUrlRequest params);

	/**
	 * 获取验证码
	 * 
	 * @param params
	 * @return
	 */
	CommonResponse getSmsCode(GetSmsCodeRequest params);

	/**
	 * 查询用户身份认证信息
	 * 
	 * @param request
	 * @return
	 */
	CommonResponse queryUserIdCardCertificationInfo(UserIdCardInfoRequest request);

	/**
	 * 保存用户认证信息
	 * 
	 * @param request
	 * @return
	 */
	CommonResponse saveUserCertificationInfo(SaveCertificationRequest request);

	/**
	 * 保存
	 * 
	 * @param request
	 * @return
	 */
	CommonResponse saveDvVoiceInfo(SaveDvVoiceRequest request);

	/**
	 * 绑定微信或者QQ
	 * 
	 * @param request
	 * @return
	 */
	CommonResponse bindVXorQQ(BindVXorQQRequest request);

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return
	 */
	CommonResponse loginOut(LoginOutRequest request);

	/**
	 * 添加系统用户
	 * 
	 * @param request
	 * @return
	 */
	CommonResponse addSystemUser(AddSystemUserRequest request);
	
	/**
     * 根据手机号，查询用户信息
     * @param request
     * @return
     */
	CommonResponse searchPersonByPhone(SearchPersonByPhoneRequest request);

}
