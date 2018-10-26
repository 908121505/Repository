package com.calf.module.resource.entity;


/**
 * 用户资源池配置表bean
 * @author zhaozheyi
 * @date Thu Oct 25 15:57:00 CST 2018
 **/
public class ResourceConfigExCust {

	/**主键ID**/
	private Integer id;

	/**资源池配置ID**/
	private Long resourceConfigId;

	/**客户号**/
	private String appId;

	/**状态(0=不可用，1=可用)**/
	private Integer status;

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


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setResourceConfigId(Long resourceConfigId){
		this.resourceConfigId = resourceConfigId;
	}

	public Long getResourceConfigId(){
		return this.resourceConfigId;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
