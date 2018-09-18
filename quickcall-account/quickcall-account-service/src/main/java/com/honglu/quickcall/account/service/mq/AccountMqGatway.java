package com.honglu.quickcall.account.service.mq;

import com.honglu.quickcall.account.facade.constants.AccountPayMqBusinessType;
import com.honglu.quickcall.account.facade.constants.MQtype;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by len.song on 2017-12-25.
 */
@Service("accountMqGatway")
public class AccountMqGatway {
    private final static Logger logger = LoggerFactory.getLogger(AccountMqGatway.class);

    @Resource
    private AmqpTemplate amqpTemplate;
    @Resource
    private RabbitTemplate rmqpTemplate;

    private void sendDataToCrQueue(String queueKey, Object obj) {
        amqpTemplate.convertAndSend(queueKey, obj);
    }

    @Test
    public void sendTest(String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "我是一条测试数据，我是发起者...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        data.put("sendMqTime", sdf.format(new Date()));
        data.put("mqtype", AccountPayMqBusinessType.MQ_TEST);
        send("accountCenter-mq-exchange", "queue_accountCenter_pay_key", data);
    }

    /**
     * 将充值信息插入MQ
     *
     * @param userId
     * @param orderNo
     */
    public void rechargeContent(Integer userId, String orderNo) {
        logger.info("充值成功后，开始发送mq消息...用户编号为：" + userId + "，订单编号为：" + orderNo);
        Map<String, Object> map = new HashMap<>();
        map.put("sendMqTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("userId", userId);
        map.put("rechargeId", orderNo);
        map.put("mqtype", AccountPayMqBusinessType.MQ_RECHARGE_SUCCESS);
        send("accountCenter-mq-exchange", "queue_accountCenter_pay_key", map);
        logger.info("用户编号为：" + userId + "的用户充值成功后，发送mq消息成功...");
    }

    /**
     * 充值 完发送MQ执行 "充值就送 / 首充"
     * @param money
     * @param persionId
     */
    public void sendRechargeAlwaysRewardMQ(Double money, Integer persionId){
        logger.info("充值成功后，开始发送mq消息...用户编号为:{},金额为:{}" + persionId + money);
        Map<String, Object> queueMap = new HashMap<String, Object>();
        queueMap.put("presonId", persionId);
        queueMap.put("money", money);
        queueMap.put("mqtype", MQtype.MQ_RECHARGE_ALWAYS_REWARD);
        send("activity-mq-exchange", "queue_activity_key", queueMap);
        logger.info("mq发送成功");
    }


    public void sendActivityMessage(Integer persionId, Double token , Double money){
        //异常捕捉，活动失败不影响充值主逻辑
        try {
            String type = JedisUtil.get(RedisKeyConstants.RECHARGE_ACTIVITY + persionId);
            if (StringUtils.isNotBlank(type)) {
                JedisUtil.del(RedisKeyConstants.RECHARGE_ACTIVITY + persionId);
            }
            Map<String, Object> queueMap = new HashMap<String, Object>();
            queueMap.put("presonId", persionId);
            queueMap.put("type", type == null ? 0 : Integer.valueOf(type));
            queueMap.put("token", token );
            queueMap.put("money", money);
            queueMap.put("mqtype", MQtype.MQ_TYPE_ACTIVITY);
            send("activity-mq-exchange", "queue_activity_key", queueMap);
        } catch (Exception e) {
            logger.error("发送活动消息失败，方法：sendActivityMessage，失败原因：" + e.getMessage());
        }
    }



    /**
     * 转账成功消息
     *
     * @param map
     */
    public void batchTransferSend(Map<String, Object> map) {
        map.put("mqtype", AccountPayMqBusinessType.MQ_BATCHTRANSFER_SUCCESS);
        send("accountCenter-mq-exchange", "queue_batchTransfeer_key", map);
    }

    private void send(String exchange, String routingKey, Object message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }


}
