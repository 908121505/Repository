package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.entity.Aliacount;
import com.honglu.quickcall.account.facade.exchange.request.BindAliaccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.RechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.WhthdrawRequest;
import com.honglu.quickcall.account.service.dao.AccountMapper;
import com.honglu.quickcall.account.service.dao.AliacountMapper;
import com.honglu.quickcall.account.service.service.AliPayService;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.HttpClientUtils;
import com.honglu.quickcall.common.core.util.UUIDUtils;

import net.sf.json.JSONObject;
@Service
public class AliPayServiceImpl implements AliPayService {
	
	@Autowired
	private AliacountMapper aliacountMapper;
	
	@Autowired
	private AccountMapper accountMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);
	private static String aliPayUrl=ResourceBundle.getBundle("thirdconfig").getString("ALI_UNIFIED_ORDER_URL");
	
	private static String aliTransUrl=ResourceBundle.getBundle("thirdconfig").getString("ALI_PAY_TRANS_FERTO_URL");
	@Override
	public CommonResponse recharge(RechargeRequest packet) {
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
		
		Account account=accountMapper.queryAccount(params.getUserId());
		if(account.getRemainderAmounts().compareTo(params.getAmount())==-1) {
			return ResultUtils.resultParamEmpty("输入金额大于提现金额");
		}
		Aliacount aliacount=aliacountMapper.selectByPrimaryKey(params.getUserId()+"");
		if(aliacount==null) {//如果未绑定支付宝，将不发放奖励
			return ResultUtils.resultParamEmpty("未绑定支付宝");
		}else {
			//组装支付宝单批转账的数据
			BigDecimal amount=params.getAmount();
			String appPlatform="2";//贷款管家
			String customerId=params.getUserId()+"";
			String outBizNo=UUIDUtils.getUUID();//唯一的流水id
			String payeeAccount=aliacount.getAccount();//支付宝账号
			String payeeRealName=aliacount.getRealname();//支付宝真实姓名
			HashMap<String, Object> paramMap=new HashMap<>();
			paramMap.put("amount", amount);
			paramMap.put("appPlatform", appPlatform);
			paramMap.put("customerId", customerId);
			paramMap.put("outBizNo", outBizNo);
			paramMap.put("payeeAccount", payeeAccount);
			paramMap.put("payeeRealName", payeeRealName);
			String json = JSON.toJSONString(paramMap,true);
			String res=HttpClientUtils.doPost(aliTransUrl, json);
			logger.info("支付宝返账 用户ID "+customerId+"返账 res:"+res);
			JSONObject  myJson = JSONObject.fromObject(res);
			if("1".equals(myJson.getString("respCode"))) {//成功
				
			}else {//失败
				
			}
		}
		
	
		return null;
	}

	@Override
	public CommonResponse bindAliaccount(BindAliaccountRequest params) {
		Aliacount acliacount=aliacountMapper.selectByPrimaryKey(params.getUserId());
		if(params.getEtype()!=null&&params.getEtype()==1) {
			return ResultUtils.resultSuccess(acliacount);
		}else {
		Aliacount acount=new Aliacount();
		acount.setAccount(params.getAccount());
		acount.setAccountId(params.getUserId());
		acount.setRealname(params.getRealname());
		if(acliacount==null) {//没有支付宝账号走insert
			aliacountMapper.insert(acount);
		}else {//走更新
			aliacountMapper.updateByPrimaryKey(acount);
		}
		return ResultUtils.resultSuccess();
		}
	}

}
