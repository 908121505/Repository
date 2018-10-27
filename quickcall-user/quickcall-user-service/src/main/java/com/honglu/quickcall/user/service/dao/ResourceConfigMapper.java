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
     * @param resourceConfigId
     * @return
     */
    List<String> selectResourceEnableSkills(@Param("resourceConfigId") Integer resourceConfigId);

    /**
     * 查询资源位配置启用技能
     * @return
     */
    List<String> selectAllEnableSkills();

    /**
     * 查询启用的大V + 技能排名列表
     * @return
     */
    List<BigvSkillScore> selectEnabledBigvAndSkillRankData(
            @Param("configSkills") List<String> configSkills,
            @Param("weekIndex") Integer weekIndex,
            @Param("endTimeStr") String endTimeStr);

}