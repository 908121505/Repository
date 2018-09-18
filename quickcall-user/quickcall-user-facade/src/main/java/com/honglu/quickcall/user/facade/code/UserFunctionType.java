package com.honglu.quickcall.user.facade.code;

/**
 * Created by len.song on 2017-12-07.
 * 只用来存放rpc功能点code
 * 组成：项目编码(MyServiceCode.USER.code()) + "变量"(从 001 - 500 范围)
 */
public interface UserFunctionType {

    //检查手机号
    String CheckPhone =  "001001";
    

    //系统相关 001300 - 001350
    String MarketOnOff = "001300";
    String AppConfig = "001301";



}
