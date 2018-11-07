package com.honglu.quickcall.databury.core.utils;

import com.alibaba.fastjson.JSON;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class BuryiedPointUtil {
    private static final Logger logger = LoggerFactory.getLogger(BuryiedPointUtil.class);
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Value("${sc.receive.url}")
    private String url;

    @Value("${sc.cache.path}")
    private String path;

    @Value("${isOnlin}")
    private String isOnlin;

    /**
     * 开始埋点
     * @param data
     */
    public void buryData(Map<String, Object> data){
        try {
            logger.info("=======开始神策埋点=======");
            logger.info("-------埋点传参为：{}", JSON.toJSONString(data));
            if(data == null){
                logger.error("神策埋点异常,数据为空");
                return;
            }

            SensorsAnalytics sensorsAnalytics = null;
            logger.info("-------神策上传url为:{}",url);
            if(StringUtils.isEmpty(url)){
                logger.error("-------神策埋点异常,数据存储url异常");
                return;
            }
            if(StringUtils.isEmpty(path)){
                logger.error("-------神策埋点异常,数据存储path异常");
            }

            if("true".equals(isOnlin)){
                sensorsAnalytics = new SensorsAnalytics(new SensorsAnalytics.ConcurrentLoggingConsumer(path));
                logger.info("-------isOnlin:"+isOnlin);
            }else{
                sensorsAnalytics = new SensorsAnalytics(new SensorsAnalytics.DebugConsumer(url, true));
                logger.info("-------isOnlin:"+isOnlin);
            }

            //设置用户Id
            String userId = "";
            boolean isReal = true;
            // 虚拟userId
            String virUserId = (String)data.get("virUserId");
            // 真实userId
            String realUserId = (String)data.get("realUserId");

            if(StringUtils.isEmpty(virUserId) && StringUtils.isEmpty(realUserId)){
                logger.error("-------神策埋点异常，虚拟userId和真实的userId都未空");
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

            //开始埋属性数据
            if(!CollectionUtils.isEmpty(user)){
                buryAttrInfo(isReal,userId,user,sensorsAnalytics);
            }
            logger.info("-------属性埋点完成，开始事件埋点-------");

            event.put("create_time", new SimpleDateFormat(TIME_PATTERN).format(new Date()));
            //埋事件
            sensorsAnalytics.track(userId, isReal, key, event);
            //关闭
            sensorsAnalytics.shutdown();

            logger.info("=======神策埋点完成=======");

        } catch (Exception e) {
            logger.error("生成神策对象异常,异常信息为:{}"+e.getMessage(),e);
        }
    }


    /**
     * 埋属性
     *
     * @param isReal
     * @param distinctId
     * @param user
     * @param sa
     * @return
     * @throws InvalidArgumentException
     */
    private void buryAttrInfo(boolean isReal, String distinctId, Map<String, Object> user, final SensorsAnalytics sa) throws InvalidArgumentException {
        logger.info("-------开始属性埋点-------");

        logger.info("*******神策埋点用户唯一标识-->distinctId="+distinctId+"*******");

        //用户id
        String userId = (String) user.get("vc_user_id");
        if (!StringUtils.isEmpty(userId)) {
            sa.profileSetOnce(distinctId, isReal, "vc_user_id", userId);
            logger.info("属性设置:用户id -> vc_user_id：" + userId );
        }

        //手机号
        String phoneNumber = (String) user.get("phoneNumber");
        if (!StringUtils.isEmpty(phoneNumber)) {
            sa.profileSetOnce(distinctId, isReal, "phoneNumber", phoneNumber);
            logger.info("属性设置:用户手机号 -> phoneNumber：" + phoneNumber );
        }

        //注册渠道
        String registSource = (String) user.get("registSource");
        if (!StringUtils.isEmpty(registSource)) {
            sa.profileSetOnce(distinctId, isReal, "registSource", registSource);
            logger.info("属性设置:注册渠道 -> registSource：" + registSource );
        }

        //注册时间
        String registDate = (String) user.get("registDate");
        if (!StringUtils.isEmpty(registDate)) {
            sa.profileSetOnce(distinctId, isReal, "registDate", registDate);
            logger.info("属性设置:注册时间 -> registDate：" + registDate );
        }

        //用户设备
        String userEquipment = (String) user.get("userEquipment");
        if(!StringUtils.isEmpty(userEquipment)){
            sa.profileSet(distinctId, isReal, "User_equipment", userEquipment);
            logger.info("属性设置:用户设备 -> User_equipment：" + userEquipment );
        }

        //性别
        String gender = (String) user.get("gender");
        if (!StringUtils.isEmpty(gender)) {
            sa.profileSet(distinctId, isReal, "gender", gender);
            logger.info("属性设置:性别 -> gender：" + gender );
        }

        //生日
        String yearOfBirth = (String) user.get("yearOfBirth");
        if (!StringUtils.isEmpty(yearOfBirth)) {
            sa.profileSet(distinctId, isReal, "yearOfBirth", yearOfBirth);
            logger.info("属性设置:生日 -> yearOfBirth：" + yearOfBirth );
        }

        //昵称
        String nickname = (String) user.get("nickname");
        if(!StringUtils.isEmpty(nickname)){
            sa.profileSet(distinctId, isReal, "nickname", nickname);
            logger.info("属性设置:昵称 -> nickname：" + nickname );
        }

        //昵称
        String nick = (String) user.get("nickname");
        if(!StringUtils.isEmpty(nick)){
            sa.profileSet(distinctId, isReal, "nick", nick);
            logger.info("属性设置:昵称 -> nick：" + nick );
        }

        //粉丝量
        String vermicelli = (String) user.get("vermicelli");
        if(!StringUtils.isEmpty(vermicelli)){
            sa.profileSet(distinctId, isReal, "vermicelli", vermicelli);
            logger.info("属性设置:粉丝量 -> vermicelli：" + vermicelli );
        }

        //关注数
        String numberOfCencerns = (String) user.get("numberOfCencerns");
        if(!StringUtils.isEmpty(numberOfCencerns)){
            sa.profileSet(distinctId, isReal, "Number_of_concerns", numberOfCencerns);
            logger.info("属性设置:关注数 -> numberOfCencerns：" + numberOfCencerns );
        }

        //用户身份
        String userIdentity = (String) user.get("userIdentity");
        if(!StringUtils.isEmpty(userIdentity)){
            sa.profileSet(distinctId, isReal, "User_identity", userIdentity);
            logger.info("属性设置:用户身份 -> userIdentity：" + userIdentity );
        }

        logger.info("-------属性埋点结束-------");
    }
}
