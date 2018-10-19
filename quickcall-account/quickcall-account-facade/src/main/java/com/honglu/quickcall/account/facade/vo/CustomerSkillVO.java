package com.honglu.quickcall.account.facade.vo;

import java.math.BigDecimal;
import java.util.List;

public class CustomerSkillVO {
	
	/**技能ID*/
	private Long  customerSkillId;
	
	
//	private  Integer  
	
	    private Long skillId;
	    private Integer  productStatus;
	    private String name;
	    /**价格列表*/
	    private List<BigDecimal>  priceList;
	    /**已设置金额*/
	    private BigDecimal  currPrice;
	    
	    

	    
    
	/**技能vo*/
    private List<SkillVO>  skillVOList;
    /**声音状态*/
    private VoiceVO  voiceVO;
	
	public List<SkillVO> getSkillVOList() {
		return skillVOList;
	}
	public void setSkillVOList(List<SkillVO> skillVOList) {
		this.skillVOList = skillVOList;
	}
	public VoiceVO getVoiceVO() {
		return voiceVO;
	}
	public void setVoiceVO(VoiceVO voiceVO) {
		this.voiceVO = voiceVO;
	}
	
	
    
    
   
    
    
    
    
   
}