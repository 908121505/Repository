package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveNickNameRequest;

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
    /**
      * 保存昵称头像
     *
     * @param params
     * @return
     */
    CommonResponse saveNicknameImage( SaveNickNameRequest params);
    
    
}
