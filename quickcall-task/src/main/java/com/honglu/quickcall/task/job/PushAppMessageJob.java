package com.honglu.quickcall.task.job;

import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.user.facade.business.UserPushAppMsgBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class PushAppMessageJob {

    public static final Logger LOGGER = LoggerFactory.getLogger(PushAppMessageJob.class);

    @Autowired
    private UserPushAppMsgBusiness userPushAppMsgBusiness;

    private final static String JOB_SERIAL_MESSAGE_KEY = "job_serial_message_key";

    @Scheduled(cron = "0 5 7 * * ?")
    public void send() {
        if (JedisUtil.setnx(JOB_SERIAL_MESSAGE_KEY, JOB_SERIAL_MESSAGE_KEY, 300) == 0) {
            LOGGER.info("==任务已经执行，当前任务被正常拒绝==");
            return;
        }
        LOGGER.info("-----------定时job开始执行-----------");
        try {
//			activityCenterService.excute(params);
        } catch (Exception e) {
            LOGGER.error("job执行发生异常，异常信息：", e);
        }
        LOGGER.info("=============定时job结束=================");
    }


}
