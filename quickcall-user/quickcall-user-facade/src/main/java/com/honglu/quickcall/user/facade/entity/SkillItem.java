package com.honglu.quickcall.user.facade.entity;


/**
 * 大V技能项信息表bean
 * @author zhaozheyi
 * @date Fri Oct 19 17:40:14 CST 2018
 **/
public class SkillItem {

	/**主键ID(15位时间+4位随机数)**/
	private Long id;

	/**技能项名称**/
	private String skillItemName;

	/**描述**/
	private String skillDescribe;

	/**背景图链接**/
	private String imageUrl;

	/**显示排序**/
	private Integer sort;

	/**状态(0=不可用，1=可用)**/
	private Integer skillStatus;

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

	/**未解锁背景图链接**/
	private String blackImageUrl;


	public SkillItem() {
		super();
	}
	public SkillItem(Long id,String skillItemName,String skillDescribe,String imageUrl,Integer sort,Integer skillStatus,String createTime,String modifyTime,String createMan,String modifyMan,String remark,String blackImageUrl) {
		super();
		this.id = id;
		this.skillItemName = skillItemName;
		this.skillDescribe = skillDescribe;
		this.imageUrl = imageUrl;
		this.sort = sort;
		this.skillStatus = skillStatus;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.blackImageUrl = blackImageUrl;
	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setSkillItemName(String skillItemName){
		this.skillItemName = skillItemName;
	}

	public String getSkillItemName(){
		return this.skillItemName;
	}

	public void setSkillDescribe(String skillDescribe){
		this.skillDescribe = skillDescribe;
	}

	public String getSkillDescribe(){
		return this.skillDescribe;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return this.imageUrl;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}

	public Integer getSort(){
		return this.sort;
	}

	public void setSkillStatus(Integer skillStatus){
		this.skillStatus = skillStatus;
	}

	public Integer getSkillStatus(){
		return this.skillStatus;
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

	public void setBlackImageUrl(String blackImageUrl){
		this.blackImageUrl = blackImageUrl;
	}

	public String getBlackImageUrl(){
		return this.blackImageUrl;
	}

}
