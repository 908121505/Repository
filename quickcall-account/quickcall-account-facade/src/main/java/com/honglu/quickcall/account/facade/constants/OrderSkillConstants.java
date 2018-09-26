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
	
	
	/**大V请求退款类型：同意*/
	public static final Integer  REQUEST_DV_REFUND_TYPE_YES = 1 ;
	/**大V请求退款类型：拒绝*/
	public static final Integer  REQUEST_DV_REFUND_TYPE_NO = 2 ;
	
	
	/**订单详情请求类型1：客户端*/
	public static final Integer  REQUEST_TYPE_CUST = 1 ;
	/**订单详情请求类型2：服务端*/
	public static final Integer  REQUEST_TYPE_DV = 2 ;
	
	
	
	/**订单状态1.下单未支付（已下单，未支付）; */
	public static final Integer  ORDER_STATUS_NOT_PAY = 1 ;
	/**订单状态2.下单未支付系统取消（已下单，30分钟内未支付）*/
	public static final Integer  ORDER_STATUS_CANCEL_NOT_PAY = 2 ;//
	/**订单状态3.已支付（已下单，支付完成）*/
	public static final Integer  ORDER_STATUS_PAYED = 3 ;
	/**订单状态4.超时未接（已支付，大V30分钟内未接单）*/
	public static final Integer  ORDER_STATUS_CANCEL_PAYED_DV_RECEIVE_OVER_TIME = 4 ;//
	/**订单状态5.大V接单前用户自主取消;*/
	public static final Integer  ORDER_STATUS_CANCEL_PAYED_USER_SELE_CANCEL = 5 ;
	/**订单状态6.大V拒绝订单（支付完成，大V拒绝，客户退款）;*/
	public static final Integer  ORDER_STATUS_PAYED_DV_REFUSE = 6 ;
	/**订单状态7.大V接受订单;*/
	public static final Integer  ORDER_STATUS_PAYED_DV_ACCEPT_ORDER = 7 ;
	/**订单状态8.大V接单后用户取消订单（用户退款）;*/
	public static final Integer  ORDER_STATUS_CANCLE_DV_ACCEPT_USER_SELF_CANCLE = 8 ;
	/**订单状态9.大V确认开始订单;*/
	public static final Integer  ORDER_STATUS_PAYED_DV_CONFIRM_START = 9 ;
	/**订单状态10.用户同意（响应大V立即服务请求）;*/
	public static final Integer  ORDER_STATUS_CUST_AGREE_DV_START_SERVICE = 10 ;
	/**订单状态11.用户拒绝（响应大V立即服务请求）;*/
	public static final Integer  ORDER_STATUS_CUST_REFUSE_DV_START_SERVICE = 11 ;
	/**订单状态12.进行中（用户同意或者到约定的订单开始时间）;*/
	public static final Integer  ORDER_STATUS_GOINGING = 12 ;
	/**订单状态13.用户申请退款;*/
	public static final Integer  ORDER_STATUS_USER_APPLAY_REFUND = 13 ;
	/**订单状态14.订单作废（大V同意退款，客户退款）;*/
	public static final Integer  ORDER_STATUS_CANCEL_DV_AGREE_REFUND = 14 ;
	/**订单状态15.订单完成（大V拒绝退款）;*/
	public static final Integer  ORDER_STATUS_END_DV_REFUSE = 15 ;
	/**订单状态16.订单完成（正常完成）;*/
	public static final Integer  ORDER_STATUS_END = 16 ;
	/**订单状态17.支付之前取消;*/
	public static final Integer  ORDER_STATUS_CANCEL_BEFORE_PAY = 17 ;
	
	
}
