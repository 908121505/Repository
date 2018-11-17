package com.honglu.quickcall.account.service.bussService.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.exchange.request.CheckReceiveSwitchRequest;
import com.honglu.quickcall.account.facade.exchange.request.DaVListBySkillItemIdRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageDaVinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageSkillinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.OpenReceiveSwitchRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillInfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillUpdateRequest;
import com.honglu.quickcall.account.facade.vo.CustomerSkillInfoVO;
import com.honglu.quickcall.account.facade.vo.DaVinfoListVO;
import com.honglu.quickcall.account.facade.vo.DaVinfoVO;
import com.honglu.quickcall.account.facade.vo.FirstPageSkillIteminfoVO;
import com.honglu.quickcall.account.service.bussService.CommonService;
import com.honglu.quickcall.account.service.bussService.ISkillBussService;
import com.honglu.quickcall.account.service.service.IProductSkillService;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：技能管理中心
 * 
 * @Package: com.honglu.quickcall.account.web.service.impl
 * @author: chenliuguang
 * @date: 2018年9月22日 下午3:17:04
 */
@Service
public class SkillBussServiceImpl implements ISkillBussService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SkillBussServiceImpl.class);
	
	@Autowired
	private CommonService  commonService;
	@Autowired
	private  IProductSkillService  productSkillService;

	@Override
	public CommonResponse querySkillInfoPersonal(SkillInfoRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询技能信息参数异常");
		}
		Long  customerId =  request.getCustomerId();
//		String customerJson = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO+request.getCustomerId());
//		if(StringUtils.isEmpty(customerJson)){
//			return ResultUtils.result(BizCode.CustomerNotExist);
//		}
		//
		CustomerSkillInfoVO   resultVO =  productSkillService.querySkillInfoPersonal(customerId);
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCode(BizCode.Success);
		commonResponse.setMessage(BizCode.Success.desc());
		commonResponse.setData(resultVO);
		LOGGER.info("用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}
	
	

	@Override
	public CommonResponse updateSkillInfoPersonal(SkillUpdateRequest request) {
		if (request == null ) {
			throw new BizException(AccountBizReturnCode.paramError, "更改技能参数异常");
		}
		String customerJson = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO+request.getCustomerId());
		if(StringUtils.isEmpty(customerJson)){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		productSkillService.updateSkillInfoPersonal(request);
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCode(BizCode.Success);
		commonResponse.setMessage(BizCode.Success.desc());
		LOGGER.info("用户编号为：" + "" + "修改成功");
		return commonResponse;
	}
	

	@Override
	public CommonResponse getFirstPageDaVinfo(FirstPageDaVinfoRequest request) {
		if (request == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询首页大V列表参数异常");
		}
		//首先查询所有的技能信息
		List<DaVinfoListVO>   resultList = new ArrayList<>();

		//资源位信息
		DaVinfoListVO  resourceDaVinfoListVO =  productSkillService.getResourceDaVinfoList();
		resultList.add(resourceDaVinfoListVO);

		//飞标签位列表
		List<DaVinfoListVO>   tagList  =  productSkillService.getTagDaVinfoList();
		resultList.addAll(tagList);

		LOGGER.info("用户编号为：" + request.getCustomerId() + "查询成功");
		return ResultUtils.resultSuccess(resultList);
	}


	@Override
	public CommonResponse getFirstPageSkillItemInfo(FirstPageSkillinfoRequest request) {
		if (request == null ) {
			throw new BizException(AccountBizReturnCode.paramError, "查询技能列表参数异常");
		}
		List<FirstPageSkillIteminfoVO>   resultList = productSkillService.selectPartSkill();
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}



	@Override
	public CommonResponse getDaVListBySkillItemId(DaVListBySkillItemIdRequest request) {
		if (request == null ) {
			throw new BizException(AccountBizReturnCode.paramError, "查询技能大V参数异常");
		}
		Long  skillItemId = request.getSkillItemId();
		List<DaVinfoVO>    resultList = productSkillService.getDaVListBySkillId(skillItemId);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}



	@Override
	public CommonResponse checkReceiveSwitch(CheckReceiveSwitchRequest request) {
		if (request == null ) {
			throw new BizException(AccountBizReturnCode.paramError, "检查声优接单开关是否开启参数异常");
		}
		Long  customerId = request.getCustomerId();
		Integer     retType = productSkillService.checkReceiveSwitch(customerId);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(retType);
		LOGGER.info("用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}



	@Override
	public CommonResponse openReceiveSwitch(OpenReceiveSwitchRequest request) {
		if (request == null ) {
			throw new BizException(AccountBizReturnCode.paramError, "开启声优接单开关参数异常");
		}
		Long  customerId = request.getCustomerId();
		productSkillService.openReceiveSwitch(customerId);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData("");
		LOGGER.info("用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}
	
	
	
	











	


}
