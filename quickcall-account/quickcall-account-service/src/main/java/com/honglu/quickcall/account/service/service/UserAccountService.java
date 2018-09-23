package com.honglu.quickcall.account.service.service;

import com.honglu.quickcall.account.facade.exchange.request.CreateUserAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.InAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.OutAccountRequest;
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
    
    /**
     * 查询账户
     * @param request
     * @return
     */
    CommonResponse qyeryAccount(QueryAccountRequest request);
    
    /**
     * 入账
     * @param request
     * @return
     */
    CommonResponse inAccount(InAccountRequest request);
    
    /**
     * 出账
     * @param request
     * @return
     */
    CommonResponse outAccount(OutAccountRequest request);
    
    

   
}
