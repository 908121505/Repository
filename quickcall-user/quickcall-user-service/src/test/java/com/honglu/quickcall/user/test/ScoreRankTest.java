package com.honglu.quickcall.user.test;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.user.facade.constants.ScoreRankConstants;

import java.math.BigDecimal;

/**
 * 订单得分测试
 *
 * @author duanjun
 * @date 2018-11-03 17:55
 */
public class ScoreRankTest {

    public static void main(String[] args) {
        BigDecimal totalScore = new BigDecimal(0);

        Order order = new Order();
        order.setOrderNum(1);
        order.setEvaluateStart(5);
        order.setServicePrice(new BigDecimal("30"));
        totalScore = totalScore.add(caclScore(0, order));

        order = new Order();
        order.setOrderNum(1);
        order.setEvaluateStart(5);
        order.setServicePrice(new BigDecimal("30"));
        totalScore = totalScore.add(caclScore(1, order));

        order = new Order();
        order.setOrderNum(1);
        order.setEvaluateStart(5);
        order.setServicePrice(new BigDecimal("30"));
        totalScore = totalScore.add(caclScore(2, order));

        order = new Order();
        order.setOrderNum(20);
        order.setEvaluateStart(5);
        order.setServicePrice(new BigDecimal("30"));
        totalScore = totalScore.add(caclScore(3, order));

        order = new Order();
        order.setOrderNum(1);
        order.setEvaluateStart(3);
        order.setServicePrice(new BigDecimal("30"));
        totalScore = totalScore.add(caclScore(4, order));


        System.out.println("totalScore == " + totalScore);
    }

    private static BigDecimal caclScore(Integer orderTotal, Order order){
        Integer evaluateStars = order.getEvaluateStart();

        // 查询该用户该技能的订单笔数

        // 计算技能总比价得分
        BigDecimal orderTotalScore = new BigDecimal((2 / Math.log10(orderTotal + 6))
                * 10 * ScoreRankConstants.PLATFORM_ORDER_NUM_TOTAL_WEIGHT);

        // 计算技能笔单价得分
        BigDecimal servicePriceScore = order.getServicePrice().multiply(new BigDecimal(ScoreRankConstants.PLATFORM_SINGLE_ORDER_PRICE_WEIGHT));

        BigDecimal ev = calculateEvaluateScore(evaluateStars, order.getOrderNum());
        System.out.println("评价得分权重值：" + ev);
        // 计算总得分
        BigDecimal valueScore = orderTotalScore.add(servicePriceScore).multiply(ev);
        valueScore = valueScore.setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println("总得分：" + valueScore);

        order.setValueScore(valueScore);
        order.setEvaluateStart(0);
        // 完成一笔价值评价=[（log(100,该技能累计订单数+6))*10*平台笔数权重+笔单价*平台笔单价权重]*(评价*评价权重*平台价值权重)

        // 计算出默认得分的总权重值
        BigDecimal defaultScore = calculateEvaluateScore(ScoreRankConstants.DEFAULT_EVALUATION_LEVEL, order.getOrderNum());
        System.out.println("默认评价得分权重值：" + defaultScore);

        BigDecimal ess = calculateEvaluateScore(order.getEvaluateStart(), order.getOrderNum());
        System.out.println("实际评价得分权重值：" + ess);
        // 评价得分
        BigDecimal evaluationScore = order.getValueScore().divide(defaultScore, 5, BigDecimal.ROUND_HALF_EVEN).multiply(ess);

        System.out.println("实际评价得分：" + evaluationScore);
        // 补差取整
        BigDecimal bucha = evaluationScore.subtract(order.getValueScore()).setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println("补差：" + bucha);
        return valueScore;
    }

    /**
     * 计算评价得分
     *
     * @param evaluateStars
     * @param orderNum
     * @return
     * @desc (评价 * 评价权重 * 平台价值权重)
     */
    private static BigDecimal calculateEvaluateScore(Integer evaluateStars, Integer orderNum) {
        if (evaluateStars == null) {
            evaluateStars = 0;
        }
        return new BigDecimal(ScoreRankConstants.EVALUATION_LEVEL_WEIGHT_MAP.get(evaluateStars)
                * ScoreRankConstants.getSingleOrderNumWeight(orderNum)
                * ScoreRankConstants.PLATFORM_ORDER_EVALUATION_WEIGHT);
    }
}
