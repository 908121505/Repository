package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.BigvScore;
import com.honglu.quickcall.user.facade.entity.example.BigvScoreExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BigvScoreMapper {

    int countByExample(BigvScoreExample example);

    int insert(BigvScore record);

    List<BigvScore> selectByExample(BigvScoreExample example);

    BigvScore selectByPrimaryKey(Integer id);

    /**
     * 更新大V评分到大V排名表
     * @param customerId
     * @param valueScore
     * @return
     */
    int updateBigvScore(@Param("customerId") Long customerId, @Param("valueScore") BigDecimal valueScore);

    /**
     * 查询在线大V数量
     * @return
     */
    int countOnlineBigvCount();

    /**
     * 查询有效大V数
     * @return
     */
    int countValidBigvCount();
}