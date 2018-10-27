package com.honglu.quickcall.user.service.constants;

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
        EVALUATION_LEVEL_WEIGHT_MAP.put(1, 5.0);
        EVALUATION_LEVEL_WEIGHT_MAP.put(2, 20.0);
        EVALUATION_LEVEL_WEIGHT_MAP.put(3, 50.0);
        EVALUATION_LEVEL_WEIGHT_MAP.put(4, 85.0);
        EVALUATION_LEVEL_WEIGHT_MAP.put(5, 100.0);

        /********************************* 单笔订单件数权重配置 *********************************/
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(1, Double.valueOf(1));
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(5, Double.valueOf(1.2));
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(10, Double.valueOf(1.3));
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(20, Double.valueOf(1.5));
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(50, Double.valueOf(2));
        SINGLE_ORDER_NUM_WEIGHT_MAP.put(100, Double.valueOf(3));
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
            if(key <= orderNo){
                index = key;
            }
        }
        return SINGLE_ORDER_NUM_WEIGHT_MAP.get(index);
    }

}
