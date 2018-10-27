package com.honglu.quickcall.user.service.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.user.facade.entity.AppVersionManage;
import com.honglu.quickcall.user.facade.exchange.request.AppVersionManageRequest;
import com.honglu.quickcall.user.facade.exchange.response.AppVersionManageResponse;
import com.honglu.quickcall.user.service.dao.AppVersionManageMapper;
import com.honglu.quickcall.user.service.service.AppVersionManageService;

@Service
public class AppVersionManageServiceImpl implements AppVersionManageService {

	private static Logger logger = LoggerFactory.getLogger(AppVersionManageServiceImpl.class);

	@Autowired
	private AppVersionManageMapper appVersionManageMapper;

	@Override
	public CommonResponse findAppVersionInfo(AppVersionManageRequest request) {
		// TODO Auto-generated method stub
		AppVersionManageResponse outPacket = new AppVersionManageResponse();

		List<AppVersionManage> list = appVersionManageMapper.findAppVersionInfo(request.getAppType(), 1,
				String.valueOf(request.getVersionType()));
		if (!list.isEmpty()) {
			outPacket.setAppType(list.get(0).getAppType());
			outPacket.setChangeDesc(list.get(0).getChangeDesc());
			outPacket.setChangeLog(list.get(0).getChangeLog());
			outPacket.setDownloadUrl(list.get(0).getDownloadUrl());
			outPacket.setForcedUpdate(Integer.parseInt(list.get(0).getChangeProperties()));
			outPacket.setPopup(list.get(0).getPopup());
			outPacket.setState(String.valueOf(list.get(0).getState()));
			outPacket.setTag(list.get(0).getTag());
			outPacket.setVersionNumber(list.get(0).getVersionNumber());
			outPacket.setCode(list.get(0).getRemark());
			outPacket.setMd5(list.get(0).getEncryption());
			ResultUtils.resultSuccess(outPacket);
		}
		return ResultUtils.resultSuccess("已经是最新版本");
	}

}
