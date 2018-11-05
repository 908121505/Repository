package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.user.facade.entity.BigvScore;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BigvScoreMapper {

    int insert(BigvScore record);

    /**
     * 更新大V评分到大V排名表
     * @param customerId
     * @param valueScore
     * @param addOrderTotal
     * @return
     */
    int updateBigvScore(@Param("customerId") Long customerId,
                        @Param("valueScore") BigDecimal valueScore,
                        @Param("addOrderTotal") Integer addOrderTotal);

    /**
     * 更新大V排名表
     * @return
     */
    int updateBigvScore2();

    /**
     * 根据客户ID -- 物理删除数据
     * @param customerId
     */
    int deleteDataByCustomerId(@Param("customerId") Long customerId);

    /**
     *  根据客户技能ID查询所有已完成的订单
     * @param customerSkillId
     * @return
     */
    List<Order> selectAllDoneOrderByCustomerSkillId(@Param("customerSkillId") Long customerSkillId);
}