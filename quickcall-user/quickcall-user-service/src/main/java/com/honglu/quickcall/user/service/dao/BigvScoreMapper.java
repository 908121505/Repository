package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.BigvScore;
import com.honglu.quickcall.user.facade.entity.example.BigvScoreExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

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