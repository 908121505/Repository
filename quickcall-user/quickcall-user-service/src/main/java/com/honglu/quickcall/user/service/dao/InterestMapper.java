package com.honglu.quickcall.user.service.dao;

import java.util.List;

import com.honglu.quickcall.user.facade.entity.Interest;

public interface InterestMapper {
	//根据customerId查找兴趣
	List<String> selectInterestByCustomerId (Long customerId);
}
