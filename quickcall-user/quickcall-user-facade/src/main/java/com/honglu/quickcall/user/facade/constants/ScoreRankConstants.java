package com.honglu.quickcall.user.facade.constants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 评分排名计算规则 -- 配置的相关常亮
 *
 * @author duanjun
 * @date 2018-10-24 23:01
 */
public class ScoreRankConstants {

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
     * 默认订单评分
     */
    public static final Integer DEFAULT_EVALUATION_LEVEL = 3;
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

    private static final String[] unit = {"k", "m", "b"};
    private static final BigDecimal ONE_THOUSAND = new BigDecimal(1000);
    /**
     * 格式化技能声量
     *
     * @param score
     * @return
     */
    public static String formatSkillScore(Integer score) {
        if (score == null) {
            return "0";
        }
        if(score < 1000){
            return String.valueOf(score);
        }
        BigDecimal decimal = new BigDecimal(score);

        int index = 0;
        do {
            decimal = decimal.divide(ONE_THOUSAND);
            index++;
        }while (decimal.compareTo(ONE_THOUSAND) >= 0 && index <= 2);
        index--;
        decimal = decimal.setScale(2, BigDecimal.ROUND_DOWN);
        return decimal + unit[index];
    }

}
