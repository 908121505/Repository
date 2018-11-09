package com.honglu.quickcall.account.service.bussService.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.exchange.request.ChannelSwitchRequest;
import com.honglu.quickcall.account.service.bussService.ChannelSwitchService;
import com.honglu.quickcall.account.service.dao.ChannelSwitchMapper;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;

/**
 * 渠道开关实现类
 * @author zhaozheyi
 *
 */
@Service
public class ChannelSwitchServiceImpl implements ChannelSwitchService {
	@Autowired
	private ChannelSwitchMapper channelSwitchMapper;

	@Override
	public CommonResponse getChannelSwitchStatus(ChannelSwitchRequest request) {
		String channel = request.getChannel();
		String version = request.getVersion();
		Integer status = channelSwitchMapper.getChannelSwitchStatusByCV(channel, version);
		Map<String,Object> map = new HashMap<>();
		if(status == null){
			return ResultUtils.resultDataNotExist("没有该渠道和版本");
		}
		String str = "";
		if(status == 1){
			str = "678";
		}else{
			str = "123"; 
		}
		map.put("talk_version", str);
		return ResultUtils.resultSuccess(map);
	}

}
