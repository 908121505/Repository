package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.facade.exchange.mqrequest.EvaluationOrderMqRequest;
import com.honglu.quickcall.user.service.constants.ScoreRankConstants;
import com.honglu.quickcall.user.service.dao.BigvSkillScoreMapper;
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
    @Autowired
    private BigvSkillScoreMapper bigvSkillScoreMapper;

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

        if (order.getValueScore() != null) {
            LOGGER.warn("客户下单消费 -- 更新主播评分排名表 -- 该订单已计算过评分：" + order.getOrderStatus());
            return;
        }

        // 计算该订单对应的技能的评分
        BigDecimal score = calculateOrderSkillScore(order);

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
     * @param order
     * @return
     * @计算公式：完成一笔价值评价=[（log(100,该技能累计订单数)+6)*10*平台笔数权重+笔单价*平台笔单价权重]*(评价*评价权重*平台价值权重)
     * @总排名：个人总价值=所有单个技能累计价值之和
     */
    private BigDecimal calculateOrderSkillScore(Order order) {
        // 判断是否计算过订单的价值
        // 查询该用户该技能的订单笔数
        Integer orderTotal = bigvSkillScoreMapper.selectBigvSkillOrderTotal(order.getServiceId(), order.getSkillItemId());
        orderTotal = orderTotal == null ? 0 : orderTotal;

        // 计算技能总比价得分
        BigDecimal orderTotalScore = new BigDecimal((Objects.equals(orderTotal, 1) ? 0 : (2 / Math.log10(orderTotal)) + 6)
                * 10 * ScoreRankConstants.PLATFORM_ORDER_NUM_TOTAL_WEIGHT);

        // 计算技能笔单价得分
        BigDecimal servicePriceScore = order.getServicePrice().multiply(new BigDecimal(ScoreRankConstants.PLATFORM_SINGLE_ORDER_PRICE_WEIGHT));

        // 计算默认评价得分 -- 默认评价3分
        BigDecimal evaluateNum = new BigDecimal(ScoreRankConstants.DEFAULT_EVALUATION_LEVEL
                * ScoreRankConstants.EVALUATION_LEVEL_WEIGHT_MAP.get(ScoreRankConstants.DEFAULT_EVALUATION_LEVEL) * ScoreRankConstants.PLATFORM_ORDER_EVALUATION_WEIGHT);

        // 计算总得分
        BigDecimal valueScore = orderTotalScore.add(servicePriceScore).multiply(evaluateNum);

        // 更新订单价值得分
        bigvSkillScoreMapper.updateValueScoreToOrder(order.getOrderId(), valueScore);
        order.setValueScore(valueScore);

        // 存入技能排名表
        if (bigvSkillScoreMapper.updateBigvSkillScore(order.getServiceId(), order.getSkillItemId(), order.getValueScore()) == 0) {
            // 更新失败则插入

        }

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
