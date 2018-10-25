package com.honglu.quickcall.common.third.push;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.notify.Notify;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class GtPushUtil {

	private static String appId = "9fBgnnLn3b5FFnYCx2ye03";
	private static String appKey = "Lern4lI6bo8olioLsO7Gg";
	private static String masterSecret = "SlJ5Jt4Syh9NN8dpzkrN31";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	private static final Logger logger = LoggerFactory.getLogger(GtPushUtil.class);

	// 单推
	/**
	 * 
	 * @param cid
	 *            个推Id
	 * @param title
	 *            标题
	 * @param content
	 *            正文
	 * @param url
	 *            跳转url
	 */
	public static void sendLinkTemplateToSingle(String cid, String title, String content, String url) {
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		LinkTemplate template = linkTemplateDemo(title, content, url);
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 3600 * 1000);
		message.setData(template);
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(cid);
		// target.setAlias(Alias);
		IPushResult ret = null;
		try {
			// push.pushMessageToApp(message);
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			logger.info("cid" + cid + "个推 响应结果-----------" + ret.getResponse().toString());
		} else {
			logger.info("服务器响应异常");
		}

	}

	// 列表推
	public static String sendLinkTemplateList(String cids, String title, String content, String url) {
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		// 通知透传模板
		LinkTemplate template = linkTemplateDemo(title, content, url);
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List<Target> targets = new ArrayList<Target>();
		for (String cid : cids.split(",")) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(cid);
			targets.add(target);
		}
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		logger.info(ret.getResponse().toString());
		return taskId;

	}

	public static LinkTemplate linkTemplateDemo(String title, String content, String url) {
		LinkTemplate template = new LinkTemplate(); //
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey); //
		// 设置通知栏标题与内容
		template.setTitle(title);
		template.setText(content);
		// 配置通知栏图标
		template.setLogo("icon.png");
		// 配置通知栏网络图标，填写图标URL地址 template.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除 template.setIsRing(true); template.setIsVibrate(true);
		template.setIsClearable(true);

		// 设置打开的网址地址
		template.setUrl(url);
		return template;
	}

	// 透传列表推
	public static String sendNotificationTemplateToList(String alias, String alert, String title, String linkUrl) {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		// 通知透传模板
		TransmissionTemplate template = notificationTemplateDemo(alert, title, linkUrl);
		ListMessage message = new ListMessage();
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		message.setData(template);
		List<Target> targets = new ArrayList<Target>();
		Set<String> set = new HashSet<String>();
		Collections.addAll(set, alias.split(","));
		for (String cid : set) {
			Target target1 = new Target();
			target1.setAppId(appId);
			target1.setClientId(cid);
			targets.add(target1);
		}
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		logger.info(ret.getResponse().toString());
		return taskId;

	}

	public static TransmissionTemplate notificationTemplateDemo(String alert, String title, String linkUrl) {
		TransmissionTemplate template = new TransmissionTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 设置通知栏标题与内容

		// template.setAPNInfo(arg0);
		// .GtPushUtil. apnPayLoad
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		ObjectMapper json = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("linkUrl", linkUrl);
		map.put("alert", alert);
		String jsonparams = "";
		try {
			jsonparams = json.writeValueAsString(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("个推参数拼接异常");
		}
		template.setTransmissionContent(jsonparams);

		APNPayload payload = new APNPayload();

		// 在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
		payload.setAutoBadge("+1");
		// payload.setContentAvailable(1);
		payload.setSound("default");
		payload.setCategory("$由客户端定义");
		// 简单模式APNPayload.SimpleMsg
		// payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));

		// 字典模式使用APNPayload.DictionaryAlertMsg
		payload.setAlertMsg(getDictionaryAlertMsg(alert, title));

		// 添加多媒体资源
		payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.video)
				.setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4").setOnlyWifi(true));

		template.setAPNInfo(payload);

		// 多厂商配置属性
		Notify notify = new Notify();
		notify.setContent(alert);
		notify.setPayload(jsonparams);
		notify.setUrl(linkUrl);
		notify.setTitle(title);
		template.set3rdNotifyInfo(notify);

		return template;

	}

	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String body, String title) {
		APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
		alertMsg.setBody(body);

		alertMsg.setTitle(title);

		return alertMsg;
	}

	public static void main(String[] args) {
		String cid = "9f9972a12abefe48274f3073a303f272";
		// sendLinkTemplateToSingle(cid, "s", "aa", "http://www.baidu.com");
		// sendLinkTemplateList(cid, "s", "aa", "http://www.baidu.com");
		sendNotificationTemplateToList(cid, "正文", "标题", "http://www.baidu.com");
	}

}
