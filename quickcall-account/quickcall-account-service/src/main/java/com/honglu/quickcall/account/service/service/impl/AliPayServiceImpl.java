package com.honglu.quickcall.account.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.exchange.request.RechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.WhthdrawRequest;
import com.honglu.quickcall.account.service.service.AliPayService;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
@Service
public class AliPayServiceImpl implements AliPayService {
	private final static Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);

	@Override
	public CommonResponse recharge(RechargeRequest params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse whthdraw(WhthdrawRequest params) {
		// TODO Auto-generated method stub
		return null;
	}

}
