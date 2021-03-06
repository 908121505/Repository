package com.honglu.quickcall.account.service.dao;

import java.util.List;

import com.honglu.quickcall.account.facade.entity.Skill;
import com.honglu.quickcall.account.facade.vo.FirstPageSkillIteminfoVO;
import com.honglu.quickcall.account.facade.vo.VoiceVOCopy;

public interface SkillMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);
    
    
    List<Skill>  selectTotalSkill();

	List<FirstPageSkillIteminfoVO> selectPartSkill();

	/**根据客户编号查询大V声音信息*/
	VoiceVOCopy getVoiceInfo(Long customerId);
}