package com.honglu.quickcall.common.api.exchange;


import com.honglu.quickcall.common.api.code.BizCode;

public class ResultUtils {
	
	
	/**
	 * 成功返回
	 * @param obj
	 * 			接口返回数据
	 * @return
	 */
	public static CommonResponse resultSuccess(Object obj) {
		CommonResponse response = new CommonResponse();
		response.setCode(BizCode.Success);
		response.setData(obj);
		response.setMessage(BizCode.Success.desc());
		return response;
	}
	
	
	


}
