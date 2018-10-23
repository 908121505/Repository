package com.honglu.quickcall.account.service.bussService.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.account.facade.exchange.request.CreateUserAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.InAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.OutAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryAccountRequest;
import com.honglu.quickcall.account.service.bussService.AccountService;
import com.honglu.quickcall.account.service.bussService.UserAccountService;
import com.honglu.quickcall.account.service.dao.AccountMapper;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.core.util.UUIDUtils;

/**
 * Created by len.song on 2017-12-16.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
	private final static Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountService accountService;

	@Override
	@Transactional
	public CommonResponse createAccount(CreateUserAccountRequest request) {
		if (request == null || request.getUserId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "创建账户参数异常");
		}
		logger.info("用户编号为：" + request.getUserId() + "的用户开始创建账户...");
		Account userAccount = new Account(UUIDUtils.getId(), request.getUserId());

		accountMapper.createUserAccount(userAccount);
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCode(BizCode.Success);
		commonResponse.setMessage(BizCode.Success.desc());
		logger.info("用户编号为：" + request.getUserId() + "的账户创建成功...");
		return commonResponse;
	}

	@Override
	public CommonResponse queryAccount(QueryAccountRequest request) {
		// TODO Auto-generated method stub
		CommonResponse response = new CommonResponse();
		Account account = accountMapper.queryAccount(request.getCustomerId());
		response.setCode(BizCode.Success);
		response.setMessage(BizCode.Success.desc());
		response.setData(account);
		return response;
	}

	@Override
	public CommonResponse inAccount(InAccountRequest request) {
		Account account = accountMapper.queryAccount(request.getUserId());
		if (account == null) {
			return ResultUtils.resultParamEmpty("账户不存在");
		}
		accountMapper.inAccount(request.getUserId(), request.getAmount(), request.getTransferType());

		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse outAccount(OutAccountRequest request) {

		Account account = accountMapper.queryAccount(request.getUserId());
		if (account == null) {
			return ResultUtils.resultParamEmpty("账户不存在");
		}
		if (request.getTransferType() == TransferTypeEnum.REMAINDER.getType()
				&& account.getRemainderAmounts().compareTo(request.getAmount()) == -1) {
			return ResultUtils.resultParamEmpty("金额不足");
		}
		if (request.getTransferType() == TransferTypeEnum.RECHARGE.getType()
				&& account.getRechargeAmounts().compareTo(request.getAmount()) == -1) {
			return ResultUtils.resultParamEmpty("金额不足");
		}
		accountMapper.outAccount(request.getUserId(), request.getAmount(), request.getTransferType());
		return ResultUtils.resultSuccess();
	}

}
