package com.honglu.quickcall.user.service.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.AliyunSms.enums.SmsTemplateEnum;
import com.honglu.quickcall.common.third.AliyunSms.utils.AliyunSmsCodeUtil;
import com.honglu.quickcall.common.third.AliyunSms.utils.MandaoSmsCodeUtil;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.exchange.request.GetSmsCodeRequest;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetHeardUrlRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetPwdRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserLoginRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserRegisterRequest;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.integration.AccountDubboIntegrationService;
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
    
    @Autowired
    private AccountDubboIntegrationService accountDubboIntegrationService;
    
    private static String resendexpire= ResourceBundle.getBundle("thirdconfig").getString("resend.expire");
    private static String resendexpirehour= ResourceBundle.getBundle("thirdconfig").getString("resend.expire.hour");
    private static String onehourfreq= ResourceBundle.getBundle("thirdconfig").getString("one.hour.freq");
    private static String onedayfreq= ResourceBundle.getBundle("thirdconfig").getString("one.day.freq");
    private static String smscodeexpire= ResourceBundle.getBundle("thirdconfig").getString("smscode.expire");
    
    
    
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
		Customer customer=saveUser(request);
		response.setCode(BizCode.Success);
		response.setData(customer);
	    response.setMessage(BizCode.Success.desc());
		return response;
	}
    
    
    


    
    

     /**
      * 保存用户，并生成融云token
      * @param request
      * @return
      */
	private Customer saveUser(UserRegisterRequest request) {
		Customer customer=new Customer();
		customer.setCustomerId(UUIDUtils.getId());
		customer.setCreateTime(new Date());
		customer.setMicroblogOpenId(request.getMicroblogOpenId());
		customer.setQqOpenId(request.getQqOpenId());
		customer.setWechatOpenId(request.getWechatOpenId());
		customer.setPhone(request.getTel());
		customer.setNickName(request.getNickName());
		customer.setHeadPortraitUrl(request.getHeardUrl());
		if(StringUtils.isNotBlank(request.getHeardUrl())) {
			defaultImg=request.getHeardUrl();
		}
		if(StringUtils.isNotBlank(request.getNickName())) {
		String rongyunToken = RongYunUtil.getToken(String.valueOf(customer.getCustomerId()), customer.getNickName(), defaultImg);
         if(rongyunToken==null||"".equals(rongyunToken)) {
        	 logger.error("用户获取融云token失败。用户ID为：" + customer.getCustomerId());
         }
         customer.setTokenCode(rongyunToken);
		}
         int row= customerMapper.insertSelective(customer);
         if(row<=0) {
        	 throw new BizException(UserBizReturnCode.exceedError,"用户未注冊");	
         }
         accountDubboIntegrationService.createAccount(customer.getCustomerId());
         
		return customer;
	}



	@Override
	public CommonResponse getSmsCode(GetSmsCodeRequest params) {
		// TODO Auto-generated method stub
		Customer customer = null;
		Customer param=new Customer();
		if(StringUtils.isNotBlank(params.getTel())) {
			param.setPhone(params.getTel());
		}
		customer=customerMapper.login(param);
		if(customer==null) {
			if(!"2".equals(params.getCodeType())){
				throw new BizException(UserBizReturnCode.exceedError,"用户未注冊");	
			}
		}else {
			if("2".equals(params.getCodeType())){
			throw new BizException(UserBizReturnCode.exceedError,"用户已注冊");	
			}
		}
		//四位随机数
		int random = (int) ((Math.random() * 9 + 1) * 1000);
		String randomCode = random + "";
		
		
		return sendSms(params.getTel(),randomCode,params.getCodeType());
	}
	
	public static CommonResponse sendSms(String phoneNum, String code,String codeType) {
		CommonResponse response= new CommonResponse();
		// 查一分钟
		if (JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_M + phoneNum) == null) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_M + phoneNum, "1", Integer.parseInt(resendexpire));
		} else {
			 throw new BizException(UserBizReturnCode.exceedError,"请勿重复发送验证码");	
		}

		// 查一小时
		if (StringUtils.isNotBlank(JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum))) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum, "1", Integer.parseInt(resendexpirehour));
		} else {
			String count = JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum);
			if (Integer.parseInt(count) >= Integer.parseInt(onehourfreq)) {
				 throw new BizException(UserBizReturnCode.exceedError,"验证码每小时只能获取五次");	
			}
			String total = String.valueOf(Integer.parseInt(count) + 1);
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum, total, JedisUtil.ddlTimes(RedisKeyConstants.USER_VERIFYCODE_H + phoneNum));
		}

		// 查一天
		if (StringUtils.isNotBlank(JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum))) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum, "1", DateUtils.getZeroTimestamp());
		} else {
			String count = JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum);
			if (Integer.parseInt(count) >= Integer.parseInt(onedayfreq)) {
				 throw new BizException(UserBizReturnCode.exceedError,"验证码日发送次数已达到上限");	
			}
			String total = String.valueOf(Integer.parseInt(count) + 1);
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum, total, JedisUtil.ddlTimes(RedisKeyConstants.USER_VERIFYCODE_D + phoneNum));
		}
		
		SendSmsResponse codeResponse;
		try {
			codeResponse = AliyunSmsCodeUtil.sendSms(phoneNum, code);


		if (codeResponse!=null&&"OK".equals(codeResponse.getCode())) {
			JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code, Integer.parseInt(smscodeexpire));
			logger.info("手机号:" + phoneNum + " 手机验证码:" + code);
			logger.info("将验证码存入redis中的key值为:{},失效时间为:{}",(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType),Integer.parseInt(smscodeexpire));
			response.setCode(BizCode.Success);
			response.setData("success");
			response.setMessage(BizCode.Success.desc());
			return response;
		} else {
			//阿里云短信异常,用 "漫道"短信通道 发送
			String mdResult = MandaoSmsCodeUtil.mdSmsSendSimple(phoneNum,
					MessageFormat.format(SmsTemplateEnum.login_auth_code_template.getContent(),code));
			if(mdResult.startsWith("-")||mdResult.equals("")){
				throw new BizException(UserBizReturnCode.DBError,"验证码发送失败，请检查手机号是否正常");
			}else{
				logger.info("漫道发送验证码手机号:" + phoneNum + " 手机验证码:" + code);
				JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code, Integer.parseInt(smscodeexpire));
				response.setCode(BizCode.Success);
				response.setData("success");
				response.setMessage(BizCode.Success.desc());
				return response;
			}
		}
		} catch (ClientException e) {
			//阿里云短信异常,用 "漫道"短信通道 发送
			String mdResult = MandaoSmsCodeUtil.mdSmsSendSimple(phoneNum,
					MessageFormat.format(SmsTemplateEnum.login_auth_code_template.getContent(),code));
			if(mdResult.startsWith("-")||mdResult.equals("")){
				throw new BizException(UserBizReturnCode.DBError,"验证码发送失败，请检查手机号是否正常");
			}else{
				logger.info("漫道发送验证码手机号:" + phoneNum + " 手机验证码:" + code);
				JedisUtil.set(RedisKeyConstants.USER_VERIFYCODE + phoneNum + codeType, code, Integer.parseInt(smscodeexpire));
				response.setCode(BizCode.Success);
				response.setData("success");
				response.setMessage(BizCode.Success.desc());
				return response;
			}
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
