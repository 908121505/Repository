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
    
    

    //系统相关 001300 - 001350
    String MarketOnOff = "001300";
    String AppConfig = "001301";

  //查看用户信息
    String PersonInfo =  "0011128";

}
