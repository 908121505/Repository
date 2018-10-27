package com.honglu.quickcall.activity.facade.code;


/**
 * Created by len.song on 2017-12-07.
 * 只用来存放rpc功能点code
 * 组成：项目编码(MyServiceCode.ACTIVITY.code()) + "变量"(从 001 - 500 范围)
 */
public interface ActivityFunctionType {

    // 从 "005001" 开始， 005保持不变  从 001 开始递增;

    /**
     * Banner查询接口
     */
    String QUERY_BANNER = "005001";
}
