package com.honglu.quickcall.account.service.dao;

import com.honglu.quickcall.account.facade.entity.EvaluationLabel;
import com.honglu.quickcall.account.facade.entity.SkillItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkillItemMapper {
    SkillItem selectByPrimaryKey(Long id);

    /**
     * 查询技能项的评价标签
     * @param skillItemId -- 技能Id
     * @param customerSex -- 性别
     * @return
     */
    List<EvaluationLabel> queryEvaluationLabel(@Param("skillItemId") Long skillItemId, @Param("customerSex") int customerSex);
}