package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午3:39:33 
 */
public class FirstPageSkillinfoVO implements  Serializable{

	private static final long serialVersionUID = -3078554028724442639L;
	/**技能名称*/
	private String name ;
    /**技能ID*/
    private Long  skillId;
    
    private String  titleUrl;
    
    

	public String getTitleUrl() {
		return titleUrl;
	}

	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
    
    
    
}
