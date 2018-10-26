package com.honglu.quickcall.account.service.integration.impl;


import com.honglu.quickcall.account.service.integration.UserIntegrationService;
import com.honglu.quickcall.user.facade.business.UserDubboBusiness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by len.song on 2017-12-16.
 */
@Service
public class UserIntegrationServiceImpl implements UserIntegrationService {
    private final static Logger logger = LoggerFactory.getLogger(UserIntegrationServiceImpl.class);

    @Autowired
    private UserDubboBusiness userDubboBusiness;

   
}
