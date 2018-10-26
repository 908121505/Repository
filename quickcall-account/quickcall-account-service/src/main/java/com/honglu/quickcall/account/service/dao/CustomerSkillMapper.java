package com.honglu.quickcall.account.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.account.facade.entity.CustomerSkill;
import com.honglu.quickcall.account.facade.vo.CustomerSkillIMVO;
import com.honglu.quickcall.account.facade.vo.FirstPageSkillIteminfoVO;
import com.honglu.quickcall.account.facade.vo.OrderDaVSkillVO;
import com.honglu.quickcall.account.facade.vo.OrderIMVO;

public interface CustomerSkillMapper {
    int deleteByPrimaryKey(Long userSkillId);

    int insert(CustomerSkill record);

    
    int insertSelective(CustomerSkill record);

    CustomerSkill selectByPrimaryKey(Long userSkillId);
    
    
    CustomerSkill selectByPrimaryKeyExt(@Param("customerSkillId")Long customerSkillId,@Param("weekIndex")Integer  weekIndex ,@Param("skillSwitch")Integer skillSwitch,@Param("endTimeStr")String  endTimeStr);

    int updateByPrimaryKeySelective(CustomerSkill record);

    int updateByPrimaryKey(CustomerSkill record);

    
    /**根据客户编号查询客户所有的技能信息*/
	List<CustomerSkill> querySkillInfoPersonal(Long customerId);

	/**批量更新数据*/
	void batchUpdate(@Param("list")  List<CustomerSkill> updateList);

	List<FirstPageSkillIteminfoVO> selectPartSkill();

	List<OrderDaVSkillVO> selectDaVSkill(Long customerId);

	
	
	OrderIMVO selectCustSkillItem(Long customerSkillId);

	/***
	 * 判断用户当天是否可以接单
	 * @param serviceId
	 * @param weekIndex
	 * @param skillSwitch
	 * @param endTimeStr
	 * @return
	 */
	CustomerSkillIMVO selectCustomerSkillByCustomerId(@Param("customerId")Long customerSkillId,@Param("weekIndex")Integer  weekIndex ,@Param("skillSwitch")Integer skillSwitch,@Param("endTimeStr")String  endTimeStr);
}