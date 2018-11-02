package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：查询兴趣列表
 * @Package: com.honglu.quickcall.user.facade.exchange.req
 * @author: chenliuguang   
 * @date: 2018年10月9日 下午12:51:39 
 */
public class QueryInterestListRequest extends UserCenterRequest{
	
	private static final long serialVersionUID = -6464068097374877532L;

	@Override
	public String getBizCode() {
		return UserFunctionType.QUERY_INTEREST_LIST;
	}

}
