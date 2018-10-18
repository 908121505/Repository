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
    public static final  String  QUERY_SKILL_NAME_FOR_FIRST_PAGE = "19004";
    /**首页分类下的大V列表*/
    public static final  String  QUERY_DV_LIST_BY_TYPE = "19005";
    
    
    
    /**查询主播可用产品*/
    public static final  String  ORDER_DAV_PRODUCT_LIST = "19011";
    /**用户下单*/
    public static final  String  ORDER_SAVE = "19012";
    
    
    
    /**查询收到的订单*/
    public static final  String  ORDER_RECEIVE_ORDER_LIST = "19021";
    /**查询发起的订单*/
    public static final  String  ORDER_SEND_ORDER_LIST = "19022";
    
    
    
    /**客户支付订单*/
    public static final  String  CUST_PAY_ORDER = "19031";
    /**客户再来一单*/
    public static final  String  CUST_COPY_ORDER = "19032";
    /**客户申请退款/完成*/
    public static final  String  CUST_APPLAY_REFUND = "19033";
    /**客户同意/拒绝订单*/
    public static final  String  CUST_CONFIRM_ORDER = "19034";
    /**订单详情*/
    public static final  String  DETAIL_ORDER = "19035";
    /**订单取消*/
    public static final  String  CANCEL_ORDER = "19036";
    
    
    
    /**大V确认订单*/
    public static final  String  DV_RECEIVE_ORDER = "19041";
    /**大V开始服务*/
    public static final  String  DV_START_SERVICE = "19042";
    /**大V同意退款/拒绝*/
    public static final  String  DV_CONFRIM_ORDER = "19043";
    
    
    /**查询进行中订单数量*/
    public static final  String  QUERY_ING_ORDER_COUNT = "19051";
    /**查询退款原因*/
    public static final  String  QUERY_REFUND_REASON = "19052";
    
    
}
