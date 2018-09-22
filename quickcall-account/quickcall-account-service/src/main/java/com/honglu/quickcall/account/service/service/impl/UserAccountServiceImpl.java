package com.honglu.quickcall.account.service.service.impl;

import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.exchange.request.CreateUserAccountRequest;
import com.honglu.quickcall.account.service.dao.AccountMapper;
import com.honglu.quickcall.account.service.service.UserAccountService;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.core.util.UUIDUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by len.song on 2017-12-16.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final static Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    @Autowired
    private AccountMapper userAccountMapper;

  

    @Override
    @Transactional
    public CommonResponse createAccount(CreateUserAccountRequest request) {
        if(request == null || request.getUserId() == null){
            throw new BizException(AccountBizReturnCode.paramError,"创建账户参数异常");
        }
        logger.info("用户编号为："+request.getUserId() +"的用户开始创建账户...");
        Account userAccount = new Account(UUIDUtils.getId(),request.getUserId());

        userAccountMapper.createUserAccount(userAccount);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(BizCode.Success);
        commonResponse.setMessage(BizCode.Success.desc());
        logger.info("用户编号为："+request.getUserId() +"的账户创建成功...");
        return commonResponse;
    }



	



	

   


}
