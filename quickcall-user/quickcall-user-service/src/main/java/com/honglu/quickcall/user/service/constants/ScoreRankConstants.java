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
     * 订单数量累计总数权重
     **/
    public static final Double ORDER_NUM_TOTAL_WEIGHT = Double.valueOf(1);
    /**
     * 单笔订单价格权重
     **/
    public static final Double SINGLE_ORDER_PRICE_WEIGHT = Double.valueOf(1);
    /**
     * 订单评价权重
     **/
    public static final Double ORDER_EVALUATION_WEIGHT = Double.valueOf(1);
    /**
     * 评分等级权重常量定义
     */
    public static Map<Integer, Integer> EVALUATION_LEVEL_WEIGHT_MAP = new HashMap<>();
    /**
     * 单笔订单件数权重常量定义
     */
    public static Map<Integer, Double> SINGLE_ORDER_NUM_WEIGHT_MAP = new LinkedHashMap<>();

    static {
        /********************************* 评分等级权重配置 *********************************/
        EVALUATION_LEVEL_WEIGHT_MAP.put(1, 10);
        EVALUATION_LEVEL_WEIGHT_MAP.put(2, 40);
        EVALUATION_LEVEL_WEIGHT_MAP.put(3, 70);
        EVALUATION_LEVEL_WEIGHT_MAP.put(4, 100);
        EVALUATION_LEVEL_WEIGHT_MAP.put(5, 130);

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
