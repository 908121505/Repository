package com.honglu.quickcall.account.facade.code;


/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单相关接口类型区分
 * @Package: com.honglu.quickcall.account.facade.code 
 * @author: chenliuguang   
 * @date: 2018年9月23日 上午10:26:43
 */
public interface OrderRequestType {

	/**查询个人技能（产品）*/
    public static final  String  QUERY_SKILL_INFO = "19001";
    /**修改个人技能（产品）*/
    public static final  String  UPDATE_SKILL_INFO = "19002";
    /**首页查询大V列表*/
    public static final  String  QUERY_ORDER_FOR_FIRST_PAGE = "19003";
    /**首页查询项目分类*/
    public static final  String  QUERY_SKILL_NAME_FOR_FIRST_PAGE = "19003";
}
