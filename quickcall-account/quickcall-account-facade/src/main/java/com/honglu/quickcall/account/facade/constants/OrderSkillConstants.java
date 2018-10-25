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
	
	
	/**1：订单不存在，可以下单 */
	public  static final  Integer  IM_RETCODE_CAN_ORDER = 1;
	/** 2：订单不存在 大V在忙*/
	public  static final  Integer  IM_RETCODE_DV_BUSY = 2;
	/** 3：订单存在*/
	public  static final  Integer  IM_RETCODE_ORDER_EXIST = 3;
	
	
	
	
	
	////////////////////////IM消息内容//////////////////////////
	public  static final  String  IM_MSG_CONTENT_RECEIVE_ORDER = "有人下单啦";
	public  static final  String  IM_MSG_CONTENT_CANCEL_ORDER = "订单取消啦";
	public  static final  String  IM_MSG_CONTENT_USER_CONFIRM_FINISH= "订单完成啦";
	public  static final  String  IM_MSG_CONTENT_USER_CONFIRM_START_SERVICE = "用户同意开始服务啦";
	public  static final  String  IM_MSG_CONTENT_DAV_REFUSE = "大V接单啦";
	public  static final  String  IM_MSG_CONTENT_DAV_CONFIRM = "大V拒绝接单啦";
	public  static final  String  IM_MSG_CONTENT_DAV_START_SERVICE = "大V开始服务啦";
	public  static final  String  IM_MSG_CONTENT_CUST_FINISH = "用户完成服务啦";
	public  static final  String  IM_MSG_CONTENT_DAV_FINISH = "大V完成服务啦";

	////////////////////////IM消息内容//////////////////////////
	
	
	/**下单返回结果：1 成功*/
	public static final String  RET_CODE_SUCCESS = "1";
	/**下单返回结果：2 余额不足*/
	public static final String  RET_CODE_BALANCE_NOT_ENOUTH = "2";
	/**下单返回结果：3 大V正忙*/
	public static final String  RET_CODE_DV_BUSY = "3";
	/**下单返回结果：4 系统错误*/
	public static final String  RET_CODE_SYSTEM_ERROR = "4";
	
	/**默认空字符串*/
	public static final String  DEFAULT_NULL_STR = "";
	
	/**半小时秒数：1800秒*/
	public  static final  Integer  HALF_HOUR_SECONDS = 1800;
	/**一小时秒数：3600秒*/
	public  static final  Integer  ONE_HOUR_SECONDS = 3600;
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
	/**订单状态22.取消（大V发起开始服务用户5分钟未接）;*/
	public static final Integer  ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT = 22 ;
	/**订单状态24.待开始（大V发起开始服务用户5分钟内同意，叫醒特享）;*/
	public static final Integer  ORDER_STATUS_GOING_WAITING_START = 24 ;
	/**订单状态26.进行中（大V发起开始服务用户5分钟内同意）;*/
	public static final Integer  ORDER_STATUS_GOING_USER_ACCEPCT = 26 ;
	/**订单状态28.进行中（大V发起完成服务）*/
	public static final Integer  ORDER_STATUS_GOING_DAV_APPAY_FINISH = 28 ;
	/**订单状态30.已完成（用户同意对方）*/
	public static final Integer  ORDER_STATUS_FINISHED_USER_ACCEPCT = 30 ;
	/**订单状态32.已完成（大V发起已完成服务，12小时客户不响应自动完成）*/
	public static final Integer  ORDER_STATUS_FINISH_DV_FINISH = 32 ;
	/**订单状态34.已完成（用户发起完成服务）*/
	public static final Integer  ORDER_STATUS_GOING_USRE_APPAY_FINISH = 34 ;
	/**订单状态36.已完成（大V在服务时间外完成）;*/
	public static final Integer  ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME = 36 ;
	/**订单状态38.已完成（订单开始12小时系统自动完成）*/
	public static final Integer  ORDER_STATUS_FINISH_BOTH_NO_OPERATE = 38 ;
	/**订单状态40.已完成（用户评价完成）*/
	public static final Integer  ORDER_STATUS_FINISHED_AND_PINGJIA = 40 ;
	
	
	
	/**订单状态18.订单完成（正常完成）;*/
	public static final Integer  ORDER_STATUS_END = 18 ;
	
	
	
	
	
	
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
	
	
}
