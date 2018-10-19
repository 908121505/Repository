package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;

/**   
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：技能分类列表展示
 * @Package: com.honglu.quickcall.account.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年9月23日 下午3:39:33 
 */
public class FirstPageSkillIteminfoVO implements  Serializable{

	private static final long serialVersionUID = -9205076445420748026L;
	/**技能名称*/
	private String skillItemName ;
    /**技能ID*/
    private Long  skillItemId;
    /**技能ICON图片地址*/
    private String  imageUrl;
    
    
    
    

    /**技能ICON图片地址*/
	public String getImageUrl() {
		return imageUrl;
	}

	/**技能ICON图片地址*/
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	

	public String getSkillItemName() {
		return skillItemName;
	}

	public void setSkillItemName(String skillItemName) {
		this.skillItemName = skillItemName;
	}

	public Long getSkillItemId() {
		return skillItemId;
	}

	public void setSkillItemId(Long skillItemId) {
		this.skillItemId = skillItemId;
	}

	
    
    
    
}
