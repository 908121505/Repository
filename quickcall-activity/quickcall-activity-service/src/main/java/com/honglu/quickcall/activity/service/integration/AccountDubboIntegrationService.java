package com.honglu.quickcall.activity.service.integration;

import com.honglu.quickcall.account.facade.business.AccountCommonBusiness;
import com.honglu.quickcall.account.facade.business.AccountDubboBusiness;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.AccountInOutEnum;
import com.honglu.quickcall.account.facade.enums.AccountOperatorTypeEnum;
import com.honglu.quickcall.account.facade.exchange.request.*;
import com.honglu.quickcall.activity.facade.code.ActivityBizReturnCode;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.code.MyServiceCode;
import com.honglu.quickcall.common.api.code.SourceCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;

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
