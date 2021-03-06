package com.honglu.quickcall.task.dao;

import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface BigvSkillScoreMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int insert(BigvSkillScore record);

    /**
     * 查询大V技能的订单笔数
     *
     * @param customerSkillId
     * @return
     */
    Integer selectBigvSkillOrderTotal(@Param("customerSkillId") Long customerSkillId);

    /**
     * 根据默认评价得到到订单表
     *
     * @param orderId
     * @param valueScore
     * @return
     */
    int updateValueScoreToOrder(@Param("orderId") Long orderId, @Param("valueScore") BigDecimal valueScore);

    /**
     * 更新大V单项技能评分
     *
     * @param customerSkillId
     * @param valueScore
     * @return
     */
    int updateBigvSkillScore(@Param("customerSkillId") Long customerSkillId, @Param("valueScore") BigDecimal valueScore);
}