package com.honglu.quickcall.user.service.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.user.facade.constants.ScoreRankConstants;
import com.honglu.quickcall.user.facade.entity.BigvScore;
import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import com.honglu.quickcall.user.facade.exchange.mqrequest.DoOrderCastMqRequest;
import com.honglu.quickcall.user.facade.exchange.mqrequest.EvaluationOrderMqRequest;
import com.honglu.quickcall.user.service.dao.BigvScoreMapper;
import com.honglu.quickcall.user.service.dao.BigvSkillScoreMapper;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.service.ScoreRankService;

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
        BigDecimal score = ScoreRankConstants.calculateOrderSkillScore(
                bigvSkillScoreMapper.selectBigvSkillOrderTotal(order.getCustomerSkillId()),
                order.getServicePrice(),
                order.getOrderNum(),
                order.getCouponFlag(),
                ScoreRankConstants.DEFAULT_EVALUATION_LEVEL);

        // 更新订单价值得分
        bigvSkillScoreMapper.updateValueScoreToOrder(order.getOrderId(), score);

        // 更新评分到大V技能评分表和总评分排名表
        updateToBigvScore(order.getServiceId(), order.getSkillItemId(), order.getCustomerSkillId(), score, 1);

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
            BigDecimal defaultScore = ScoreRankConstants.calculateEvaluateScore(ScoreRankConstants.DEFAULT_EVALUATION_LEVEL, order.getOrderNum());

            // 评价得分
            BigDecimal evaluationScore = order.getValueScore().divide(defaultScore, 5, BigDecimal.ROUND_HALF_EVEN)
                    .multiply(ScoreRankConstants.calculateEvaluateScore(order.getEvaluateStart(), order.getOrderNum()));

            // 更新订单价值得分
            bigvSkillScoreMapper.updateValueScoreToOrder(order.getOrderId(), evaluationScore.setScale(0, BigDecimal.ROUND_HALF_UP));

            // 补差 -- 四舍五入取整
            BigDecimal updateScore = evaluationScore.subtract(order.getValueScore()).setScale(0, BigDecimal.ROUND_HALF_UP);

            // 更新评分到大V技能评分表和总评分排名表
            updateToBigvScore(order.getServiceId(), order.getSkillItemId(), order.getCustomerSkillId(), updateScore, 0);
        } else {
            // 计算该订单对应的技能的评分
            BigDecimal score = ScoreRankConstants.calculateOrderSkillScore(
                    bigvSkillScoreMapper.selectBigvSkillOrderTotal(order.getCustomerSkillId()),
                    order.getServicePrice(),
                    order.getOrderNum(),
                    order.getCouponFlag(),
                    order.getEvaluateStart());

            // 更新订单价值得分
            bigvSkillScoreMapper.updateValueScoreToOrder(order.getOrderId(), score);

            // 更新评分到大V技能评分表和总评分排名表
            updateToBigvScore(order.getServiceId(), order.getSkillItemId(), order.getCustomerSkillId(), score, 1);
        }
    }


    /**
     * 更新评分到大V技能评分表和总评分排名表
     *
     * @param customerId
     * @param skillItemId
     * @param score
     * @param addOrderTotal 累加订单的笔数
     */
    private void updateToBigvScore(Long customerId, Long skillItemId, Long customerSkillId, BigDecimal score, Integer addOrderTotal) {
        // 存入技能排名表
        if (bigvSkillScoreMapper.updateBigvSkillScore(customerSkillId, score, addOrderTotal) == 0) {
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
        if (bigvScoreMapper.updateBigvScore(customerId, score, addOrderTotal) == 0) {
            // 更新失败则插入
            BigvScore bigvScore = new BigvScore();
            bigvScore.setId(UUIDUtils.getId());
            bigvScore.setCustomerId(customerId);
            bigvScore.setOrderTotal(1);
            bigvScore.setScoreTotal(score);
            bigvScoreMapper.insert(bigvScore);
        }
    }


    @Override
    public CommonResponse initBigvScoreRankData() {
        LOGGER.info("初始化声优声量评分排名数据 ----------- start");
        // 删除不是大V列表的排名数据
        int deleteNum = bigvScoreMapper.deleteNotBigvData();
        LOGGER.info("从【bigv_score】表删除不是大V的数量条数：" + deleteNum);

        // 删除不在大V技能列表的排名数据
        deleteNum = bigvSkillScoreMapper.deleteNotBigvData();
        LOGGER.info("从【bigv_skill_score】表删除不是大V技能表中的数据条数：" + deleteNum);

        // 查询不在大V总排名表中的大VID
        List<Long> customerIds = bigvScoreMapper.selectNeedInsertBigvData();
        LOGGER.info("插入【bigv_score】表差的数据条数：" + customerIds.size());
        if (!customerIds.isEmpty()) {
            BigvScore bigScore;
            for (Long customerId : customerIds) {
                bigScore = new BigvScore();
                bigScore.setId(UUIDUtils.getId());
                bigScore.setCustomerId(customerId);
                bigScore.setOrderTotal(0);
                bigScore.setScoreTotal(new BigDecimal(0));
                bigvScoreMapper.insert(bigScore);
            }
        }

        // 查询不在大V技能排名表的大V技能数据
        List<BigvSkillScore> bigvSkillScores = bigvSkillScoreMapper.selectNeedInsertBigvData();
        LOGGER.info("插入【bigv_skill_score】表差的数据条数：" + customerIds.size());
        if (!bigvSkillScores.isEmpty()) {
            BigvSkillScore bigvSkillScore;
            for (BigvSkillScore skillScore : bigvSkillScores) {
                bigvSkillScore = new BigvSkillScore();
                bigvSkillScore.setId(UUIDUtils.getId());
                bigvSkillScore.setCustomerId(skillScore.getCustomerId());
                bigvSkillScore.setCustomerSkillId(skillScore.getCustomerSkillId());
                bigvSkillScore.setSkillItemId(skillScore.getSkillItemId());
                bigvSkillScore.setOrderTotal(0);
                bigvSkillScore.setScoreTotal(new BigDecimal(0));
                bigvSkillScoreMapper.insert(bigvSkillScore);
            }
        }

        Long lastId = null;
        // 循环处理大V技能排名表数据
        while (true) {
            // 查询大V技能数据
            BigvSkillScore bigvSkillScore = bigvSkillScoreMapper.selectOneData(lastId);
            // 若查询不到数据 -- 代表已处理完成
            if (bigvSkillScore == null) {
                break;
            }
            lastId = bigvSkillScore.getId();

            // 判断当前客户是否为大V
            if (customerMapper.judgeCustomerIsBigv(bigvSkillScore.getCustomerId()) == 0) {
                // 若不是大V -- 清楚技能排名表数据
                LOGGER.info("从【bigv_score 和 bigv_skill_score】删除不是大V的数据：" + bigvSkillScore.getCustomerId());
                bigvSkillScoreMapper.deleteDataByCustomerId(bigvSkillScore.getCustomerId());
                bigvScoreMapper.deleteDataByCustomerId(bigvSkillScore.getCustomerId());
            }

            // 声优该技能的总声量
            BigDecimal totalScore = new BigDecimal(0);
            // 查询声优该项技能已完成的订单
            List<Order> orderList = bigvScoreMapper.selectAllDoneOrderByCustomerSkillId(bigvSkillScore.getCustomerSkillId());
            // 循环计算声优该技能的累积得分值
            for (Order order : orderList) {
                // 计算该订单对应的技能的评分
                totalScore = totalScore.add(calculateOrderSkillScore2(order));
            }

            // 存入技能排名表
            LOGGER.info("更新表【bigv_skill_score】大V的技能排名："
                    + bigvSkillScore.getCustomerSkillId() + " - totalScore：" + totalScore + " - orderTotal：" + orderList.size());
            bigvSkillScoreMapper.updateBigvSkillScore2(bigvSkillScore.getCustomerSkillId(), totalScore, orderList.size());
        }

        // 更新大V声量排名表数据
        bigvScoreMapper.updateBigvScore2();

        LOGGER.info("初始化声优声量评分排名数据 ----------- end");
        return ResultUtils.resultSuccess();
    }

    private BigDecimal calculateOrderSkillScore2(Order order) {
        // 订单累计数，暂时用字段 paymentType 接收
        Integer orderTotal = order.getPaymentType();
        if (order.getServicePrice() == null) {
            order.setServicePrice(new BigDecimal(0));
        }
        if (!Objects.equals(order.getCustomerIsEvaluate(), 1)) {
            order.setEvaluateStart(3);
        }
        if (order.getEvaluateStart() == null) {
            order.setEvaluateStart(0);
        }

        // 计算技能总比价得分
        BigDecimal orderTotalScore = new BigDecimal((2 / Math.log10(orderTotal + 6))
                * 10 * ScoreRankConstants.PLATFORM_ORDER_NUM_TOTAL_WEIGHT);
        // 计算技能笔单价得分
        BigDecimal servicePriceScore = order.getServicePrice().multiply(
                new BigDecimal(ScoreRankConstants.PLATFORM_SINGLE_ORDER_PRICE_WEIGHT));

        // 计算总得分
        BigDecimal valueScore = orderTotalScore.add(servicePriceScore).multiply(
                ScoreRankConstants.calculateEvaluateScore(order.getEvaluateStart(), order.getOrderNum()));

        // 四舍五入取整
        return valueScore.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

}
