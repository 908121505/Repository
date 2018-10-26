package com.honglu.quickcall.common.api.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class ResponseUtils {
	/**
	 * 获取一个返回成功的数据包
	 *
	 * @return
	 */
	public static HashMap<String, Object> getSuccessData() {
		HashMap<String, Object> map = new HashMap<String, Object>(); 
		map.put(ConstantUtils.RETURN_CODE, ConstantUtils.RET_OK_CODE);
		map.put(ConstantUtils.RETURN_MSG, ConstantUtils.RET_OK_MSG);
		map.put(ConstantUtils.RET_DATA, null);
		return map;
	}

	/**
	 * 获取一个返回失败的数据包
	 *
	 * @return
	 */
	public static HashMap<String, Object> getFailedData(String msg) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.contains(msg, "令牌失效") || StringUtils.contains(msg, "token")) {
			map.put(ConstantUtils.RETURN_CODE, ConstantUtils.TOKEN_FAILURE_CODE);
		} else {
			map.put(ConstantUtils.RETURN_CODE, ConstantUtils.RET_FAILURE_CODE);
		}
		map.put(ConstantUtils.RETURN_MSG, msg == null ? ConstantUtils.RET_FAILURE_MSG : msg);
		map.put(ConstantUtils.RET_DATA, null);
		return map;
	}

	/**
	 * 获取一个返回成功的数据包
	 * 
	 * @return
	 */
	public static HashMap<String, Object> getSuccessData(String channelKey) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(ConstantUtils.RETURN_CODE, ConstantUtils.RET_OK_CODE);
		map.put(ConstantUtils.RETURN_MSG, ConstantUtils.RET_OK_MSG);
		map.put(ConstantUtils.RET_DATA, null);
		return map;
	}

	/**
	 * 获取一个返回失败的数据包
	 * 
	 * @return
	 */
	public static HashMap<String, Object> getFailedData(String returnCode, String message) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(ConstantUtils.RETURN_CODE, returnCode);
		map.put(ConstantUtils.RETURN_MSG, message);
		map.put(ConstantUtils.RET_DATA, "");
		return map;
	}

	/**
	 * 获取一个返回成功的数据包
	 * @param msg
	 * 			提示信息
	 * @param data
	 * 			返回数据
	 * @return
	 */
	public static HashMap<String, Object> getSuccessData(String msg ,Object data) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(ConstantUtils.RETURN_CODE, ConstantUtils.RET_OK_CODE);
		map.put(ConstantUtils.RETURN_MSG, msg == null ? ConstantUtils.RET_OK_MSG : msg);
		map.put(ConstantUtils.RET_DATA, data == null ? null : data);
		return map;
	}
}
