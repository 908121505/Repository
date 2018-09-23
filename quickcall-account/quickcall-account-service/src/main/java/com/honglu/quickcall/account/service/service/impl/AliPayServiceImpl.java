package com.honglu.quickcall.account.service.service.impl;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.exchange.request.RechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.WhthdrawRequest;
import com.honglu.quickcall.account.service.service.AliPayService;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.HttpClientUtils;
import com.honglu.quickcall.common.core.util.UUIDUtils;
@Service
public class AliPayServiceImpl implements AliPayService {
	private final static Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);
	private static String aliPayUrl=ResourceBundle.getBundle("thirdconfig").getString("ALI_UNIFIED_ORDER_URL");
	@Override
	public CommonResponse recharge(RechargeRequest packet) {
		 CommonResponse response =new CommonResponse();
		 String params="";
         String orderNo=UUIDUtils.getUUID();//订单
         String orderDesc="支付宝充值";
         String amount=packet.getAmount()+"";//交易金额
         String xnPayType="1";//支付类型 1:支付宝APP, 2 :微信APP ,3:支付宝H5支付,4：微信H5支付
        // String extData= "{\"phoneNum\":\"18217583747\"}";
         String extData="{\"accountId\":packet.getAccountId()}";
         String accountId=packet.getUserId();
         String createIp=packet.getRemoteIp();//请求ip
         params+="appName=1&orderId="+orderNo+"&orderDesc="+orderDesc;
         params+="&amount="+amount+"&xnPayType="+xnPayType+"&extData="+extData+"&createIp="+createIp+"&customerId="+accountId;
         String result=null;
         try{
        	 result =HttpClientUtils.doPostForm(aliPayUrl, params);
		 }catch(Exception e) {
			 logger.info("预支付接口异常········");
			 throw new BizException(BizCode.ParamError, "预支付接口异常");
		 }
         return ResultUtils.resultSuccess(result);
		
	}

	@Override
	public CommonResponse whthdraw(WhthdrawRequest params) {
		// TODO Auto-generated method stub
		return null;
	}

}
