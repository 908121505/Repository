package com.honglu.quickcall.user.facade.entity;

import java.math.BigDecimal;

/**
 * 用戶技能认证表bean
 * @author zhaozheyi
 * @date Fri Oct 19 11:38:50 CST 2018
 **/
public class CustomerSkillCertify {

	/**主键ID**/
	private Long certifyId;

	/**用户编号**/
	private Long customerId;

	/**技能编号**/
	private Long skillItemId;

	/**技能审核状态（0：未解锁，1：待审核，2：审核通过，3：审核失败）**/
	private Integer auditStatus;

	/**技能声音url**/
	private String skillVoiceUrl;

	/**技能声音时长**/
	private BigDecimal skillVoiceTime;

	/**技能审核声音url**/
	private String skillVoiceUrlTmp;

	/**技能审核声音时长**/
	private BigDecimal skillVoiceTimeTmp;

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


	public CustomerSkillCertify() {
		super();
	}
	public CustomerSkillCertify(Long certifyId,Long customerId,Long skillItemId,Integer auditStatus,String skillVoiceUrl,BigDecimal skillVoiceTime,String skillVoiceUrlTmp,BigDecimal skillVoiceTimeTmp,Integer isAudited,String createTime,String modifyTime,String createMan,String modifyMan,String remark) {
		super();
		this.certifyId = certifyId;
		this.customerId = customerId;
		this.skillItemId = skillItemId;
		this.auditStatus = auditStatus;
		this.skillVoiceUrl = skillVoiceUrl;
		this.setSkillVoiceTime(skillVoiceTime);
		this.skillVoiceUrlTmp = skillVoiceUrlTmp;
		this.setSkillVoiceTimeTmp(skillVoiceTimeTmp);
		this.isAudited = isAudited;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setCertifyId(Long certifyId){
		this.certifyId = certifyId;
	}

	public Long getCertifyId(){
		return this.certifyId;
	}

	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}

	public Long getCustomerId(){
		return this.customerId;
	}

	public void setSkillItemId(Long skillItemId){
		this.skillItemId = skillItemId;
	}

	public Long getSkillItemId(){
		return this.skillItemId;
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
	public BigDecimal getSkillVoiceTime() {
		return skillVoiceTime;
	}
	public void setSkillVoiceTime(BigDecimal skillVoiceTime) {
		this.skillVoiceTime = skillVoiceTime;
	}
	public BigDecimal getSkillVoiceTimeTmp() {
		return skillVoiceTimeTmp;
	}
	public void setSkillVoiceTimeTmp(BigDecimal skillVoiceTimeTmp) {
		this.skillVoiceTimeTmp = skillVoiceTimeTmp;
	}

}
