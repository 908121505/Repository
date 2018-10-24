package com.honglu.quickcall.common.third.rongyun.util;

import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.third.rongyun.RongCloud;
import com.honglu.quickcall.common.third.rongyun.RongYunPushBean;
import com.honglu.quickcall.common.third.rongyun.SendUser;
import com.honglu.quickcall.common.third.rongyun.messages.BaseMessage;
import com.honglu.quickcall.common.third.rongyun.messages.TxtMessage;
import com.honglu.quickcall.common.third.rongyun.models.CodeSuccessReslut;
import com.honglu.quickcall.common.third.rongyun.models.TokenReslut;

/**
 * 操作融云的工具类
 *
 */
public class RongYunUtil {
	// 读取融云的配置参数
	public static String APPKEY = ResourceBundle.getBundle("thirdconfig").getString("appkey");
	public static String APPSECRET = ResourceBundle.getBundle("thirdconfig").getString("appsecret");

	// 系统用户Id
	public static String SYSTEM_COSTOMER_ID = ResourceBundle.getBundle("thirdconfig").getString("SYSTEM_COSTOMER_ID");
	// 活动用户Id
	public static String ACTIVITY_COSTOMER_ID = ResourceBundle.getBundle("thirdconfig")
			.getString("ACTIVITY_COSTOMER_ID");
	// 订单用户ID
	public static String ORDER_COSTOMER_ID = ResourceBundle.getBundle("thirdconfig").getString("ORDER_COSTOMER_ID");

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
	 */
	public static String getToken(String id, String username, String imageUrl) {
		// 实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		//
		TokenReslut userGetTokenResult = null;
		try {
			userGetTokenResult = rongCloud.user.getToken(id, username, imageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGetTokenResult.getToken();
	}

	/**
	 * 刷新用户信息
	 */
	public static CodeSuccessReslut refreshUser(String id, String username, String imageUrl) {
		// 实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		//
		CodeSuccessReslut userRefreshResult = null;
		// 刷新用户信息方法
		try {
			userRefreshResult = rongCloud.user.refresh(id, username, imageUrl);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("broadcast:  " + messageBroadcastResult.toString());
	}

	/**
	 * 发送单聊消息的方法
	 */
	public static Integer publishPrivate(String fromUserId, String[] toUserId, BaseMessage message, String pushContent,
			String pushData, String count, Integer verifyBlacklist, Integer isPersisted, Integer isCounted) {
		// 实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		CodeSuccessReslut messageBroadcastResult = null;
		try {
			messageBroadcastResult = rongCloud.message.publishPrivate(fromUserId, toUserId, message, pushContent,
					pushData, count, verifyBlacklist, isPersisted, isCounted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageBroadcastResult.getCode();
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
		sendMessage("系统消息", fromUserId, toCustomerId, content, 1,
				"http://wdgj.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/25d8dfa082314cb693f78faaf51581d8.jpg");
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
		sendMessage("活动消息", fromUserId, toCustomerId, content, 1, null);
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
		sendMessage("订单消息", fromUserId, toCustomerId, content, 1, null);
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
		sendMessage(nickName, fromUserId, toCustomerId, content, sex, headPortraitUrl);
	}

	private static void sendMessage(String nickName, Long fromUserId, Long toCustomerId, String content, Integer sex,
			String headPortraitUrl) {

		Long otherId = toCustomerId;
		SendUser sendUser = new SendUser(nickName, headPortraitUrl, sex, otherId);
		RongYunPushBean rongYunPushBean = new RongYunPushBean(1, content, 1, 0, 10, fromUserId, otherId, 1, sendUser,
				System.currentTimeMillis());
		String jsonString = JSON.toJSONString(rongYunPushBean);
		TxtMessage txtMessage = new TxtMessage(jsonString, "");
		String[] otherIds = new String[1];
		otherIds[0] = String.valueOf(otherId);
		Integer code = RongYunUtil.publishPrivate(String.valueOf(fromUserId), otherIds, txtMessage, null, null, null,
				null, null, null);
		System.out.println(code);
	}

	public static void main(String[] args) {
		// sendUser();
		/*
		 * Long fromUserId = 1810161814552346784L; String nickName = "Aaaa"; String
		 * photo =
		 * "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/user/headimg/0dd3d0611bfa4e2e8f118a1d8f01dbb7.jpg";
		 * Integer sex = 2; Long otherId = 1810161659557030302L; SendUser sendUser = new
		 * SendUser(nickName, photo, sex, otherId); RongYunPushBean rongYunPushBean =
		 * new RongYunPushBean(1, "你好", 1, 0, 10, fromUserId, otherId, 1, sendUser,
		 * System.currentTimeMillis()); String jsonString =
		 * JSON.toJSONString(rongYunPushBean); TxtMessage txtMessage = new
		 * TxtMessage(jsonString, ""); String[] otherIds = new String[1]; otherIds[0] =
		 * String.valueOf(otherId); Integer code =
		 * RongYunUtil.publishPrivate(String.valueOf(fromUserId), otherIds, txtMessage,
		 * null, null, null, null, null, null); System.out.println(code);
		 */

		sendSystemMessage(1810161659557030302L, "我喜欢亚波");
		/*
		 * refreshUser("1810201521499459795", "系统消息",
		 * "shanghai.aliyuncs.com/voice/user/headimg/25d8dfa082314cb693f78faaf51581d8.jpg"
		 * );
		 */
	}
}
