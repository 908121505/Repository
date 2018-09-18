package com.honglu.quickcall.common.api.util;

/**
 * Created by wlj
 */
public class RedisKeyConstants {

    //系统参数
    public static final String SYS_PARAM = "pp_sys:param:";
    //提现限制
    public static final String WITHDRAW = "user:withdraw:";
    //是否为活动充值的key
    public static final String RECHARGE_ACTIVITY = "user:recharge_activity:";

    //用户的open_id
    public static final String USER_OPEN_ID_NX = "user:open_id";
    //用户的心跳
    public static final String HEARTBEAT = "user:heartbeat";
    //用户心跳离线标识
    public static final String HEART_TO_ONLINE = "user:heartBeatToOnline";
    //用户验证码
    public static final String USER_VERIFYCODE = "user:verifycode:";
    //用户验证码一分钟获取次数
    public static final String USER_VERIFYCODE_M = "user:verifycode:m:";
    //用户验证码一小时钟获取次数
    public static final String USER_VERIFYCODE_H = "user:verifycode:h:";
    //用户验证码一天获取次数
    public static final String USER_VERIFYCODE_D = "user:verifycode:d:";
    //用户信息
    public static final String USER_INFO_NX = "user:infonx:";
    //用户修改资料步骤
    public static final String USER_EDIT_STEP = "user:editStep:";
    
    //送礼活动，银蛋
    public static final String GIFT_ACTIVITY_SILVERY = "user:gift:silvery:";
   //送礼活动，金蛋
    public static final String GIFT_ACTIVITY_GOLD = "user:gift:gold:";
    //送礼活动，钻石蛋
    public static final String GIFT_ACTIVITY_EWEL = "user:gift:ewel:";
    //心动女神 分享
    public static final String SHARE_ACTIVITY="user:share:";
    
    //心动女神畅聊包
    public static final String CHAR_BAG_ONE="user:chatbag:one:";
    //畅聊包
    public static final String CHAR_BAG="user:chatbag:";
    
    //充值优惠限时抢活动
    //优惠分享累计次数
    public static final String GRAB_SHARE_COUNT="user:grabShare:count:";
    //当天分享有效
    public static final String GRAB_SHARE_ONE="user:grabShare:one:";
    //是否能抢的标记
    public static final String EXISTS_QUICK_GRAB="user:quickGrab:";
    //时间记录标记
    public static final String EXISTS_TIME_GRAB="user:time:grab";
    //充值分享优惠使用标记
    public static final String GRAB_SHAR_USE="user:quickGrab:use:";
    
    //迎接财神榜第一的用户ID
    public static final String GOD_LIST_TOP_ONE="user:godList:topOne";
    //迎接财神榜第一的消费金额
    public static final String GOD_LIST_TOP_TOKENS="user:godList:topTokens";

    // 用户注册时放入redis的缓存，用于后面判断用户是否存在
    public static final String USER_REGISTER_EXIST = "user:exist:";

    // 首页女神数据缓存
    public static final String USER_HOMEPAGE_LIST = "user:homepage:";

    //消费批次号标识
    public static final String CONSUME_BATCH_NO = "consume:batchNo";

    //用户的缓存状态
    public static final String USER_IMSTATE_CACHE = "user:imstate_cache";
    
    //男性新用户排行榜打招呼屏蔽已打招呼对象
    public static final String USER_RANK_SHIELD = "user:rankShield:";
    
    
    //魅力周榜
    public static final String CHARISMATIC_WEEK_LIST="user:charismaticWeekList";
    //魅力日榜
    public static final String CHARISMATIC_DAY_LIST="user:charismaticDayList";
    //富豪周榜
    public static final String RICH_WEEK_LIST="user:richWeekList"; 
    //富豪日榜
    public static final String RICH_DAY_LIST="user:richDayList"; 
    //新人榜
    public static final String NEW_ROOKIE_LIST="user:newRookieList"; 
    //魅力周榜 我的排名
    public static final String MY_CHARISMATIC_WEEK="user:mycharismaticWeek:";
    //魅力日榜 我的排名
    public static final String MY_CHARISMATIC_DAY="user:mycharismaticDay:";
    //富豪周榜 我的排名
    public static final String MY_RICH_WEEK="user:myrichWeek:"; 
    //富豪日榜 我的排名
    public static final String MY_RICH_DAY="user:myrichDay:"; 
    //新人榜 我的排名
    public static final String MY_NEW_ROOKIE="user:mynewRookie:"; 
    
    

}
