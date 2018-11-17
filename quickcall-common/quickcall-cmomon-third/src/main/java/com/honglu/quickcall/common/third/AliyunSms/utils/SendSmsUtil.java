package com.honglu.quickcall.common.third.AliyunSms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.HttpClientUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;

import net.sf.json.JSONObject;

public class SendSmsUtil {
	// 发送验证码 模板
	public static String codeTemplateCode = "sms_10_code_61";

	// 订单通知模板
	public static String orderTemplateCode = "sms_10_code_62";
	
	// 通知管理员模板
	public static String notifyTemplateCode = "sms_10_code_66";
	
	//短信sign
	public static String singName = "轻音互动";

	//发送短信http地址
	public static String send_msg_url = ResourceBundle.getBundle("thirdconfig").getString("send_msg_url");
	
	//声优阀值配置
	public static String threshold = ResourceBundle.getBundle("thirdconfig").getString("warning_msg_threshold");
	
	//管理员的手机号
	private static String[] adminPhone = ResourceBundle.getBundle("thirdconfig").getString("warning_msg_phone").split(",");
	
	//固定线程池
	private static ExecutorService notifyThreadPool = Executors.newFixedThreadPool(5);
	

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
	public static String sendSms(String uuId, String tel, Integer templateType, String... codeParams) {
		try {
			Date date = new Date();
			SimpleDateFormat month = new SimpleDateFormat("MM");

			SimpleDateFormat day = new SimpleDateFormat("dd");

			HashMap<String, Object> paramMap = new HashMap<>();
			HashMap<String, Object> paramMap1 = new HashMap<>();

			paramMap.put("requestId", uuId);
			if (templateType == 1) {
				paramMap.put("templateId", codeTemplateCode);
				paramMap1.put("code", codeParams[0]);

			} else if (templateType == 2) {
				//通知管理员扔入线程池
				notifyThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						notifyAdmin(tel);
					}
				});
				
				paramMap.put("templateId", orderTemplateCode);
				paramMap1.put("month", month.format(date));
				paramMap1.put("day", day.format(date));
				paramMap1.put("order", codeParams[0]);

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
	
	private static void notifyAdmin(String tel){
		//判断当天100条短信发送给管理人员
		int n = 1;
		Object count = JedisUtil.get(RedisKeyConstants.PHONE_SMS_COUNT+tel);
		if(count != null){
			n = Integer.valueOf(count+"")+1;
			if(n % Integer.valueOf(threshold) == 0){
				HashMap<String, Object> warningParam = new HashMap<>();
				HashMap<String, Object> warningParam1 = new HashMap<>();
				warningParam1.put("mobile", tel);
				warningParam.put("templateId", notifyTemplateCode);
				warningParam.put("appNum", 10);// 4:惠花花，5:秒购 10：轻音
				warningParam.put("signName", singName);
				warningParam.put("templateType", 1);// 1:即时 2:系统 3:市场
				warningParam.put("codeParams", warningParam1);
				for(String phone:adminPhone){
					warningParam.put("phone", phone);
					warningParam.put("requestId", getUUID());
					String json = JSON.toJSONString(warningParam, true);
					HttpClientUtils.doPost(send_msg_url, json);
				}
			}
		}
		JedisUtil.set(RedisKeyConstants.PHONE_SMS_COUNT+tel, n+"",DateUtils.getZeroTimestamp());
	}
	
	private static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 

	public static void main(String[] args) {
		JedisUtil.set(RedisKeyConstants.PHONE_SMS_COUNT+"13057035325", "99",DateUtils.getZeroTimestamp());
		notifyAdmin("13057035325");
		
//		sendSms("11244111411", "18217583747", 2, "小花");

	}

}
