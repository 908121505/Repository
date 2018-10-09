package com.honglu.quickcall.user.service.dao;

import java.util.List;

import com.honglu.quickcall.user.facade.entity.Occupation;
import com.honglu.quickcall.user.facade.vo.OccupationVO;

public interface OccupationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Occupation record);

    int insertSelective(Occupation record);

    Occupation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Occupation record);

    int updateByPrimaryKey(Occupation record);
    
    //根据customerId查找职业
    String selectByCustomerId(Long customerId);

    /**查询职业列表*/
	List<OccupationVO> selectOccupationList();
}