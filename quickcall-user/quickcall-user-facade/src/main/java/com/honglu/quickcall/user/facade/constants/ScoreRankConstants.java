package com.honglu.quickcall.user.facade.constants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 评分排名计算规则 -- 配置的相关常亮
 *
 * @author duanjun
 * @date 2018-10-24 23:01
 */
public class ScoreRankConstants {
    /**
     * 默认订单评分
     */
    public static final Integer DEFAULT_EVALUATION_LEVEL = 3;

    /**
     * 平台笔数权重
     **/
    public static final Double PLATFORM_ORDER_NUM_TOTAL_WEIGHT = Double.valueOf(1);
    /**
     * 平台笔单价权重
     **/
    public static final Double PLATFORM_SINGLE_ORDER_PRICE_WEIGHT = Double.valueOf(0.2);
    /**
     * 平台价值权重
     **/
    public static final Double PLATFORM_ORDER_EVALUATION_WEIGHT = Double.valueOf(0.8);
    /**
     * 评分等级权重常量定义
     */
    public static Map<Integer, Double> EVALUATION_LEVEL_WEIGHT_MAP = new HashMap<>();
    /**
     * 单笔订单件数权重常量定义
     */
    private static Map<Integer, Double> SINGLE_ORDER_NUM_WEIGHT_MAP = new LinkedHashMap<>();

    static {
        /********************************* 评分等级权重配置 *********************************/
        EVALUATION_LEVEL_WEIGHT_MAP.put(0, 0.01);
        EVALUATION_LEVEL_WEIGHT_MAP.put(1, 0.05);
        EVALUATION_LEVEL_WEIGHT_MAP.put(2, 0.2);
        EVALUATION_LEVEL_WEIGHT_MAP.put(3, 0.5);
        EVALUATION_LEVEL_WEIGHT_MAP.put(4, 0.8);
        EVALUATION_LEVEL_WEIGHT_MAP.put(5, 1.0);

        /********************************* 单笔订单件数权重配置 *********************************/
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(1, 1.0);
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(5, 1.2);
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(10, 1.3);
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(20, 1.5);
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(50, 2.0);
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(100, 3.0);
    }

    /**
     * 根据订单件数获取单笔订单件数权重
     *
     * @param orderNo
     * @return
     */
    public static Double getSingleOrderNumWeight(Integer orderNo) {
        Integer index = 1;
        for (Integer key : SINGLE_ORDER_NUM_WEIGHT_MAP.keySet()) {
            if (key <= orderNo) {
                index = key;
            }
        }
        return SINGLE_ORDER_NUM_WEIGHT_MAP.get(index);
    }

    /**
     * 声量格式化的单位
     */
    private static final String[] unit = {"k", "m", "b"};
    /**
     * 声量格式化的分界线
     */
    private static final BigDecimal ONE_THOUSAND = new BigDecimal(1000);
    /**
     * 格式化技能声量
     *
     * @param score
     * @return
     */
    public static String formatSkillScore(BigDecimal score) {
        if (score == null) {
            return "0";
        }
        if(score.compareTo(new BigDecimal(10000)) < 0){
            return String.valueOf(score.setScale(0, BigDecimal.ROUND_DOWN));
        }
        int index = 0;
        do {
            score = score.divide(ONE_THOUSAND);
            index++;
        }while (score.compareTo(ONE_THOUSAND) >= 0 && index <= 2);
        index--;
        score = score.setScale(2, BigDecimal.ROUND_DOWN);
        return score + unit[index];
    }

    public static void main(String[] args) {
        System.out.println(formatSkillScore(new BigDecimal(0.54)));
        System.out.println(formatSkillScore(new BigDecimal(9999)));
        System.out.println(formatSkillScore(new BigDecimal(10000)));
        System.out.println(formatSkillScore(new BigDecimal("1000000000000")));
    }

    /**
     * 计算该笔订单的评价值
     *
     * @param orderTotal 技能订单总数
     * @param servicePrice 服务价格
     * @param orderNum 订单数量
     * @param couponFlag 使用优惠券标志
     * @param evaluateStars
     * @return
     * @计算公式：完成一笔价值评价=[（log(100,该技能累计订单数+6))*10*平台笔数权重+笔单价*平台笔单价权重]*(评价*评价权重*平台价值权重)
     * @总排名：个人总价值=所有单个技能累计价值之和
     */
    public static BigDecimal calculateOrderSkillScore(Integer orderTotal, BigDecimal servicePrice, Integer orderNum, Integer couponFlag, Integer evaluateStars) {
        // 查询该用户该技能的订单笔数
        orderTotal = orderTotal == null ? 0 : orderTotal;

        // 计算技能总比价得分
        BigDecimal orderTotalScore = new BigDecimal((2 / Math.log10(orderTotal + 6))
                * 10 * PLATFORM_ORDER_NUM_TOTAL_WEIGHT);

        // 计算技能笔单价得分
        BigDecimal servicePriceScore = servicePrice.multiply(new BigDecimal(PLATFORM_SINGLE_ORDER_PRICE_WEIGHT));

        // 计算总得分
        BigDecimal valueScore = orderTotalScore.add(servicePriceScore).multiply(calculateEvaluateScore(evaluateStars, orderNum));

        // 有抵扣券参与的订单时，在计算此次订单价值时需要额外*40，以此来提高免费服务声优的技能升级速度和平台资源位露出机会.
        if (Objects.equals(couponFlag, 1)) {
            valueScore = valueScore.multiply(new BigDecimal(40));
        }

        // 四舍五入取整
        return valueScore.setScale(0, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 计算评价得分
     *
     * @param evaluateStars
     * @param orderNum
     * @return
     * @desc (评价 * 评价权重 * 平台价值权重)
     */
    public static BigDecimal calculateEvaluateScore(Integer evaluateStars, Integer orderNum) {
        if (evaluateStars == null) {
            evaluateStars = 0;
        }
        return new BigDecimal(EVALUATION_LEVEL_WEIGHT_MAP.get(evaluateStars)
                * getSingleOrderNumWeight(orderNum)
                * PLATFORM_ORDER_EVALUATION_WEIGHT);
    }
}
