package com.honglu.quickcall.account.service.service;

import com.honglu.quickcall.account.facade.exchange.request.CreateUserAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryAccountRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

/**
 * Created by len.song on 2017-12-16.
 */
public interface UserAccountService {
    /**
     * 创建账户
     * @param request
     * @return
     */
    CommonResponse createAccount(CreateUserAccountRequest request);
    
    
    CommonResponse qyeryAccount(QueryAccountRequest request);

   
}
