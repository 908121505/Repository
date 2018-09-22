package com.honglu.quickcall.activity.service.integration;

import com.honglu.quickcall.account.facade.business.AccountDubboBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("accountDubboIntegrationService")
public class AccountDubboIntegrationService {
    @Autowired
    private AccountDubboBusiness accountDubboBusiness;


    private final static Logger logger = LoggerFactory.getLogger(AccountDubboIntegrationService.class);
    
   
}
