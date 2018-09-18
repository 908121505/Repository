package com.honglu.quickcall.user.service.integration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.business.AccountDubboBusiness;

/**
 * 引 Account dubbo 服务
 * @author liyingtang
 *
 */
@Service("accountDubboIntegrationService")
public class AccountDubboIntegrationService {
    @Autowired
    private AccountDubboBusiness accountDubboBusiness;
    private final static Logger logger = LoggerFactory.getLogger(AccountDubboIntegrationService.class);
    
   
}
