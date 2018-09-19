package com.honglu.quickcall.account.web.controller;


import com.honglu.quickcall.account.facade.exchange.request.*;
import com.honglu.quickcall.account.web.service.AccountCommonService;
import com.honglu.quickcall.account.web.service.AccountOrderService;
import com.honglu.quickcall.account.web.service.PaymentService;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.StringUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class AccountUserInfoController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AccountOrderService accountOrderService;

    @Autowired
    private AccountCommonService accountCommonService;



    /**
     * 充值记录
     * @param params
     * @return
     */
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getRecharge( GetRechargeRequest params) {
    		 WebResponseModel response = paymentService.execute(params);
        return response;
    }
    
    /**
     * 获取充值套餐
     * @param params
     * @return
     */
    @RequestMapping(value = "/token_package", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel tokenPackage( TokenPackageRequest params) {
    		 WebResponseModel response = paymentService.execute(params);
        return response;
    }
    
    /**
     * 提现信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/selectWithdrawByPid", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel selectWithdrawByPid( SelectWithDrawRequest params) {
    		 WebResponseModel response = paymentService.execute(params);
        return response;
    }
    
    /**
     * 申请提现
     * @param params
     * @return
     */
    @RequestMapping(value = "/applicationWithdraw", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel applicationWithdraw( applicationWithDrawRequest params) {
   	 WebResponseModel response=new WebResponseModel();
   	if(StringUtil.isNull(params.getCode())) {
   	 String redisLockKey=null;
   	 if(StringUtil.isNull(params.getTel())) {
   		 redisLockKey = RedisKeyConstants.USER_INFO_NX+params.getTel() ;//redis数据锁 
   	 }
   	String redisCode= JedisUtil.get(RedisKeyConstants.USER_VERIFYCODE + params.getTel());
   	 if(!StringUtil.isNull(redisCode)) {//验证码超时
   		 response.setCode(UserBizReturnCode.CodeOvertime.code());
            response.setMsg(UserBizReturnCode.CodeOvertime.desc());
            return response;
   	 }else {
   		 if(!redisCode.equals(params.getCode())){//验证码错误
   			 response.setCode(UserBizReturnCode.CodeError.code());
                response.setMsg(UserBizReturnCode.CodeError.desc()); 
                return response;
   		 }
   	 }
   	 //setnx =0  代表已存在，1未存在。 超时时间为5s
        long redisResult = JedisUtil.setnx(redisLockKey, params.getTel(), 5) ;
        if(redisResult == 0){
            response.setCode(UserBizReturnCode.EruptClick.code());
            response.setMsg(UserBizReturnCode.EruptClick.desc());
            return  response;
        }
   	}
     	response = paymentService.execute(params);
       return response; 
   
    }
    
    /**
     * 获得金币与人民币的兑换比例
     * @param params
     * @return
     */
    @RequestMapping(value = "/tokenasrmb", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel tokenAsRmb( TokenAsRmbRequest params) {
    		 WebResponseModel response = paymentService.execute(params);
        return response;
    }


    /**
     * 查询用户账户余额
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryAccount", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryAccount(@RequestBody QueryAccountRequest params) {
        WebResponseModel response = accountOrderService.execute(params);
        return response;
    }
    
    /**
     * 确认是否为首冲
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryFirstRecharge", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel queryFirstRecharge(QueryFirstRechargeRequest params) {
        WebResponseModel response = paymentService.execute(params);
        return response;
    }

    /**
     * 获取充值金额列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getRechargeAmount", method = RequestMethod.POST)
    @ResponseBody
    public WebResponseModel getRechargeAmount(GetRechargeAmountRequest request) {
        WebResponseModel response = accountCommonService.execute(request);
        return response;
    }
}
