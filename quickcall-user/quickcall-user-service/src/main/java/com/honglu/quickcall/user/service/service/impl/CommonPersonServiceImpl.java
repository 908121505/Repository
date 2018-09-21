package com.honglu.quickcall.user.service.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetHeardUrlRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetPwdRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserLoginRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserRegisterRequest;
import com.honglu.quickcall.user.service.service.CommonPersonService;

import cn.jiguang.commom.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by len.song on 2017-12-07.
 */
@Service
public class CommonPersonServiceImpl implements CommonPersonService {
 
   
    private final static Logger logger = LoggerFactory.getLogger(CommonPersonServiceImpl.class);

	@Override
	public CommonResponse regUserExist(IsPhoneExistsRequest params) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public CommonResponse login(UserLoginRequest params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse setpwd(SetPwdRequest params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse setHeardUrl(SetHeardUrlRequest params) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public CommonResponse register(UserRegisterRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    


    
    

   
	
	
	
	
	
	
	
	
	
	
	
	
	


}
