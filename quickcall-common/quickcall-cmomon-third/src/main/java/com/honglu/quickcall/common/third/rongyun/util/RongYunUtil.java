package com.honglu.quickcall.common.third.rongyun.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.ObjectUtils.Null;

import com.honglu.quickcall.common.third.rongyun.RongCloud;
import com.honglu.quickcall.common.third.rongyun.messages.BaseMessage;
import com.honglu.quickcall.common.third.rongyun.messages.TxtMessage;
import com.honglu.quickcall.common.third.rongyun.models.CodeSuccessReslut;
import com.honglu.quickcall.common.third.rongyun.models.TokenReslut;


/**
 * 操作融云的工具类
 *
 */
public class RongYunUtil {
	//读取融云的配置参数
	public static String APPKEY = ResourceBundle.getBundle("thirdconfig").getString("appkey");
	public static String APPSECRET = ResourceBundle.getBundle("thirdconfig").getString("appsecret");
	
	
	/**
	 * 获取 Token 方法 
	 * @param id     用户在数据库中的唯一标识
	 * @param username  用户昵称
	 * @param imageUrl	用户头像
	 * @return
	 */
	public static String getToken(String id,String username,String imageUrl){
		//实例化
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
	public static CodeSuccessReslut refreshUser(String id,String username,String imageUrl){
		//实例化
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
	public static void sendUser(){
		//实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		// 发送广播消息方法（发送消息给一个应用下的所有注册用户，如用户未在线会对满足条件（绑定手机终端）的用户发送 Push 信息，单条消息最大 128k，会话类型为 SYSTEM。每小时只能发送 1 次，每天最多发送 3 次。） 
		TxtMessage messageBroadcastTxtMessage = new TxtMessage("1213w21321", "21312312321");
		CodeSuccessReslut messageBroadcastResult = null;
		try {
			messageBroadcastResult = rongCloud.message.broadcast("170", messageBroadcastTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", "iOS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("broadcast:  " + messageBroadcastResult.toString());
	}
	
	/**
	 * 发送单聊消息的方法
	 */
	public static Integer publishPrivate(String fromUserId, String[] toUserId, BaseMessage message, String pushContent, String pushData, String count, Integer verifyBlacklist, Integer isPersisted, Integer isCounted){
		//实例化
		RongCloud rongCloud = RongCloud.getInstance(APPKEY, APPSECRET);
		CodeSuccessReslut messageBroadcastResult = null;
		try {
			messageBroadcastResult = rongCloud.message.publishPrivate(fromUserId, toUserId, message, pushContent,  pushData,  count,  verifyBlacklist, isPersisted, isCounted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageBroadcastResult.getCode();
		//System.out.println("broadcast:  " + messageBroadcastResult.toString());
	}
	
	/**
	 * 禁用
	 */
	public static void stopUser(){
		//实例化
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
	
	public static void main(String[] args) {
		//refreshUser("999","梦逐乐2434","http://kuaihu.xnsudai.com/1513170336945.png"); BaseMessage
		Map<String, Object> map=new HashMap<>();
		map.put("categoryId", 1);
		map.put("content", "1234");
		map.put("direction", 1);
		//map.put("id", 4);
		map.put("receiveStatus", 0);
		//map.put("receiveTime", 0);
		map.put("sendStatus", 10);
		//map.put("sendTime", 1516167379006);
		map.put("senderId", 123);
		map.put("targetId", 109);
		map.put("type", 1);
		TxtMessage messageBroadcastTxtMessage = new TxtMessage(map.toString(), "1111");
		String[] y= new String[]{"109"};
		publishPrivate("123",y,messageBroadcastTxtMessage,null,null,null,null,null,null);
	}
}
