package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.facade.exchange.mqrequest.EvaluationOrderMqRequest;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.service.ScoreRankService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 刷新评分排名表服务接口实现类
 *
 * @author duanjun
 * @date 2018-10-24 19:48
 */
@Service
public class ScoreRankServiceImpl implements ScoreRankService {
    private final static Logger LOGGER = Logger.getLogger(ScoreRankServiceImpl.class);

    /**
     * 更新评分排名分布式锁
     */
    private static final String SCORE_RANK_DISTRIBUTED_KEY = "distributed_lock:SCORE_RANK_CUSTOMER_ID_";

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void doOrderCast(DoOrderCastMqRequest request) {
        LOGGER.info("客户下单消费 -- 更新主播评分排名表：" + request.getOrderId());

        // 查询客户下的订单
        Order order = customerMapper.selectCustomerOrder(request.getOrderId());
        if (order == null) {
            LOGGER.warn("客户下单消费 -- 更新主播评分排名表 -- 未查询到订单，订单ID：" + request.getOrderId());
            return;
        }

        // 判断服务是否完成
        if (!ifDoneOrder(order.getOrderStatus())) {
            LOGGER.warn("客户下单消费 -- 更新主播评分排名表 -- 订单未完成，订单状态：" + order.getOrderStatus());
            return;
        }

        // 查询该用户该技能的订单笔数 -- ADUAN -- 待查询
        int orderTotal = 1;

        // 计算该订单对应的技能的评分
        BigDecimal score = calculateOrderSkillScore(orderTotal, order.getServicePrice());

        // 更新评分到大V技能评分表和总评分排名表
        updateToBigvScore(order.getServiceId(), order.getSkillItemId(), score);

    }

    @Override
    public void evaluationOrder(EvaluationOrderMqRequest request) {
        LOGGER.info("客户评价订单 -- 更新主播评分排名表：" + request.getOrderId());

        // 查询客户下的订单
        Order order = customerMapper.selectCustomerOrder(request.getOrderId());
        if (order == null) {
            LOGGER.warn("客户评价订单 -- 更新主播评分排名表 -- 未查询到订单，订单ID：" + request.getOrderId());
            return;
        }

    }

    /**
     * 计算该笔订单的评价值
     *
     * @param orderTotal
     * @param servicePrice
     * @return
     */
    private BigDecimal calculateOrderSkillScore(int orderTotal, BigDecimal servicePrice) {
        // ADUAN -- 计算规则待完成


        return new BigDecimal(100);
    }

    /**
     * 更新评分到大V技能评分表和总评分排名表
     *
     * @param serviceId
     * @param skillItemId
     * @param score
     */
    private void updateToBigvScore(Long serviceId, Long skillItemId, BigDecimal score) {


    }

    /**
     * 判断订单是否完成状态
     *
     * @param orderStatus
     * @return
     */
    private boolean ifDoneOrder(Integer orderStatus) {
        return Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT) // 30.已完成（用户同意对方）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISH_DV_FINISH) // 32.已完成（大V发起已完成服务，12小时客户不响应自动完成）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_GOING_USRE_APPAY_FINISH) // 34.已完成（用户发起完成服务）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME) // 36.已完成（大V在服务时间外完成）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISH_BOTH_NO_OPERATE) // 38.已完成（双方12小时内未发起完成服务）
                || Objects.equals(orderStatus, OrderSkillConstants.ORDER_STATUS_FINISHED_AND_PINGJIA); // 40.已完成（用户评价完成）
    }

    /**
     * 获取锁
     *
     * @param lockKey
     * @return
     */
    private boolean lock(String lockKey) {
        try {
            Jedis jedis = JedisUtil.getJedisPool().getResource();

            // 重试5次
            int tryNum = 5;
            while (tryNum-- > 0) {
                if (jedis.setnx(lockKey, "1") == 1) {
                    return true;
                }
                Thread.sleep(100);// 等待100毫秒
            }
            return false;
        } catch (Exception e) {
            LOGGER.error("更新大V评分排名获取锁异常：", e);
            return false;
        }
    }

    /**
     * 释放锁
     *
     * @param lockKey
     */
    private void unlock(String lockKey) {
        JedisUtil.del(lockKey);
    }

}
