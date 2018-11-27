package com.honglu.quickcall.task.dao;

import com.honglu.quickcall.user.facade.entity.BigvScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface BigvScoreMapper {

    int insert(BigvScore record);

    /**
     * 更新大V评分到大V排名表
     * @param customerId
     * @param valueScore
     * @return
     */
    int updateBigvScore(@Param("customerId") Long customerId, @Param("valueScore") BigDecimal valueScore);

}