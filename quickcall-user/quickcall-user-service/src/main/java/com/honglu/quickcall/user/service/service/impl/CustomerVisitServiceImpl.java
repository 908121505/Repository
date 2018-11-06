package com.honglu.quickcall.user.service.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.user.facade.exchange.request.RecentVisitRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetVisitReadRequest;
import com.honglu.quickcall.user.service.dao.CustomerVisitMapper;
import com.honglu.quickcall.user.service.service.CustomerVisitService;

@Service
public class CustomerVisitServiceImpl implements CustomerVisitService {
	
	@Autowired
	private CustomerVisitMapper customerVisitMapper;

	@Override
	public CommonResponse queryRecentVisitList(RecentVisitRequest params) {
		//现在时间
		Date now = new Date(params.getStartTime());
		//获得30天前的时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE,-30);
		Date before = cal.getTime();
		
		Long customerId = params.getCustomerId();
		Integer start = params.getPageIndex()*params.getPageSize();
		Integer size = params.getPageSize();
		List<Map<String,Object>> ls = customerVisitMapper.queryCustomerVisitList(before, now, customerId, start, size);
		
		return ResultUtils.resultSuccess(ls);
	}
	
	@Override
	public CommonResponse setVisitRead(SetVisitReadRequest params) {
		Date now = new Date(params.getStartTime());
		Long customerId = params.getCustomerId();
		customerVisitMapper.updateReadStatus(now, customerId);
		return ResultUtils.resultSuccess();
	}

}
