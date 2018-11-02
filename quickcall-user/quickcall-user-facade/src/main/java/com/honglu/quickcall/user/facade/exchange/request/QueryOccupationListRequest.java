package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：查询职业列表入参
 * @Package: com.honglu.quickcall.user.facade.exchange.req
 * @author: chenliuguang   
 * @date: 2018年10月9日 下午12:55:20 
 */
public class QueryOccupationListRequest extends UserCenterRequest{
	
	private static final long serialVersionUID = -8027264006662652964L;

	@Override
	public String getBizCode() {
		return UserFunctionType.QUERY_OCCUPATION_LIST;
	}

}
