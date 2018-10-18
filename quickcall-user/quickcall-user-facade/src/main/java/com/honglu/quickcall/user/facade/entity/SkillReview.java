package com.honglu.quickcall.user.facade.entity;


/**
 * 技能审核
 * @author zhaozheyi
 * @date Thu Oct 18 14:40:43 CST 2018
 **/
public class SkillReview {

	/**用户编号**/
	private Long customerId;

	/**技能编号**/
	private Long skillId;

	/**技能审核状态（0：待审核，1：审核通过，2：审核失败）**/
	private Integer auditStatus;

	/**技能声音url**/
	private String skillVoiceUrl;

	/**技能审核声音url**/
	private String skillVoiceUrlTmp;

	/**是否曾经已经审核通过（0：未通过，1：已通过）**/
	private Integer isAudited;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;

	/**创建人**/
	private String createMan;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public SkillReview() {
		super();
	}
	public SkillReview(Long customerId,Long skillId,Integer auditStatus,String skillVoiceUrl,String skillVoiceUrlTmp,Integer isAudited,String createTime,String modifyTime,String createMan,String modifyMan,String remark) {
		super();
		this.customerId = customerId;
		this.skillId = skillId;
		this.auditStatus = auditStatus;
		this.skillVoiceUrl = skillVoiceUrl;
		this.skillVoiceUrlTmp = skillVoiceUrlTmp;
		this.isAudited = isAudited;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}

	public Long getCustomerId(){
		return this.customerId;
	}

	public void setSkillId(Long skillId){
		this.skillId = skillId;
	}

	public Long getSkillId(){
		return this.skillId;
	}

	public void setAuditStatus(Integer auditStatus){
		this.auditStatus = auditStatus;
	}

	public Integer getAuditStatus(){
		return this.auditStatus;
	}

	public void setSkillVoiceUrl(String skillVoiceUrl){
		this.skillVoiceUrl = skillVoiceUrl;
	}

	public String getSkillVoiceUrl(){
		return this.skillVoiceUrl;
	}

	public void setSkillVoiceUrlTmp(String skillVoiceUrlTmp){
		this.skillVoiceUrlTmp = skillVoiceUrlTmp;
	}

	public String getSkillVoiceUrlTmp(){
		return this.skillVoiceUrlTmp;
	}

	public void setIsAudited(Integer isAudited){
		this.isAudited = isAudited;
	}

	public Integer getIsAudited(){
		return this.isAudited;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
