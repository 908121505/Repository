package com.honglu.quickcall.common.third.AliyunSms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.util.HttpClientUtils;

import net.sf.json.JSONObject;

public class SendSmsUtil {
	// 发送验证码 模板
	public static String codeTemplateCode = "sms_10_code_61";

	// 订单通知模板
	public static String orderTemplateCode = "sms_10_code_62";

	public static String singName = "轻音互动";

	public static String send_msg_url = ResourceBundle.getBundle("thirdconfig").getString("send_msg_url");

	/**
	 * 发送短信
	 * 
	 * @param uuId
	 * @param templateType
	 *            1 发送验证码 2发送订单通知
	 * @param codeParams
	 *            验证码的 传code 订单通知的 传订单名字
	 * @return
	 */
	public static String sendSms(String uuId, String tel, Integer templateType, String codeParams) {
		try {
			Date date = new Date();
			SimpleDateFormat month = new SimpleDateFormat("MM");

			SimpleDateFormat day = new SimpleDateFormat("dd");

			HashMap<String, Object> paramMap = new HashMap<>();
			HashMap<String, Object> paramMap1 = new HashMap<>();

			paramMap.put("requestId", uuId);
			if (templateType == 1) {
				paramMap.put("templateId", codeTemplateCode);
				paramMap1.put("code", codeParams);

			} else if (templateType == 2) {
				paramMap.put("templateId", orderTemplateCode);
				paramMap1.put("month", month.format(date));
				paramMap1.put("day", day.format(date));
				paramMap1.put("order", codeParams);

			}
			paramMap.put("appNum", 10);// 4:惠花花，5:秒购 10：轻音
			paramMap.put("signName", singName);
			paramMap.put("phone", tel);
			paramMap.put("templateType", 1);// 1:即时 2:系统 3:市场
			paramMap.put("codeParams", paramMap1);
			String json = JSON.toJSONString(paramMap, true);
			String res = HttpClientUtils.doPost(send_msg_url, json);
			JSONObject myJson = JSONObject.fromObject(res);

			return myJson.getString("requestFaceDesc");
		} catch (Exception e) {
			return "发送失败";
		}
	}

	public static void main(String[] args) {
		sendSms("11244111411", "18217583747", 2, "小花");

	}

}
