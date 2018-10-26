package com.honglu.quickcall.common.third.weixin.service;

import com.honglu.quickcall.common.third.weixin.models.User;

/**
 * 定义第三方登录接口
 * @author GunnyZeng
 *
 */
public interface OAuthApiService {
	/**
	 * 登录跳转认证授权地址
	 * @return 封装的授权url
	 */
	public String authorize();
	/**
	 * 授权后，数据获取
	 * @param 参数默认为code
	 * @return 登录的用户信息
	 */
	public User getUser(Object parame) throws Exception;
}
