package com.honglu.quickcall.activity.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.activity.facade.business.ActivityDubboBusiness;
import com.honglu.quickcall.activity.facade.code.ActivityBizReturnCode;
import com.honglu.quickcall.activity.web.service.ActivityCenterService;
import com.honglu.quickcall.common.api.code.MyServiceCode;
import com.honglu.quickcall.common.api.code.SourceCode;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

/**
 * Created by len.song on 2017-12-08.
 */
@Service
public class ActivityCenterServiceImpl implements ActivityCenterService {
	private static final Logger logger = LoggerFactory.getLogger(ActivityCenterServiceImpl.class);

	@Autowired
	private ActivityDubboBusiness activityDubboBusiness;

	@Override
	public WebResponseModel execute(AbstractRequest request) {
		request.setService(MyServiceCode.ACTIVITY);
		request.setSource(SourceCode.OpenApi);
		logger.info("功能编码为" + request.getBizCode() + "发送请求：{}", request);
		WebResponseModel response = new WebResponseModel();
		try {
			CommonResponse $response = activityDubboBusiness.excute(request);
			logger.info("功能编码为" + request.getBizCode() + "接收响应：{}", $response);
			if (!$response.isSuccess()) {
				throw new RemoteException($response.getCode(), $response.getMessage());
			}
			response.setCode($response.getCode().code());
			response.setMsg($response.getMessage());
			// response.setData(DES3Utils.encryptMode(JSON.toJSONString(result),
			// ConstantUtils.THREEDES_KEY));
			response.setData(JSON.toJSONString($response.getData()));
		} catch (RemoteException e) {
			logger.error("功能编码为" + request.getBizCode() + "的远程调用异常" + e.getMessage(), e);
			response.setCode(e.getCode().code());
			response.setMsg(e.getMessage());
			response.setData("");
		} catch (Exception e) {
			logger.error("功能编码为" + request.getBizCode() + "接口未知异常" + e.getMessage(), e);
			response.setCode(ActivityBizReturnCode.Unknown.code());
			response.setMsg(ActivityBizReturnCode.Unknown.desc());
		}
		logger.info("返回结果{}", response);
		return response;
	}

}
