package com.honglu.quickcall.task.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.task.dao.TaskMapper;
import com.honglu.quickcall.task.entity.Order;
import com.honglu.quickcall.task.utils.GtPushUtil;
import com.honglu.quickcall.user.facade.enums.PushAppMsgTypeEnum;

/**
 * 推送APP消息异步任务
 *
 * @author duanjun
 * @date 2018-09-24 15:31
 */
@Component
public class PushAppMessageJob {

    public static final Logger LOGGER = LoggerFactory.getLogger(PushAppMessageJob.class);

    @Autowired
    private TaskMapper taskMapper;

    private final static String JOB_SERIAL_MESSAGE_KEY = "job_serial_message_key";

    // ADUAN -- 砍需求，job发消息暂时不上
//    @Scheduled(cron = "5 * * * * ?")
    public void send() {
        if (JedisUtil.setnx(JOB_SERIAL_MESSAGE_KEY, JOB_SERIAL_MESSAGE_KEY, 10) == 0) {
            LOGGER.info("==任务已经执行，当前任务被正常拒绝==");
            return;
        }
        LOGGER.info("=============推送消息定时job开始=================");
        try {
            List<Order> list = taskMapper.queryWillBeStartMessage();
            LOGGER.info("推送APP消息跑批数据条数：" + list.size());
            if (list != null && list.size() > 0) {
                List<String> clientIdList = new ArrayList<>();
                for (Order order : list) {
                    String sellerDeviceId = taskMapper.getCustomerDeviceId(order.getSellerId());
                    String buyerDeviceId = taskMapper.getCustomerDeviceId(order.getBuyerId());
                    if (StringUtils.isNotBlank(sellerDeviceId)) {
                        clientIdList.add(sellerDeviceId);
                    }
                    if (StringUtils.isNotBlank(buyerDeviceId)) {
                        clientIdList.add(buyerDeviceId);
                    }
                    GtPushUtil.pushMessage(clientIdList, PushAppMsgTypeEnum.WILL_BE_START.getMsgContent(), "");
                }
            }
        } catch (Exception e) {
            LOGGER.error("job执行发生异常，异常信息：", e);
        }
        LOGGER.info("=============推送消息定时job结束=================");
    }

}
