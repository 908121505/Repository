
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

}
