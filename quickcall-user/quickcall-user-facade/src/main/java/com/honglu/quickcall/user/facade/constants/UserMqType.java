package com.honglu.quickcall.user.facade.constants;

/**
 * Created by len.song on 2018-02-01.
 */
public interface UserMqType {

    //活动mq(开始)
    //砸金蛋
    int MQ_TYPE_ACTIVITY_EGGS = 2201;


    //普通mq(开始)

    //首页缓存
    int  MQ_HOMEPAGE_CACHE_SUCCESS = 2101;
    //用户完成注册
    int  MQ_AFTER_REGISTER = 2102;
}
