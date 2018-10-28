package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import com.honglu.quickcall.user.facade.entity.CustomerSkill;
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
     * 查询配置位排除的customerId
     *
     * @param resourceConfigId
     * @return
     */
    List<Long> selectResourceExCustomerIds(@Param("resourceConfigId") Integer resourceConfigId);

    /**
     * 查询启用的大V + 技能排名列表 + 未被下单的总数
     *
     * @param configSkills
     * @param exCustomerIds
     * @param weekIndex
     * @param endTimeStr
     * @return
     */
    int countEnabledBigvAndSkillRankData(@Param("configSkills") List<Long> configSkills,
                                         @Param("exCustomerIds") List<Long> exCustomerIds,
                                         @Param("weekIndex") Integer weekIndex,
                                         @Param("endTimeStr") String endTimeStr,
                                         @Param("skillOrdered") Integer skillOrdered);

    /**
     * 随机根据大V排名查询一条数据（未被下单的）
     *
     * @return
     */
    BigvSkillScore selectEnabledBigvAndSkillRankData(@Param("configSkills") List<Long> configSkills,
                                                     @Param("exCustomerIds") List<Long> exCustomerIds,
                                                     @Param("weekIndex") Integer weekIndex,
                                                     @Param("endTimeStr") String endTimeStr,
                                                     @Param("beginIndex") int beginIndex,
                                                     @Param("endIndex") int endIndex,
                                                     @Param("skillOrdered") Integer skillOrdered);

    /**
     * 随机从推荐池里面查询一个大V（未被下单的）
     *
     * @param resourcePoolId
     * @param configSkills
     * @param exCustomerIds
     * @param weekIndex
     * @param endTimeStr
     * @return
     */
    BigvSkillScore selectRandomBigvFromResourcePool(@Param("resourcePoolId") Long resourcePoolId,
                                                    @Param("configSkills") List<Long> configSkills,
                                                    @Param("exCustomerIds") List<Long> exCustomerIds,
                                                    @Param("weekIndex") Integer weekIndex,
                                                    @Param("endTimeStr") String endTimeStr,
                                                    @Param("skillOrdered") Integer skillOrdered);

    /**
     * 根据大V排名查询大V数据
     *
     * @param skillItemId
     * @param weekIndex
     * @param endTimeStr
     * @return
     */
    List<CustomerSkill> selectRankBigvListBySkillItemId(@Param("skillItemId") Long skillItemId,
                                                        @Param("weekIndex") Integer weekIndex,
                                                        @Param("endTimeStr") String endTimeStr);
}