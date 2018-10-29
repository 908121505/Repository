package com.honglu.quickcall.databury.util;

import com.alibaba.fastjson.JSON;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by len.song on 2018-05-02.
 */
public class BuryiedPointUtil {
    private static final Logger logger = LoggerFactory.getLogger(BuryiedPointUtil.class);
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 开始埋点
     * @param data
     */
    public static void buryData(Map<String, Object> data){
        try {

            logger.info("埋点传参为：{}", JSON.toJSONString(data));
            if(data == null){
                logger.error("神策埋点异常,数据为空");
                return;
            }

            SensorsAnalytics sensorsAnalytics = null;
            String url = PropertiesUtil.get("sc.receive.url");

            logger.info("神策上传url为:{}",url);

            if(StringUtils.isEmpty(url)){
                logger.error("神策埋点异常,数据存储url异常");
                return;
            }
            String path = PropertiesUtil.get("sc.cache.path");

            if(StringUtils.isEmpty(path)){
                logger.error("神策埋点异常,数据存储path异常");
            }

            if("true".equals(PropertiesUtil.get("isOnlin"))){
                sensorsAnalytics = new SensorsAnalytics(new SensorsAnalytics.ConcurrentLoggingConsumer(path));
                logger.info("==========ConcurrentLoggingConsumer==========");
            }else{
                sensorsAnalytics = new SensorsAnalytics(new SensorsAnalytics.DebugConsumer(url, true));
                logger.info("==========DebugConsumer==========");
            }

            //设置用户Id
            String userId = "";
            boolean isReal = true;
            // 虚拟userId
            String virUserId = (String)data.get("virUserId");
            // 真实userId
            String realUserId = (String)data.get("realUserId");

            if(StringUtils.isEmpty(virUserId) && StringUtils.isEmpty(realUserId)){
                logger.error("神策埋点异常，虚拟userId和真实的userId都未空");
                return;
            }

            // 用户变量
            Map<String, Object> user = (Map<String, Object>) data.get("user");
            // 事件变量
            Map<String, Object> event = (Map<String, Object>) data.get("event");
            // 事件Key
            String key = (String) data.get("key");

            if (StringUtils.isNotEmpty(virUserId) && StringUtils.isEmpty(realUserId)) {
                userId = "" + virUserId;
                isReal = false;
            } else {
                userId = "" + realUserId;
                if (StringUtils.isNotEmpty(virUserId) && StringUtils.isNotEmpty(realUserId)) {
                    sensorsAnalytics.trackSignUp("" + realUserId, "" + virUserId);
                }
            }

            if(!CollectionUtils.isEmpty(user)){
                setUserInfo(isReal,userId,user,sensorsAnalytics);
            }
            logger.info("===========埋点属性设置完成===========");
            event.put("create_time", new SimpleDateFormat(TIME_PATTERN).format(new Date()));
            sensorsAnalytics.track(userId, isReal, key, event);
            sensorsAnalytics.shutdown();

            logger.info("============================埋点成功============================");

        } catch (Exception e) {
            logger.error("生成神策对象异常,异常信息为:{}"+e.getMessage(),e);
        }
    }



    /**
     * 设置用户属性
     *
     * @param isReal
     * @param userId
     * @param user
     * @param sa
     * @return
     * @throws InvalidArgumentException
     */
    private static void  setUserInfo(boolean isReal, String userId, Map<String, Object> user, final SensorsAnalytics sa) throws InvalidArgumentException {
        //==========================一次性设置的属性(开始)=========================
        //手机号
        String mobile_num = (String) user.get("phoneNumber");
        if (!StringUtils.isEmpty(mobile_num)) {
            sa.profileSetOnce(userId, isReal, "phoneNumber", mobile_num);
            logger.info("==========user -> phoneNum：" + mobile_num + "==========");
        }


        //用户id
        String gj_user_id = (String) user.get("gj_user_id");
        if (!StringUtils.isEmpty(gj_user_id)) {
            sa.profileSetOnce(userId, isReal, "gj_user_id", mobile_num);
            logger.info("==========user -> gj_user_id：" + gj_user_id + "==========");
        }

        //性别
        String gender = (String) user.get("gender");
        if (!StringUtils.isEmpty(gender)) {
            sa.profileSetOnce(userId, isReal, "gender", gender);
            logger.info("==========user -> gender：" + gender + "==========");
        }

        //生日
        String yearOfBirth = (String) user.get("yearOfBirth");
        if (!StringUtils.isEmpty(yearOfBirth)) {
            sa.profileSetOnce(userId, isReal, "yearOfBirth", yearOfBirth);
            logger.info("==========user -> yearOfBirth：" + yearOfBirth + "==========");
        }

        //注册时间
        String registDate = (String) user.get("registDate");
        if (!StringUtils.isEmpty(registDate)) {
            sa.profileSetOnce(userId, isReal, "registDate", registDate);
            logger.info("==========user -> registDate：" + registDate + "==========");
        }

        //注册渠道
        String registSource = (String) user.get("registSource");
        if (!StringUtils.isEmpty(registSource)) {
            sa.profileSetOnce(userId, isReal, "registSource", registSource);
            logger.info("==========user -> registSource：" + registSource + "==========");
        }

        //==========================一次性设置的属性(结束)=========================



        //==========================变化的属性设置(开始)=========================
        //变化的属性
        String nick = (String) user.get("nick");
        if(!StringUtils.isEmpty(nick)){
            sa.profileSet(userId, isReal, "nick", nick);
            logger.info("==========user -> nick：" + nick + "==========");
        }

        //==========================变化的属性设置(结束)=========================
    }
}
