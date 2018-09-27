package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.entity.Aliacount;
import com.honglu.quickcall.account.facade.entity.Recharge;
import com.honglu.quickcall.account.facade.exchange.request.AlipayNotifyRequest;
import com.honglu.quickcall.account.facade.exchange.request.BindAliaccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.RechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.WhthdrawRequest;
import com.honglu.quickcall.account.service.dao.AccountMapper;
import com.honglu.quickcall.account.service.dao.AliacountMapper;
import com.honglu.quickcall.account.service.dao.RechargeMapper;
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

	@Autowired
	private RechargeMapper rechargeMapper;

	private final static Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);
	private static String aliPayUrl = ResourceBundle.getBundle("thirdconfig").getString("ALI_UNIFIED_ORDER_URL");

	private static String aliTransUrl = ResourceBundle.getBundle("thirdconfig").getString("ALI_PAY_TRANS_FERTO_URL");

	@Override
	public CommonResponse recharge(RechargeRequest packet) {
		String params = "";
		String orderNo = UUIDUtils.getUUID();// 订单
		String orderDesc = "支付宝充值";
		String amount = packet.getAmount() + "";// 交易金额
		String xnPayType = "1";// 支付类型 1:支付宝APP, 2 :微信APP ,3:支付宝H5支付,4：微信H5支付
		// String extData= "{\"phoneNum\":\"18217583747\"}";
		String extData = "{\"accountId\":packet.getUserId()}";
		String accountId = packet.getUserId() + "";
		String createIp = packet.getRemoteIp();// 请求ip
		params += "appName=3&orderId=" + orderNo + "&orderDesc=" + orderDesc;
		params += "&amount=" + amount + "&xnPayType=" + xnPayType + "&extData=" + extData + "&createIp=" + createIp
				+ "&customerId=" + accountId;
		String result = null;
		try {
			result = HttpClientUtils.doPostForm(aliPayUrl, params);
		} catch (Exception e) {
			logger.info("预支付接口异常········");
			throw new BizException(BizCode.ParamError, "预支付接口异常");
		}
		if (result != null) {
			Map maps = JSON.parseObject(result);
			if (maps.get("code").equals("200")) {
				result = JSON.parseObject(maps.get("data").toString()).get("payReturnStr").toString();
			}
		}
		// 插入充值信息
		Recharge recharge = new Recharge();
		recharge.setCustomerId(packet.getUserId());
		recharge.setCreateDate(new Date());
		recharge.setAmount(packet.getAmount());
		recharge.setType(1);// 1充值 2提现
		recharge.setOrdersn(orderNo);
		recharge.setState(1);// 状态。1-申请支付，2-支付成功 3支付失败
		recharge.setRechargeType(1);// 充值类型。1为支付宝，2为微信
		rechargeMapper.insertSelective(recharge);
		return ResultUtils.resultSuccess(result);

	}

	@Override
	public CommonResponse whthdraw(WhthdrawRequest params) {

		Account account = accountMapper.queryAccount(params.getUserId());
		String errorMsg = null;
		if (account.getRemainderAmounts().compareTo(params.getAmount()) == -1) {
			return ResultUtils.resultParamEmpty("输入金额大于提现金额");
		}
		Aliacount aliacount = aliacountMapper.selectByPrimaryKey(params.getUserId() + "");
		if (aliacount == null) {// 如果未绑定支付宝，将不发放奖励
			return ResultUtils.resultParamEmpty("未绑定支付宝");
		} else {
			// 组装支付宝单批转账的数据
			BigDecimal amount = params.getAmount();
			String appPlatform = "2";// 贷款管家
			String customerId = params.getUserId() + "";
			String outBizNo = UUIDUtils.getUUID();// 唯一的流水id
			String payeeAccount = aliacount.getAccount();// 支付宝账号
			String payeeRealName = aliacount.getRealname();// 支付宝真实姓名
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("amount", amount);
			paramMap.put("appPlatform", appPlatform);
			paramMap.put("customerId", customerId);
			paramMap.put("outBizNo", outBizNo);
			paramMap.put("payeeAccount", payeeAccount);
			paramMap.put("payeeRealName", payeeRealName);
			String json = JSON.toJSONString(paramMap, true);
			String res = HttpClientUtils.doPost(aliTransUrl, json);
			logger.info("支付宝返账 用户ID " + customerId + "返账 res:" + res);
			JSONObject myJson = JSONObject.fromObject(res);
			Recharge recharge = new Recharge();
			recharge.setCustomerId(params.getUserId());
			recharge.setCreateDate(new Date());
			recharge.setAmount(params.getAmount());
			recharge.setType(2);// 1充值 2提现
			recharge.setOrdersn(outBizNo);
			recharge.setRechargeType(1);// 充值类型。1为支付宝，2为微信
			if ("1".equals(myJson.getString("respCode"))) {// 成功
				recharge.setState(2);// 状态。1-申请支付，2-支付成功 3支付失败
				accountMapper.outAccount(params.getUserId(), params.getAmount());
			} else {// 失败
				recharge.setState(3);// 状态。1-申请支付，2-支付成功 3支付失败
				errorMsg = myJson.getString("respMsg");
				throw new BizException(BizCode.ParamError, errorMsg);
			}
			// 插入提现信息
			rechargeMapper.insertSelective(recharge);
		}

		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse bindAliaccount(BindAliaccountRequest params) {
		Aliacount acliacount = aliacountMapper.selectByPrimaryKey(params.getUserId());
		if (params.getEtype() != null && params.getEtype() == 1) {
			return ResultUtils.resultSuccess(acliacount);
		} else {
			Aliacount acount = new Aliacount();
			acount.setAccount(params.getAccount());
			acount.setAccountId(params.getUserId());
			acount.setRealname(params.getRealname());
			if (acliacount == null) {// 没有支付宝账号走insert
				aliacountMapper.insert(acount);
			} else {// 走更新
				aliacountMapper.updateByPrimaryKey(acount);
			}
			return ResultUtils.resultSuccess();
		}
	}

	@Override
	public CommonResponse alipayNotify(AlipayNotifyRequest params) {
		// TODO Auto-generated method stub
		logger.info("支付回调参数===========" + JSON.toJSONString(params));

		Recharge recharge = new Recharge();
		recharge.setCustomerId(params.getAccountId());
		recharge.setFinishDate(new Date());
		recharge.setOrdersn(params.getOrderNo());
		if (params.getPayState() == 1) {
			recharge.setState(2);// 状态。1-申请支付，2-支付成功 3支付失败
			// 入账
			accountMapper.inAccount(params.getAccountId(), params.getAmount());
		} else if (params.getPayState() == 0) {
			recharge.setState(3);// 状态。1-申请支付，2-支付成功 3支付失败
		}
		rechargeMapper.updateByOrderNo(recharge);
		return ResultUtils.resultSuccess();
	}

}
