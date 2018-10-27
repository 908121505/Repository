package com.honglu.quickcall.user.service.dao;

import java.util.List;
import java.util.Map;

import com.honglu.quickcall.user.facade.entity.Interest;
import com.honglu.quickcall.user.facade.vo.InterestVO;
import org.apache.ibatis.annotations.Param;

public interface InterestMapper {
	//根据customerId查找兴趣
	List<Interest> selectInterestByCustomerId (Long customerId);
	
	/**查询兴趣列表*/
	List<InterestVO> selectInterestList ();

	List<InterestVO> selectInterestListByCustomerId (@Param("customerId") Long customerId);

}
