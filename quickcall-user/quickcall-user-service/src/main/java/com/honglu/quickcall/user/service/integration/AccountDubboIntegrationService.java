package com.honglu.quickcall.user.service.integration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.business.AccountDubboBusiness;
import com.honglu.quickcall.account.facade.exchange.request.CreateUserAccountRequest;
import com.honglu.quickcall.common.api.code.MyServiceCode;
import com.honglu.quickcall.common.api.code.SourceCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;

/**
 * 引 Account dubbo 服务
 * @author liyingtang
 *
 */
@Service("accountDubboIntegrationService")
public class AccountDubboIntegrationService  {
    @Autowired
    private AccountDubboBusiness accountDubboBusiness;
    private final static Logger logger = LoggerFactory.getLogger(AccountDubboIntegrationService.class);
    
    /**
     * 创建账户
     * @param userId
     */
    public CommonResponse createAccount(Long userId) {
    	if(userId == null){
            throw new BizException(UserBizReturnCode.UserIdIsNotNull, UserBizReturnCode.UserIdIsNotNull.desc());
        }
    	CreateUserAccountRequest request = new CreateUserAccountRequest();
        request.setService(MyServiceCode.USER);
        request.setSource(SourceCode.OpenApi);
        request.setUserId(userId);
        logger.info("功能编码为"+request.getBizCode()+"发送请求：{}", request);
        CommonResponse response = accountDubboBusiness.excute(request);
        if(!response.isSuccess()){
            throw new RemoteException(response.getCode(), response.getMessage());
        }
        logger.info("返回结果{}", response);
        return response;
    }
    
   
}
