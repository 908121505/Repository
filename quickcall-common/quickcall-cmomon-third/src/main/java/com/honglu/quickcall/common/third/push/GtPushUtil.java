package com.honglu.quickcall.common.third.push;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

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

	public static void main(String[] args) {
		String cid = "34a8710a9b67e9227a4b73ed56e05bdc";
		sendLinkTemplateToSingle(cid, "s", "aa", "http://www.baidu.com");
		// sendLinkTemplateList(cid, "s", "aa", "http://www.baidu.com");
	}

}
