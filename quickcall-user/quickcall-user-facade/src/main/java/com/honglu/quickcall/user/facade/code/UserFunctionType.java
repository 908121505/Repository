package com.honglu.quickcall.user.facade.code;

/**
 * Created by len.song on 2017-12-07.
 * 只用来存放rpc功能点code
 * 组成：项目编码(MyServiceCode.USER.code()) + "变量"(从 001 - 500 范围)
 */
public interface UserFunctionType {

	 //检查是否注册
    String CheckPhone =  "001001";
    //注册
    String register="001002";
    //登录
    String login="001003";
    //设置、找回密码
    String setpwd="001004";
    //设置头像
    String setHeardUrl="001005";
    //发送短信
    String getSmsCode="001006";


    //系统相关 001300 - 001350
    String MarketOnOff = "001300";
    String AppConfig = "001301";

    /**
     * 推送APP消息
     */
    String PUSH_APP_MSG = "001310";

    //查看用户信息
    String PersonInfo =  "0011128";

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

    //保存昵称和头像
    String SaveNicknameImage = "0011120";
    //保存性别
    String SaveGender = "0011121";
    //保存签名
  String SaveSignName = "0011125";
  //保存生日
  String SaveBirthday = "0011122";
  //保存兴趣
  String SaveInterest = "0011123";
  //保存职业
  String SaveOccupation = "0011124";
  //大V主页，普通用户主页（客态）
  String ShowHomePageLogout = "0011126";
      
}
