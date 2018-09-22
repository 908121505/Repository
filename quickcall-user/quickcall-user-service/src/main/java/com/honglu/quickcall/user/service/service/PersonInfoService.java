package com.honglu.quickcall.user.service.service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;

/**
 * 
 * @author liuyinkai
 *
 */
public interface PersonInfoService {
	
	/**
     * 根据用户id 获取用户信息
     *
     * @param params
     * @return
     */
    CommonResponse queryPersonInfo( PersonInfoRequest params);
}
