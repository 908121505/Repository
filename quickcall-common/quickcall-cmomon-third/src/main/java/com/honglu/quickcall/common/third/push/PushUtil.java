package com.honglu.quickcall.common.third.push;

import java.util.HashMap;
import java.util.Map;

import cn.jiguang.commom.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.commom.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.gson.JsonObject;

public class PushUtil {
    protected static final Logger logger = LoggerFactory.getLogger(PushUtil.class);

    // 加载配置信息
	private static final String appKey = JpushConfig.appKey;
	private static final String masterSecret = JpushConfig.masterSecret;

	public static void main(String[] args) {
		PushUtil.pushMessageWithType("nvhsn", "新增粉丝，用户："+"测试测试" + "关注了你。",4,"126","103");
	}

	public static void pushMessageWithType(String title,String content,Integer type,String aliasses,String jumpParam){
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
		PushPayload payload = pushMessageWithTypeToObject(title,content,type,aliasses,jumpParam);

		try {
			logger.info("jSend_payload_object = " + payload.toString());
			PushResult result = jpushClient.sendPush(payload);
			logger.info("jSend_articlesended_notice result="+result);

		} catch (APIConnectionException e) {
			logger.error("jSend_error_info_APIConnectionException:"+e.getMessage(),e);
		} catch (APIRequestException e) {
			logger.info("jSend_payload_object_error = " + payload.toString()+" 字段显示完成end");
			logger.error("jSend_error_info_APIRequestException:"+e.getErrorMessage(),e);
			logger.error("Error response from JPush server. Should review and fix it. "+ e);
			logger.error("HTTP Status: " + e.getStatus());
			logger.error("Error Code: " + e.getErrorCode());
			logger.error("Error Message: " + e.getErrorMessage());
			logger.error("Msg ID: " + e.getMsgId());
		}
	}
	



	/**
	 *
	 * @param aliasses		名称
	 * @param content		内容
	 * @param type			类型
	 * @return
	 */
	public static PushPayload pushMessageWithTypeToObject(String title,String content,Integer type,String aliasses,String jumpParam){
		if(StringUtils.isEmpty(aliasses)){
			return null;
		}

		String [] strs = aliasses.split(",");
		return  PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.alias(strs))
				.setNotification(Notification.newBuilder()
						//推送到Android
						.addPlatformNotification(AndroidNotification.newBuilder()
								.setTitle(title)
								.setAlert(content)
								.addExtra("from","JPush")
								.addExtra("type",type)
								.addExtra("jumpParam", jumpParam)
								.build())
						//推送到IOS
						.addPlatformNotification(IosNotification.newBuilder()
								.setAlert(content)
								.addExtra("from","JPush")
								.setBadge(5)
								.setSound("happy")
								.addExtra("type",type)
								.addExtra("jumpParam", jumpParam)
								.build())
						.build())
				.setMessage(Message.content(content))
				.setOptions(Options.newBuilder()
						.setApnsProduction(true)
						.build())
				.build();

	}

}

