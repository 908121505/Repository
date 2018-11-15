package com.honglu.quickcall.account.facade.constants;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单技能相关常量值
 * @Package: com.honglu.quickcall.account.facade.constants 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午1:59:56
 */
public interface OrderSkillConstants {
	
	
	/**订单消息页每页显示条数*/
	public static  final  Integer  ORDER_MSG_DEFAULT_PAGE_SIZE =  20 ;
	
	
	/**自动接单开关1：开启*/
	public  static final  Integer  AUTO_RECEIVE_OPEN =  1 ;
	/**自动接单开关0：关闭*/
	public  static final  Integer  AUTO_RECEIVE_CLOSE =  0 ;
	/**接单开关1：开启*/
	public  static final  Integer  RECEIVE_OPEN =  1 ;
	/**接单开关0：关闭*/
	public  static final  Integer  RECEIVE_CLOSE =  0 ;
	
	
	
	/**接单超时15分钟*/
	public static final  Integer  RECEIVE_ORDER_OVER_TIME =  15;
	/**声优5分钟未发起立即服务*/
	public static final  Integer  START_SERVICE_OVER_TIME_DAV =  5;
	/**用户5分钟未接受立即服务*/
	public static final  Integer  START_SERVICE_OVER_TIME_CUST =  5;
	
	
	
	/**消息标识：大V方*/
	public static final  String  MSG_CONTENT_DAV = "V";
	/**消息标识：用户方*/
	public static final  String  MSG_CONTENT_C = "C";
	
	
	/**1：订单不存在，可以下单 */
	public  static final  Integer  IM_RETCODE_CAN_ORDER = 1;
	/** 2：订单不存在 大V在忙*/
	public  static final  Integer  IM_RETCODE_DV_BUSY = 2;
	/** 3：订单存在*/
	public  static final  Integer  IM_RETCODE_ORDER_EXIST = 3;
	/** 4：大V不可下单*/
	public  static final  Integer  IM_RETCODE_ORDER_COUND_ORDER = 4;
	
	
	
	/***个推消息*/
	public  static final  String  GT_MSG_ORDER_TITLE = "订单消息";
	public  static final  String  GT_MSG_CONTENT_RECEIVE_ORDER = "您有新的订单，请及时查看";
	//大V提示需要跳转到接单记录页面
	public  static final  String  GT_MSG_CONTENT_RECEIVE_ORDER_URL = "voice://com.yanjing.voice/native?name=order_center&index=0&need_login=1";
	
//	voice://com.yanjing.voice/native?name=order_center&index=1&need_login=1    index-0接单记录1-点单记录
	
	public  static final  String  GT_MSG_CONTENT_START_SERVICE_TO_DAV = "您已发起立即服务，请提醒用户5分钟内响应，超时订单将失效";
	public  static final  String  GT_MSG_CONTENT_START_SERVICE_TO_DAV_URL = "voice://com.yanjing.voice/native?name=order_center&index=0&need_login=1";
	
	
	public  static final  String  GT_MSG_CONTENT_START_SERVICE_TO_CUST = "对方发起立即服务，请在5分钟内确认，超时未响应订单将失效";
	public  static final  String  GT_MSG_CONTENT_START_SERVICE_TO_CUST_URL = "voice://com.yanjing.voice/native?name=order_center&index=1&need_login=1";
	
	
	////////////////////////IM消息内容//////////////////////////
	//用户下单
	public  static final  String  IM_MSG_CONTENT_RECEIVE_ORDER_TO_DV = "您收到一个新的订单，请尽快确认";
	public  static final  String  IM_MSG_CONTENT_RECEIVE_ORDER_TO_CUST = "订单已收到，声优会尽快确认";
	
	//订单取消
	public  static final  String  IM_MSG_CONTENT_CANCEL_ORDER_TO_DV = "用户取消了您的订单，再接再厉哦~";
	public  static final  String  IM_MSG_CONTENT_CANCEL_ORDER_TO_CUST = "您取消了一个订单，音符已退回轻音账户";
	
	
	//订单被强制取消
	public  static final  String  IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_DV = "订单强制取消，有疑问请联系客服";
	public  static final  String  IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_CUST = "订单强制取消，有疑问请联系客服";
//	public  static final  String  IM_MSG_CONTENT_USER_CONFIRM_FINISH= "订单完成啦";
	
	//用户同意声优立即服务
	public  static final  String  IM_MSG_CONTENT_USER_CONFIRM_START_SERVICE_TO_DAV = "用户已同意立即服务，赶快聊聊吧";
	public  static final  String  IM_MSG_CONTENT_USER_CONFIRM_START_SERVICE_TO_CUST = "您已同意立即服务";
	
	
	public  static final  String  IM_MSG_CONTENT_USER_CONFIRM_START_SERVICE_JX_TO_DAV = "用户已经同意，请准时提供服务哦~";
	public  static final  String  IM_MSG_CONTENT_USER_CONFIRM_START_SERVICE_JX_TO_CUST = "您已同意，准时为您服务!";
	
	//拒绝接单
	public  static final  String  IM_MSG_CONTENT_DAV_REFUSE_TO_CUST = "声优未接单，音符已退回轻音账户";
	public  static final  String  IM_MSG_CONTENT_DAV_REFUSE_TO_DV = "您已拒绝，订单已取消~";
	
	
	//同意接单
	public  static final  String  IM_MSG_CONTENT_DAV_CONFIRM_TO_CUST = "声优已接单，麦克风调试中~";
	public  static final  String  IM_MSG_CONTENT_DAV_CONFIRM_TO_CUST_JIAO_XING = "声优已接单，正在记录中..";
	public  static final  String  IM_MSG_CONTENT_DAV_CONFIRM_TO_DAV = "您已接单，请在5分钟内申请立即服务";
	
	public  static final  String  IM_MSG_CONTENT_DAV_CONFIRM_OTHER_CANCEL_TO_CUST = "声优未接单，音符已退回轻音账户";
	public  static final  String  IM_MSG_CONTENT_DAV_CONFIRM_OTHER_CANCEL_TO_DAV = "您已选择其他订单，该订单已取消";
	
	
	//声优发起立即服务
	public  static final  String  IM_MSG_CONTENT_DAV_START_SERVICE_TO_CUST = "声优申请立即服务，请您确认~";
	public  static final  String  IM_MSG_CONTENT_DAV_START_SERVICE_TO_DAV = "您已申请立即服务，等待用户同意";
	
	
	
	//声优在服务时间内发起完成服务
	public  static final  String  IM_MSG_CONTENT_CUST_FINISH_TO_DAV = "您已申请完成订单，等待用户确认";
	public  static final  String  IM_MSG_CONTENT_CUST_FINISH_TO_CUST = "声优申请完成订单，请您确认~";
	
	
	//声优在服务时间之外发起完成服务
	public  static final  String  IM_MSG_CONTENT_CUST_NOT_PING_JIA_TO_DV = "订单已完成，快让用户对您本次服务进行评价吧~";
	public  static final  String  IM_MSG_CONTENT_CUST_NOT_PING_JIA_TO_CUST = "订单已完成，给个评价吧";
	
	//用户同意声优服务完成
	public  static final  String  IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_DV = "订单已完成，赫兹进入轻音账户";
	public  static final  String  IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_CUST = "订单已完成，给个评价吧";
	

	
	
	public  static final  String  IM_MSG_CONTENT_PING_JIA_FINISH_TO_CUST = "评级已完成，感谢您对我们的支持！";
	public  static final  String  IM_MSG_CONTENT_PING_JIA_FINISH_TO_DV = "用户已完成评价，再接再厉哦~";
	
	
//	public  static final  String  IM_MSG_CONTENT_DAV_FINISH = "大V完成服务啦";
	
	
	
	public  static final  String  IM_MSG_CONTENT_CANCEL_DV_15MINUTE_TIMEOUT = "您因超时未接单，订单已取消";
	public  static final  String  IM_MSG_CONTENT_CANCEL_CUST_15MINUTE_TIMEOUT = "声优未接单，音符已退回轻音账户";
	
//	/**大V5分钟未发起服务*/
	public  static final  String  IM_MSG_CONTENT_CANCEL_CUST_5MINUTE_START_TIMEOUT = "声优未发起立即服务，订单已取消~";
//	/**大V5分钟未发起服务*/
	public  static final  String  IM_MSG_CONTENT_CANCEL_DV_5MINUTE_START_TIMEOUT = "超时未申请立即服务，订单已取消~";
	
	
	/**用户5分钟未接*/
	public  static final  String  IM_MSG_CONTENT_CANCEL_CUST_5MINUTE_CONFIRM_TIMEOUT = "您未同意立即服务，订单已取消~";
	/**用户5分钟未接*/
	public  static final  String  IM_MSG_CONTENT_CANCEL_DV_5MINUTE_CONFIRM_TIMEOUT = "用户未同意立即服务，订单已取消~";
	/**12小时自动完成*/
	public  static final  String  IM_MSG_CONTENT_SYSTEM_FINISH_TIMEOUT_TO_DAV = "订单已完成，赫兹已进入轻音账户";
	/**12小时自动完成*/
	public  static final  String  IM_MSG_CONTENT_SYSTEM_FINISH_TIMEOUT_TO_CUST = "订单已完成，给个评价吧~";
	
	
	/**服务完成*/
	public  static final  String  IM_MSG_CONTENT_CANCEL_CUST_FINISH = "您有新完成的消息";
	public  static final  String  IM_MSG_CONTENT_DEFAULT = "您有新短消息";
	
	
	
	
	
	
	
	
	

	////////////////////////IM消息内容//////////////////////////
	
	
	/**下单返回结果：1 成功*/
	public static final String  RET_CODE_SUCCESS = "1";
	/**下单返回结果：2 余额不足*/
	public static final String  RET_CODE_BALANCE_NOT_ENOUTH = "2";
	/**下单返回结果：3 大V正忙*/
	public static final String  RET_CODE_DV_BUSY = "3";
	/**下单返回结果：4 系统错误*/
	public static final String  RET_CODE_SYSTEM_ERROR = "4";
	/**下单返回结果：5 大V正忙*/
	public static final String  RET_CODE_DV_NOT_ACCEPTE_ORDER = "5";
	/**下单返回结果：6 预约时间不正确*/
	public static final String  RET_CODE_APPOINT_TIME_ERROR = "6";
	/**下单返回结果：6 预约时间不正确*/
	public static final String  RET_CODE_APPOINT_TIME_ERROR_MSG = "下单时间必须大于预约时间30分钟";
	
	/**默认空字符串*/
	public static final Integer  DEFAULT_NULL_STR = 0;
	
	/**半小时秒数：1800秒*/
	public  static final  Integer  HALF_HOUR_SECONDS = 1800;
	/**一小时秒数：3600秒*/
	public  static final  Integer  ONE_HOUR_SECONDS = 3600;
	/**服务单位：一小时*/
	public  static final  String  SERVICE_UNIT_ONE_HOUR = "小时";
	/**服务单位：一小时*/
	public  static final  String  SERVICE_UNIT_HOUR = "一小时";
	/**服务单位：半小时*/
	public  static final  String  SERVICE_UNIT_HALF_HOUR = "半小时";
	/**服务单位：次*/
	public  static final  String  SERVICE_UNIT_TIMES = "次";
	
	
	
	/**默认每页显示条数*/
	public static  final  Integer  DEFAULT_PAGE_SIZE = 6;
    
	/**产品状态1：可用*/
	public  static final  Integer  PRODUCT_STATUS_EFFECTIVE = 1 ;
	/**产品状态0：不可用*/
	public static final  Integer  PRODUCT_STATUS_UN_EFFECTIVE = 0 ;
	
	/**请求类型：申请退款*/
	public static final Integer  REQUEST_REFUND_TYPE_REFUND = 1 ;
	/**请求类型：申请完成*/
	public static final Integer  REQUEST_REFUND_TYPE_FINISH = 2 ;
	
	
	/**请求类型：同意*/
	public static final Integer  REQUEST_CONFIRM_TYPE_YES = 1 ;
	/**请求类型：拒绝*/
	public static final Integer  REQUEST_CONFIRM_TYPE_NO = 2 ;
	
	
	/**大V请求类型：同意*/
	public static final Integer  REQUEST_DV_CONFIRM_TYPE_YES = 1 ;
	/**大V请求类型：拒绝*/
	public static final Integer  REQUEST_DV_CONFIRM_TYPE_NO = 2 ;
	
	
	/**用户请求完成服务类型：1 */
	public static final Integer  REQUEST_DV_FINISH_TYPE = 1 ;
	/**大V请求完成服务类型：2 */
	public static final Integer  REQUEST_CUST_FINISH_TYPE = 2 ;
	
	
	/**订单详情请求类型1：客户端*/
	public static final Integer  REQUEST_TYPE_CUST = 1 ;
	/**订单详情请求类型2：服务端*/
	public static final Integer  REQUEST_TYPE_DV = 2 ;
	
	
	/**技能类型类型1：不可重复接单*/
	public static final Integer  SKILL_TYPE_YES = 1 ;
	/**技能类型类型2：可重复接单*/
	public static final Integer  SKILL_TYPE_NO = 2 ;
	
	
	
	/**订单状态1.全部;   */
	public static final Integer  ORDER_STATUS_PARAM_TOTAL = 1 ;
	/**订单状态 2：待接单;   */
	public static final Integer  ORDER_STATUS_PARAM_WAITING_RECEIVE_ORDER = 2 ;
	/**订单状态3：待开始;   */
	public static final Integer  ORDER_STATUS_PARAM_WAITING_START = 3 ;
	/**订单状态 4：进行中;   */
	public static final Integer  ORDER_STATUS_PARAM_GOING = 4 ;
	/**订单状态 5：已完成;   */
	public static final Integer  ORDER_STATUS_PARAM_FINISHED = 5 ;
	/**订单状态6.已拒绝    */
	public static final Integer  ORDER_STATUS_PARAM_REFUSED = 6 ;
	/**订单状态7.已取消;   */
	public static final Integer  ORDER_STATUS_PARAM_CANCEL = 7 ;
	/**订单状态 8：待评价   */
	public static final Integer  ORDER_STATUS_PARAM_PING_JIA = 8 ;
	
	
	
	
	
	
	
	
	
	
	
	/**订单状态2.待接单;   */
	public static final Integer  ORDER_STATUS_WAITING_RECEIVE = 2 ;
	/**订单状态4.取消（用户下单后自主取消）*/
	public static final Integer  ORDER_STATUS_CANCEL_BEFORE_RECEIVE = 4 ;//
	/**订单状态6.取消（待接单-大V超时未接）*/
	public static final Integer  ORDER_STATUS_CANCEL_SYSTEM_NOT_RECEIVE = 6 ;//
	/**订单状态8.已拒绝（大V拒绝接单）*/
	public static final Integer  ORDER_STATUS_DAV_REFUSED_RECEIVE = 8 ;
	/**订单状态10.待开始（大V接单）;*/
	public static final Integer  ORDER_STATUS_WAITING_START = 10 ;//
	/**订单状态12.取消（大V接单，大V同一时间其它订单取消）;*/
	public static final Integer  ORDER_STATUS_CANCEL_DAV_START_ONE_ORDER = 12 ;
	/**订单状态14.取消（大V接单后用户自主取消）;*/
	public static final Integer  ORDER_STATUS_CANCEL_BEFORE_DAV_START = 14 ;
	/**订单状态16.取消（待开始5分钟大V未发起开始服务）*/
	public static final Integer  ORDER_STATUS_CANCEL_NOT_START = 16 ;
	/**订单状态18.待开始(大V发起开始服务)*/
	public static final Integer  ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE = 18 ;
	/**订单状态20.取消（大V发起开始服务用户自主取消）;*/
	public static final Integer  ORDER_STATUS_CANCLE_USER_SELF_BEFORE_SERVICE = 20 ;
	/**订单状态22.取消（大V发起开始服务用户5分钟未接）/叫醒服务预约时间;*/
	public static final Integer  ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT = 22 ;
	/**订单状态23.取消（叫醒预约时间之前取消）*/
	public static final Integer  ORDER_STATUS_CANCEL_BEFORE_APPOINT_TIME = 23 ;
	/**订单状态24.待开始（大V发起开始服务用户5分钟内同意，叫醒特享）;*/
	public static final Integer  ORDER_STATUS_GOING_WAITING_START = 24 ;
	/**订单状态26.进行中（大V发起开始服务用户5分钟内同意）;*/
	public static final Integer  ORDER_STATUS_GOING_USER_ACCEPCT = 26 ;
	
	/**订单状态28.进行中（大V发起完成服务）*/
	public static final Integer  ORDER_STATUS_GOING_DAV_APPAY_FINISH = 28 ;
	/**订单状态29.已取消（强制取消）*/
	public static final Integer  ORDER_STATUS_CANCEL_FORCE = 29 ;
	/**订单状态30.已完成（用户同意对方）*/
	public static final Integer  ORDER_STATUS_FINISHED_USER_ACCEPCT = 30 ;
	/**订单状态31.已完成，用户未评价;*/
	public static final Integer  ORDER_STATUS_GOING_USER_NOT_PING_JIA = 31 ;
	/**订单状态32.已完成（大V发起已完成服务，12小时客户不响应自动完成）*/
	public static final Integer  ORDER_STATUS_FINISH_DV_FINISH = 32 ;
	/**订单状态33.已完成（大V发起在服务时间内发起完成服务，到服务时间释放大V）*/
	public static final Integer  ORDER_STATUS_FINISH_DV_RELEASE = 33 ;
	/**订单状态34.已完成（用户发起完成服务）*/
	public static final Integer  ORDER_STATUS_GOING_USRE_APPAY_FINISH = 34 ;
	/**订单状态36.已完成（大V在服务时间外完成）;*/
	public static final Integer  ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME = 36 ;
	/**订单状态38.已完成（订单开始12小时系统自动完成）*/
	public static final Integer  ORDER_STATUS_FINISH_BOTH_NO_OPERATE = 38 ;
	/**订单状态40.已完成（用户评价完成）*/
	public static final Integer  ORDER_STATUS_FINISHED_AND_PINGJIA = 40 ;
	/**订单状态42.已完成（强制完成）*/
	public static final Integer  ORDER_STATUS_FINISHED_FORCE = 42 ;
	
	
	
//	/**订单状态18.订单完成（正常完成）;*/
//	public static final Integer  ORDER_STATUS_END = 18 ;
	
	
	
	
	
	
//	大V声音审核状态1：待审核（审核中） 2：审核拒绝   3.审核通过   4.修改之后待审核   5.修改之后审核拒绝    6.修改之后审核通过
	
	//////////////大V声音状态/////////////////
	/**大V声音状态1：未录制*/
	public static final  Integer  VOICE_STATUS_UNEXIST = 1;
	/**大V声音状态2：待审核（审核中）*/
	public static final  Integer  VOICE_STATUS_UN_APPROVE = 2;
	/**大V声音状态3：审核拒绝*/
	public static final  Integer  VOICE_STATUS_APPROVE_REFUSED = 3;
	/**大V声音状态4：审核通过*/
	public static final  Integer  VOICE_STATUS_APPROVE_PASS = 4;
	
	//////////////////////////////订单用券状态///////////////////////////////////////////
	/**订单用券状态0：不使用*/
	public static final  Integer  ORDER_COUPON_FLAG_DEFAULT = 0;
	/**订单用券状态1：使用券*/
	public static final  Integer  ORDER_COUPON_FLAG_USE = 1;
	/**订单用券状态2：使用券订单取消*/
	public static final  Integer  ORDER_COUPON_FLAG_CANCEL = 2;
	
	
	
	
	
}
