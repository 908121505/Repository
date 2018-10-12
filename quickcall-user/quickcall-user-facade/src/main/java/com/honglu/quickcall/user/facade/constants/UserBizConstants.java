package com.honglu.quickcall.user.facade.constants;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：user系统常用常量
 * @Package: com.honglu.quickcall.user.facade.constants 
 * @author: chenliuguang   
 * @date: 2018年10月9日 下午4:09:06
 */
public interface UserBizConstants {
    
	
	/**关注状态 0：未关注*/
	public  static final  Integer   ATTENTION_STATUS_UN_ATTENED = 0 ;
	/**关注状态 1：关注*/
	public  static final  Integer   ATTENTION_STATUS_ATTENED = 1 ;
	
	
	
	
	/**查询类型1：查询关注列表*/
	public static final   Integer   QUERY_ATTENTION_LIST_TYPE = 1 ;
	/**查询类型2：查询粉丝列表*/
	public static final   Integer   QUERY_FANS_LIST_TYPE = 2 ;
	
	
	/**关注/取消类型1：关注*/
	public static final   Integer   ATTENTION_TYPE_ADD = 1 ;
	/**关注/取消类型2：取消*/
	public static final   Integer   ATTENTION_TYPE_CANCEL = 2 ;
	
	
	
	/**声音上传请求类型*/
	public static final   String   UPLOAD_AUDIT_REQUEST = "MYVOICE" ;




}
