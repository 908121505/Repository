package com.honglu.quickcall.common.third.newrongyun;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.third.newrongyun.io.rong.RongCloud;
import com.honglu.quickcall.common.third.newrongyun.io.rong.messages.BaseMessage;
import com.honglu.quickcall.common.third.newrongyun.io.rong.messages.TxtMessage;
import com.honglu.quickcall.common.third.newrongyun.io.rong.methods.message._private.Private;
import com.honglu.quickcall.common.third.newrongyun.io.rong.methods.user.User;
import com.honglu.quickcall.common.third.newrongyun.io.rong.models.Result;
import com.honglu.quickcall.common.third.newrongyun.io.rong.models.message.PrivateMessage;
import com.honglu.quickcall.common.third.newrongyun.io.rong.models.response.ResponseResult;
import com.honglu.quickcall.common.third.newrongyun.io.rong.models.response.TokenResult;
import com.honglu.quickcall.common.third.newrongyun.io.rong.models.user.UserModel;
import com.honglu.quickcall.common.third.rongyun.RongYunPushBean;
import com.honglu.quickcall.common.third.rongyun.SendUser;

public class RongYunUtil {
	// private static String APPKEY =
	// ResourceBundle.getBundle("thirdconfig").getString("appkey");
	// private static String APPSECRET =
	// ResourceBundle.getBundle("thirdconfig").getString("appsecret");

	private static String APPKEY = "tdrvipkstxe05";
	private static String APPSECRET = "aEwhqSySuNPhFo";

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
	 */
	public static String getToken(String id, String username, String imageUrl) {
		// 实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		//
		UserModel user = new UserModel();
		user.setId(id);
		user.setName(username);
		user.setPortrait(imageUrl);
		User u = new User(APPKEY, APPSECRET);
		u.setRongCloud(rongCloud);
		try {
			TokenResult result = u.register(user);
			return result.getToken();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刷新用户信息
	 */
	public static Result refreshUser(String id, String username, String imageUrl) {

		// 实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		//
		UserModel user = new UserModel();
		user.setId(id);
		user.setName(username);
		user.setPortrait(imageUrl);
		User u = new User(APPKEY, APPSECRET);
		u.setRongCloud(rongCloud);
		try {
			Result result = u.update(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 发送单聊消息的方法
	 */
	public static ResponseResult publishPrivate(String fromUserId, String[] toUserId, BaseMessage message) {

		// 实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		//
		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setSenderId(fromUserId);
		privateMessage.setTargetId(toUserId);
		privateMessage.setContent(message);
		privateMessage.setObjectName("RC:TxtMsg");

		Private u = new Private(APPKEY, APPSECRET);
		u.setRongCloud(rongCloud);
		try {
			ResponseResult result = u.send(privateMessage);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// System.out.println("broadcast: " + messageBroadcastResult.toString());
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

		BaseMessage baseMessage = new TxtMessage(jsonString, "");
		String[] otherIds = new String[1];
		otherIds[0] = String.valueOf(otherId);
		publishPrivate(String.valueOf(fromUserId), otherIds, baseMessage);

	}

	@SuppressWarnings("unused")
	private static void sendMessage(String nickName, Long fromUserId, Long toCustomerId, String content, Integer sex,
			String headPortraitUrl, Integer type, String remarkName) {

	}

	public static void main(String[] args) {
		Long toCustomerId = 1811131520196475332L;
		sendSystemMessage(toCustomerId, "你好帅");
		sendActivityMessage(toCustomerId, "你好帅");
		sendOrderMessage(toCustomerId, "你好帅");
		sendBespokeMessage(toCustomerId, "你好帅");

	}

}
