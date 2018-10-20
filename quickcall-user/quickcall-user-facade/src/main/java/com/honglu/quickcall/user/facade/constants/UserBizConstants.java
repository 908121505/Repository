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
	
	
	//技能认证状态
	//技能认证中
	public static final Integer SKILL_CERTIFY_STATUS_AUDIT = 1 ;
	//技能认证通过
	public static final Integer SKILL_CERTIFY_STATUS_PASS = 2;
	//技能认证拒绝
	public static final Integer SKILL_CERTIFY_STATUS_REFUSED = 3;





}
