package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.user.facade.entity.BigvScore;
import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.facade.exchange.mqrequest.EvaluationOrderMqRequest;
import com.honglu.quickcall.user.service.constants.ScoreRankConstants;
import com.honglu.quickcall.user.service.dao.BigvScoreMapper;
import com.honglu.quickcall.user.service.dao.BigvSkillScoreMapper;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.service.ScoreRankService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private BigvScoreMapper bigvScoreMapper;
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

        if (order.getValueScore() != null) {
            LOGGER.warn("客户下单消费 -- 更新主播评分排名表 -- 该订单已计算过评分：" + order.getOrderId());
            return;
        }

        // 计算该订单对应的技能的评分
        BigDecimal score = calculateOrderSkillScore(order, ScoreRankConstants.DEFAULT_EVALUATION_LEVEL);

        // 更新订单价值得分
        bigvSkillScoreMapper.updateValueScoreToOrder(order.getOrderId(), score);

        // 更新评分到大V技能评分表和总评分排名表
        updateToBigvScore(order.getServiceId(), order.getSkillItemId(), order.getCustomerSkillId(), score);

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

        if (order.getValueScore() != null) {
            LOGGER.info("客户评价订单 -- 更新主播评分排名表 -- 订单已计算过评分：" + order.getOrderId());

            // 计算出默认得分的总权重值
            BigDecimal defaultScore = calculateEvaluateScore(ScoreRankConstants.DEFAULT_EVALUATION_LEVEL);

            // 评价得分
            BigDecimal evaluationScore = order.getValueScore().divide(defaultScore, 5, BigDecimal.ROUND_HALF_EVEN)
                    .multiply(calculateEvaluateScore(order.getEvaluateStart()));

            // 更新评分到大V技能评分表和总评分排名表
            updateToBigvScore(order.getServiceId(), order.getSkillItemId(), order.getCustomerSkillId(), evaluationScore.subtract(order.getValueScore()));
        } else {
            // 计算该订单对应的技能的评分
            BigDecimal score = calculateOrderSkillScore(order, order.getEvaluateStart());

            // 更新订单价值得分
            bigvSkillScoreMapper.updateValueScoreToOrder(order.getOrderId(), score);

            // 更新评分到大V技能评分表和总评分排名表
            updateToBigvScore(order.getServiceId(), order.getSkillItemId(), order.getCustomerSkillId(), score);
        }
    }

    /**
     * 计算该笔订单的评价值
     *
     * @param order
     * @param evaluateStars
     * @return
     * @计算公式：完成一笔价值评价=[（log(100,该技能累计订单数)+6)*10*平台笔数权重+笔单价*平台笔单价权重]*(评价*评价权重*平台价值权重)
     * @总排名：个人总价值=所有单个技能累计价值之和
     */
    private BigDecimal calculateOrderSkillScore(Order order, Integer evaluateStars) {
        // 查询该用户该技能的订单笔数
        Integer orderTotal = bigvSkillScoreMapper.selectBigvSkillOrderTotal(order.getServiceId(), order.getSkillItemId());
        orderTotal = orderTotal == null ? 0 : orderTotal;

        // 计算技能总比价得分
        BigDecimal orderTotalScore = new BigDecimal((Objects.equals(orderTotal, 1) ? 0 : (2 / Math.log10(orderTotal)) + 6)
                * 10 * ScoreRankConstants.PLATFORM_ORDER_NUM_TOTAL_WEIGHT);

        // 计算技能笔单价得分
        BigDecimal servicePriceScore = order.getServicePrice().multiply(new BigDecimal(ScoreRankConstants.PLATFORM_SINGLE_ORDER_PRICE_WEIGHT));

        // 计算总得分
        BigDecimal valueScore = orderTotalScore.add(servicePriceScore).multiply(calculateEvaluateScore(evaluateStars));

        return valueScore;
    }

    /**
     * 更新评分到大V技能评分表和总评分排名表
     *
     * @param customerId
     * @param skillItemId
     * @param score
     */
    private void updateToBigvScore(Long customerId, Long skillItemId, Long customerSkillId, BigDecimal score) {
        // 存入技能排名表
        if (bigvSkillScoreMapper.updateBigvSkillScore(customerSkillId, score) == 0) {
            // 更新失败则插入
            BigvSkillScore bigvSkillScore = new BigvSkillScore();
            bigvSkillScore.setId(UUIDUtils.getId());
            bigvSkillScore.setCustomerId(customerId);
            bigvSkillScore.setSkillItemId(skillItemId);
            bigvSkillScore.setCustomerSkillId(customerSkillId);
            bigvSkillScore.setOrderTotal(1);
            bigvSkillScore.setScoreTotal(score);
            bigvSkillScoreMapper.insert(bigvSkillScore);
        }

        // 存入大V排名表
        if (bigvScoreMapper.updateBigvScore(customerId, score) == 0) {
            // 更新失败则插入
            BigvScore bigvScore = new BigvScore();
            bigvScore.setId(UUIDUtils.getId());
            bigvScore.setCustomerId(customerId);
            bigvScore.setOrderTotal(1);
            bigvScore.setScoreTotal(score);
            bigvScoreMapper.insert(bigvScore);
        }
    }

    /**
     * 计算评价得分
     *
     * @param evaluateStars
     * @return
     */
    private static BigDecimal calculateEvaluateScore(Integer evaluateStars) {
        if(evaluateStars == null){
            evaluateStars = 0;
        }
        return new BigDecimal(evaluateStars
                * ScoreRankConstants.EVALUATION_LEVEL_WEIGHT_MAP.get(evaluateStars)
                * ScoreRankConstants.PLATFORM_ORDER_EVALUATION_WEIGHT);
    }

}
