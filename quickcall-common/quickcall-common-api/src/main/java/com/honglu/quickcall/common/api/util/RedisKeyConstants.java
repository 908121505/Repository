
package com.honglu.quickcall.common.api.util;

/**
 * Created by wlj
 */
public class RedisKeyConstants {

	// 系统参数
	public static final String SYS_PARAM = "pp_sys:param:";
	// 是否为活动充值的key
	public static final String RECHARGE_ACTIVITY = "user:recharge_activity:";

	// 用户的心跳
	public static final String HEARTBEAT = "user:heartbeat";
	// 用户心跳离线标识
	public static final String HEART_TO_ONLINE = "user:heartBeatToOnline";
	// 用户验证码
	public static final String USER_VERIFYCODE = "user:verifycode:";
	// 用户验证码一分钟获取次数
	public static final String USER_VERIFYCODE_M = "user:verifycode:m:";
	// 用户验证码一小时钟获取次数
	public static final String USER_VERIFYCODE_H = "user:verifycode:h:";
	// 用户验证码一天获取次数
	public static final String USER_VERIFYCODE_D = "user:verifycode:d:";

	// 用户冻结的流水ID 多个以逗号分隔
	public static final String ACCOUNT_USERFROZEN_USER = "account:userfrozen:userId:";

	// 流水Id记录对应的金额
	public static final String ACCOUNT_USERFROZEN_STREAM = "account:userfrozen:stream:";
	// 流水冻结 24小时
	public static final String ACCOUNT_USERFROZEN_Time = "account:userfrozen:stream:time:";
	// 流水对应的订单Id
	public static final String ACCOUNT_USERFROZEN_ORDER_NO = "account:userfrozen:stream:orderNo:";
	// 用户信息緩存（用戶Id）
	public static final String USER_CUSTOMER_INFO = "voice_user:customerinfo:";
	// 充值回调锁
	public static final String ACCOUNT_ORDER_NO_NX = "account:orderNo:nx:";
	
	
	// 检查接单开关是否开启
	public static final String ACCOUNT_RECEIVE_NO_NX = "account:receive:nx:";
	
	// 用户短信次数
	public static final String PHONE_SMS_COUNT = "sms:send:count:";
	// 完成订单服务锁
	public static final String FINISH_ORDER_KEY = "account:finish:order:";
	
	/**下单锁限制*/
//	public static final String ORDER_KEY = "account:save:order:";
	/**取消订单*/
	public static final String CANCEL_ORDER_KEY = "account:cancel:order:";
	/**取消订单*/
	public static final String CANCEL_RECEIVE_KEY = "account:receive:order:";
	/**取消订单*/
	public static final String SAVE_ORDER_KEY = "account:save:order:";
	/**取消订单*/
	public static final String CONFIRM_FINISH_ORDER_KEY = "account:confirmfinish:order:";
	//用户关注3分钟状态
	public static final String CUSTOMER_ATTENTION_TIME = "user:attentionTime:";

	//活动activity
	/**分享活动领券*/
	public static final String ACTIVITY_RECEIVE_COUPON_KEY = "activity:receive:coupon:";

}
