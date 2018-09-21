package com.honglu.quickcall.user.service.service.impl;

import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetHeardUrlRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetPwdRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserLoginRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserRegisterRequest;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.service.CommonPersonService;


/**
 * Created by len.song on 2017-12-07.
 */
@Service
public class CommonPersonServiceImpl implements CommonPersonService {
 
   
    private final static Logger logger = LoggerFactory.getLogger(CommonPersonServiceImpl.class);
    
    //默认图片
    private static String defaultImg =   ResourceBundle.getBundle("thirdconfig").getString("defaultImg");
    @Autowired
    private CustomerMapper customerMapper;
    
	@Override
	public CommonResponse regUserExist(IsPhoneExistsRequest params) {
		CommonResponse response=new CommonResponse();
		Customer customer = null;
		Customer param=new Customer();
		if(StringUtils.isNotBlank(params.getTel())) {
			param.setPhone(params.getTel());
		}
		else if(StringUtils.isNotBlank(params.getMicroblogOpenId())) {
			param.setMicroblogOpenId(params.getMicroblogOpenId());
		}
		else if(StringUtils.isNotBlank(params.getQqOpenId())) {
			param.setQqOpenId(params.getQqOpenId());
			
		}else if(StringUtils.isNotBlank(params.getWechatOpenId())) {
			param.setWechatOpenId(params.getWechatOpenId());
		}
		customer=customerMapper.login(param);
		if(customer!=null) {
			response.setData(1);
		}else {
			response.setData(0);
		}
	     response.setCode(BizCode.Success);
	     response.setMessage(BizCode.Success.desc());
		return response;
	}

	

	@Override
	public CommonResponse login(UserLoginRequest params) {
		CommonResponse response=new CommonResponse();
		Customer customer = null;
		Customer param=new Customer();
		//手机号登录 验证码登录
		if(StringUtils.isNotBlank(params.getTel())) {
			param.setPhone(params.getTel());
		}//手机号 密码登录
		if(StringUtils.isNotBlank(params.getPassWord())) {
			param.setCustPassword(params.getPassWord());
		}//微博登录
		else if(StringUtils.isNotBlank(params.getMicroblogOpenId())) {
			param.setMicroblogOpenId(params.getMicroblogOpenId());
		}//QQ登录
		else if(StringUtils.isNotBlank(params.getQqOpenId())) {
			param.setQqOpenId(params.getQqOpenId());
			
		}//微信登录
		else if(StringUtils.isNotBlank(params.getWechatOpenId())) {
			param.setWechatOpenId(params.getWechatOpenId());
		}
		customer=customerMapper.login(param);
		if(customer==null) {
			logger.info("用户不存在");
			 throw new BizException(BizCode.ParamError, "用戶不存在");	
		}
		
		//修改登录时间  补 融云token
		Customer login = new Customer();
        if (StringUtils.isNotBlank(customer.getTokenCode())) {
            String rongyunToken = RongYunUtil.getToken(String.valueOf(customer.getCustomerId()), customer.getNickName(), defaultImg);
            if(rongyunToken==null||"".equals(rongyunToken)) {
           	 logger.error("用户获取融云token失败。用户ID为：" + customer.getCustomerId());
            }
            login.setCustomerId(customer.getCustomerId());
            login.setTokenCode(rongyunToken);
            login.setModifyTime(new Date());
        }
		customerMapper.updateByPrimaryKeySelective(login);
		
		   response.setCode(BizCode.Success);
           response.setData(customer);
           response.setMessage(BizCode.Success.desc());
		
		return response;
	}

	@Override
	public CommonResponse setpwd(SetPwdRequest params) {
		CommonResponse response=new CommonResponse();
		int row=customerMapper.setPwd(params.getTel(), params.getPassWord());
		if(row<=0) {
			 throw new BizException(BizCode.ParamError, "设置密码失败");	
		}
		 response.setCode(BizCode.Success);
         response.setMessage(BizCode.Success.desc());
		return response;
	}

	@Override
	public CommonResponse setHeardUrl(SetHeardUrlRequest params) {
		// TODO Auto-generated method stub
		CommonResponse response=new CommonResponse();
		int row=customerMapper.setHeardUrl(params.getTel(), params.getHeadPortraitUrl(),params.getNickName());
		if(row<=0) {
			 throw new BizException(BizCode.ParamError, "设置密码失败");	
		}
		 response.setCode(BizCode.Success);
        response.setMessage(BizCode.Success.desc());
		return response;
	}



	@Override
	public CommonResponse register(UserRegisterRequest request) {
		// TODO Auto-generated method stub
		CommonResponse response=new CommonResponse();
		
		
		
		
		
		return null;
	}
    
    
    


    
    

     
	private Customer saveUser(UserRegisterRequest request) {
		Customer customer=new Customer();
		customer.set
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	


}
