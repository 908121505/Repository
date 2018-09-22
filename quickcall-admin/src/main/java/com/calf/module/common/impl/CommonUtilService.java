package com.calf.module.common.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service("commonService")
public class CommonUtilService {

	
	
	/**
	 * 获取当前用户名称
	 * @return
	 */
	public String  getCurrUser(){
		Subject currentUser = SecurityUtils.getSubject();
		return  currentUser.getPrincipal().toString();
	}
}
