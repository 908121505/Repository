package com.honglu.quickcall.user.facade.code;

/**
 * Created by len.song on 2017-12-07. 只用来存放rpc功能点code
 * 组成：项目编码(MyServiceCode.USER.code()) + "变量"(从 001 - 500 范围)
 */
public interface UserFunctionType {

	// 检查是否注册
	String CheckPhone = "001001";
	// 注册
	String register = "001002";
	// 登录
	String login = "001003";
	// 设置、找回密码
	String setpwd = "001004";
	// 设置头像
	String setHeardUrl = "001005";
	// 发送短信
	String getSmsCode = "001006";
	// 绑定微信或者QQ
	String bindVXorQQ = "001007";

	// 阅读关注(底部tab 点击关注)
	String readAttention = "001008";
	// 关注未读数量
	String NoReadAttentionCount = "001009";
	// 退出登录
	String loginOut = "001010";
	// 系统相关 001300 - 001350
	String MarketOnOff = "001300";
	String AppConfig = "001301";

	/**
	 * 推送APP消息
	 */
	String PUSH_APP_MSG = "001310";

	/**
	 * 未读消息数量查询
	 */
	String UNREAD_MESSAGE_NUM = "001020";
	/**
	 * 用户身份认证信息查询接口
	 */
	String USER_ID_CARD_CERTIFY_INFO = "001021";
	/**
	 * 保存用户认证信息
	 */
	String SAVE_USER_CERTIFY_INFO = "001022";

	// 保存昵称和头像
	String SaveNicknameImage = "0011120";
	// 保存性别
	String SaveGender = "0011121";
	// 保存签名
	String SaveSignName = "0011125";
	// 保存生日
	String SaveBirthday = "0011122";
	// 保存兴趣
	String SaveInterest = "0011123";
	// 保存职业
	String SaveOccupation = "0011124";

	/** 查询兴趣列表 */
	public static final String QUERY_INTEREST_LIST = "0011127";
	/** 查询职业列表 */
	public static final String QUERY_OCCUPATION_LIST = "0011129";
	/** 查询关注/粉丝 */
	public static final String QUERY_ATTENDTION_FANS_LIST = "0011130";
	/** 添加/取消关注 */
	public static final String ADD_OR_UPDATE_FANS = "0011131";
	/** 判断是否关注对方 */
	public static final String CHECK_ATTENTION = "0011132";

	/** 保存大V声音信息 */
	public static final String SAVE_DV_VOICE_INFO = "0011133";

	// 首页搜索用户
	public static final String SEARCH_PERSON_LIST = "0011134";

	// 查看我的技能列表
	public static final String QUERY_MY_SKILL = "0011135";

	// 保存大V技能审核信息
	public static final String SAVE_DV_SKILL_AUDIT = "0011136";
	
	/** 判断是否关注对方 */
	public static final String CHECK_EACH_ATTENTION = "0011137";

	/**
	 * 修改昵称
	 */
	String updateNickname = "0011140";
	/**
	 * 修改头像
	 */
	String updateHeadPortrait = "0011141";
	/**
	 * 修改个性签名
	 */
	String updateSignName = "0011142";
	/**
	 * 修改星座
	 */
	String updateStarSign = "0011143";
	/**
	 * 修改形象照
	 */
	String updateAppearance = "0011144";
	/**
	 * 查询兴趣列表
	 */
	String queryInterestList = "0011149";
	/**
	 * 修改兴趣
	 */
	String updateInterest = "0011145";
	/**
	 * 删除形象照
	 */
	String removeAppearance = "0011146";
	/**
	 * 修改声鉴卡
	 */
	String updateVoiceIdentificationCard = "0011147";
	/**
	 * 删除声鉴卡
	 */
	String removeVoiceIdentificationCard = "0011148";

	/**
	 * 个人中心接口
	 */
	String CUSTOMER_CENTER = "0011150";

	/**
	 * 客户主页接口
	 */
	String CUSTOMER_HOME = "0011151";

	/**
	 * 我的等级页面接口
	 */
	String CUSTOMER_LEVEL = "0011152";

	/**
	 * 删除黑名单
	 */
	String removeBlacklist = "0011160";
	/**
	 * 查询黑名单
	 */
	String queryBlacklist = "0011161";
	/**
	 * 添加黑名单
	 */
	String saveBlacklist = "0011162";
	/**
	 * 修改性别
	 */
	String updateGender = "0011163";
	/**
	 * 修改年龄
	 */
	String updateBirthday = "0011164";
	/**
	 * 编辑资料页面数据展示
	 */
	String queryUserEditInfo = "0011165";

	/**
	 * 查询所有非其他原因的举报
	 */
	String getAllDelate = "0011170";

	/**
	 * 插入举报
	 */
	String insertDelate = "0011171";

	/**
	 * 插入反馈
	 */
	String insertFeedBack = "0011180";

}
