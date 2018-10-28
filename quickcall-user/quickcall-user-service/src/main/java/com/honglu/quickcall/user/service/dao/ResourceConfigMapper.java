package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import com.honglu.quickcall.user.facade.entity.ResourceConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceConfigMapper {
    /**
     * 查询所有的6帧资源位的配置
     *
     * @return
     */
    List<ResourceConfig> selectAllResourceConfig();

    /**
     * 查询资源位配置启用技能
     *
     * @param resourceConfigId
     * @return
     */
    List<Long> selectResourceEnableSkills(@Param("resourceConfigId") Integer resourceConfigId);

    /**
     * 查询资源位配置启用技能
     *
     * @return
     */
    List<String> selectAllEnableSkills();

    /**
     * 查询启用的大V + 技能排名列表 + 未被下单的总数
     *
     * @param configSkills
     * @param customerIds
     * @param weekIndex
     * @param endTimeStr
     * @return
     */
    int countEnabledBigvAndSkillRankData(List<Long> configSkills, List<Long> customerIds, Integer weekIndex, String endTimeStr);

    /**
     * 查询启用的大V + 技能排名列表 + 未被下单的
     *
     * @return
     */
    List<BigvSkillScore> selectEnabledBigvAndSkillRankData(
            @Param("configSkills") List<Long> configSkills,
            @Param("customerIds") List<Long> customerIds,
            @Param("weekIndex") Integer weekIndex,
            @Param("endTimeStr") String endTimeStr);

}