package com.honglu.quickcall.common.third.rongyun.util;

import java.util.Date;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.third.rongyun.MsgContent;
import com.honglu.quickcall.common.third.rongyun.RongCloud;
import com.honglu.quickcall.common.third.rongyun.RongYunOrderMsgPushBean;
import com.honglu.quickcall.common.third.rongyun.RongYunPushBean;
import com.honglu.quickcall.common.third.rongyun.SendUser;
import com.honglu.quickcall.common.third.rongyun.messages.BaseMessage;
import com.honglu.quickcall.common.third.rongyun.messages.TxtMessage;
import com.honglu.quickcall.common.third.rongyun.models.CodeSuccessReslut;

/**
 * 操作融云的工具类
 *
 */
public class RongYunUtil {
	// 读取融云的配置参数
	public static String APPKEY = ResourceBundle.getBundle("thirdconfig").getString("appkey");
	public static String APPSECRET = ResourceBundle.getBundle("thirdconfig").getString("appsecret");

	// 系统用户Id
	public static String SYSTEM_COSTOMER_ID = "1810201521499459795";
	// 活动用户Id
	public static String ACTIVITY_COSTOMER_ID = "1810231738121684156";
	// 订单用户ID
	public static String ORDER_COSTOMER_ID = "1810231739254693242";
	// 预约用户Id
	public static String BESPOKE_COSTOMER_ID = "1809282012067564328";

	/**
	 * 获取 Token 方法
	 * 
	 * @param id
	 *            用户在数据库中的唯一标识
	 * @param username
	 *            用户昵称
	 * @param imageUrl
	 *            用户头像
	 * @return
	 *//*
		 * public static String getToken(String id, String username, String imageUrl) {
		 * // 实例化 RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET); //
		 * TokenReslut userGetTokenResult = null; try { userGetTokenResult =
		 * rongCloud.user.getToken(id, username, imageUrl); if (userGetTokenResult ==
		 * null) { userGetTokenResult = rongCloud.user.getToken(id, username, imageUrl);
		 * } if (userGetTokenResult == null) { userGetTokenResult =
		 * rongCloud.user.getToken(id, username, imageUrl); } return
		 * userGetTokenResult.getToken(); } catch (Exception e) { e.printStackTrace();
		 * return null; } }
		 */

	/**
	 * 刷新用户信息
	 */
	public static CodeSuccessReslut refreshUser(String id, String username, String imageUrl) {

		CodeSuccessReslut userRefreshResult = null;
		try {
			// 实例化
			RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
			// 刷新用户信息方法
			userRefreshResult = rongCloud.user.refresh(id, username, imageUrl);
			if (userRefreshResult == null) {
				userRefreshResult = rongCloud.user.refresh(id, username, imageUrl);
			}
			if (userRefreshResult == null) {
				userRefreshResult = rongCloud.user.refresh(id, username, imageUrl);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return userRefreshResult;
	}

	/**
	 * 发送系统消息
	 */
	public static void sendUser() {
		// 实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		// 发送广播消息方法（发送消息给一个应用下的所有注册用户，如用户未在线会对满足条件（绑定手机终端）的用户发送 Push 信息，单条消息最大
		// 128k，会话类型为 SYSTEM。每小时只能发送 1 次，每天最多发送 3 次。）
		TxtMessage messageBroadcastTxtMessage = new TxtMessage("1213w21321", "21312312321");
		CodeSuccessReslut messageBroadcastResult = null;
		try {
			messageBroadcastResult = rongCloud.message.broadcast("170", messageBroadcastTxtMessage, "thisisapush",
					"{\"pushData\":\"hello\"}", "iOS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("broadcast:  " + messageBroadcastResult.toString());
	}

	/**
	 * 发送单聊消息的方法
	 */
	public static Integer publishPrivate(String fromUserId, String[] toUserId, BaseMessage message, String pushContent,
			String pushData, String count, Integer verifyBlacklist, Integer isPersisted, Integer isCounted) {

		CodeSuccessReslut messageBroadcastResult = null;
		try {
			// 实例化
			RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
			messageBroadcastResult = rongCloud.message.publishPrivate(fromUserId, toUserId, message, pushContent,
					pushData, count, verifyBlacklist, isPersisted, isCounted);
			if (messageBroadcastResult == null) {
				messageBroadcastResult = rongCloud.message.publishPrivate(fromUserId, toUserId, message, pushContent,
						pushData, count, verifyBlacklist, isPersisted, isCounted);
			}
			if (messageBroadcastResult == null) {
				messageBroadcastResult = rongCloud.message.publishPrivate(fromUserId, toUserId, message, pushContent,
						pushData, count, verifyBlacklist, isPersisted, isCounted);
			}
			return messageBroadcastResult.getCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		// System.out.println("broadcast: " + messageBroadcastResult.toString());
	}

	/**
	 * 禁用
	 */
	public static void stopUser() {
		// 实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		// 封禁用户方法（每秒钟限 100 次）
		CodeSuccessReslut userBlockResult = null;
		try {
			userBlockResult = rongCloud.user.block("190", 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("block:  " + userBlockResult.toString());
	}

	/**
	 * 发送系统消息
	 * 
	 * @param toCustomerId
	 *            接收人的用户ID
	 * @param content
	 *            发送内容
	 * 
	 */
	public static void sendSystemMessage(Long toCustomerId, String content) {

		Long fromUserId = Long.parseLong(SYSTEM_COSTOMER_ID);
		String imageUrl = "http://wdgj.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/25d8dfa082314cb693f78faaf51581d8.jpg";

		sendMessage("系统消息", fromUserId, toCustomerId, content, 1, imageUrl, 10);
	}

	/**
	 * 发送系统消息
	 * 
	 * @param toCustomerId
	 *            接收人的用户ID
	 * @param content
	 *            发送内容
	 * 
	 */
	public static void sendSystemMessage(Long toCustomerId, String content, String remarkName) {

		Long fromUserId = Long.parseLong(SYSTEM_COSTOMER_ID);
		String imageUrl = "http://wdgj.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/25d8dfa082314cb693f78faaf51581d8.jpg";

		sendMessage("系统消息", fromUserId, toCustomerId, content, 1, imageUrl, 10, remarkName);
	}

	/**
	 * 发送活动消息
	 * 
	 * @param toCustomerId
	 *            接收人的用户ID
	 * @param content
	 *            发送内容
	 * 
	 */
	public static void sendActivityMessage(Long toCustomerId, String content) {
		Long fromUserId = Long.parseLong(ACTIVITY_COSTOMER_ID);
		String imageUrl = "http://wdgj.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/d13b199ed66f4ba1aedc6ac4b32e00cd.jpg";

		sendMessage("活动消息", fromUserId, toCustomerId, content, 1, imageUrl, 10);
	}

	/**
	 * 发送订单消息
	 * 
	 * @param toCustomerId
	 *            接收人的用户ID
	 * @param content
	 *            发送内容
	 * 
	 */
	public static void sendOrderMessage(Long toCustomerId, String content) {
		Long fromUserId = Long.parseLong(ORDER_COSTOMER_ID);
		String imageUrl = "http://wdgj.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/dd85f3b6b42e441eb48f2839752474cc.jpg";

		sendMessage("订单消息", fromUserId, toCustomerId, content, 1, imageUrl, 10);
	}

	/**
	 * 发送订单消息
	 * 
	 * @param toCustomerId
	 *            接收人的用户ID
	 * @param content
	 *            发送内容
	 * 
	 */
	public static void sendOrderMessage(Long toCustomerId, String content, String remarkName) {
		Long fromUserId = Long.parseLong(ORDER_COSTOMER_ID);
		String imageUrl = "http://wdgj.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/dd85f3b6b42e441eb48f2839752474cc.jpg";
		sendMessage("订单消息", fromUserId, toCustomerId, content, 1, imageUrl, 10, remarkName);
	}

	/**
	 * 推送订单IM消息
	 * 
	 * @param toCustomerId
	 * @param remarkName
	 * @param orderId
	 * @param orderDesc
	 * @param imageUrl
	 */
	public static void sendOrderIMMessage(String userName, Long fromUserId, Long toCustomerId, String remarkName,
			Long orderId, String orderDesc, String imageUrl) {
		Integer type = 21;
		sendMessageIm(userName, fromUserId, toCustomerId, 1, imageUrl, type, remarkName, orderId, orderDesc);
	}

	public static void sendBespokeMessage(Long toCustomerId, String content) {
		Long fromUserId = Long.parseLong(BESPOKE_COSTOMER_ID);
		String imageUrl = "http://wdgj.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/d122cdf6638e4765894b62fff69a68e6.jpg";
		sendMessage("预约消息", fromUserId, toCustomerId, content, 1, imageUrl, 10);
	}

	/**
	 * 发送关注消息
	 * 
	 * @param fromUserId
	 *            发送人 用户Id
	 * @param nickName
	 *            发送人的昵称
	 * @param headPortraitUrl
	 *            发送人的头像
	 * @param toCustomerId
	 *            接收人的用户ID
	 * @param content
	 *            发送内容
	 * @param sex
	 *            发送人的性别
	 */
	public static void sendFansMessage(Long fromUserId, String nickName, String headPortraitUrl, Long toCustomerId,
			String content, Integer sex) {
		sendMessage(nickName, fromUserId, toCustomerId, content, sex, headPortraitUrl, 20);
	}

	private static void sendMessage(String nickName, Long fromUserId, Long toCustomerId, String content, Integer sex,
			String headPortraitUrl, Integer type) {

		Long otherId = toCustomerId;
		// refreshUser(fromUserId + "", nickName, headPortraitUrl);
		SendUser sendUser = new SendUser(nickName, headPortraitUrl, sex, fromUserId);
		RongYunPushBean rongYunPushBean = new RongYunPushBean(1, content, 1, 0, type, fromUserId, otherId, 1, sendUser,
				System.currentTimeMillis());
		String jsonString = JSON.toJSONString(rongYunPushBean);
		TxtMessage txtMessage = new TxtMessage(jsonString, "");
		String[] otherIds = new String[1];
		otherIds[0] = String.valueOf(otherId);
		Integer code = RongYunUtil.publishPrivate(String.valueOf(fromUserId), otherIds, txtMessage, null, null, null,
				null, null, null);
		System.out.println(code);
	}

	private static void sendMessage(String nickName, Long fromUserId, Long toCustomerId, String content, Integer sex,
			String headPortraitUrl, Integer type, String remarkName) {

		Long otherId = toCustomerId;
		// refreshUser(fromUserId + "", nickName, headPortraitUrl);
		SendUser sendUser = new SendUser(nickName, headPortraitUrl, sex, fromUserId, remarkName);
		RongYunPushBean rongYunPushBean = new RongYunPushBean(1, content, 1, 0, type, fromUserId, otherId, 1, sendUser,
				System.currentTimeMillis());
		String jsonString = JSON.toJSONString(rongYunPushBean);
		TxtMessage txtMessage = new TxtMessage(jsonString, "");
		String[] otherIds = new String[1];
		otherIds[0] = String.valueOf(otherId);
		Integer code = RongYunUtil.publishPrivate(String.valueOf(fromUserId), otherIds, txtMessage, null, null, null,
				null, null, null);
		System.out.println(code);
	}

	private static void sendMessageIm(String nickName, Long fromUserId, Long toCustomerId, Integer sex,
			String headPortraitUrl, Integer type, String remarkName, Long orderId, String orderDesc) {

		Long otherId = toCustomerId;
		SendUser sendUser = new SendUser(nickName, headPortraitUrl, sex, fromUserId, remarkName);
		MsgContent msgContent = new MsgContent(orderId, orderDesc, 41);
		RongYunOrderMsgPushBean rongYunPushBean = new RongYunOrderMsgPushBean(1, msgContent, 1, 0, 10,
				new Date().getTime(), sendUser, fromUserId, toCustomerId, 21);
		String jsonString = JSON.toJSONString(rongYunPushBean);
		TxtMessage txtMessage = new TxtMessage(jsonString, "");
		String[] otherIds = new String[1];
		otherIds[0] = String.valueOf(otherId);
		Integer code = RongYunUtil.publishPrivate(String.valueOf(fromUserId), otherIds, txtMessage, null, null, null,
				null, null, null);
		System.out.println(code);
	}

	public static void main(String[] args) {

	}
}
