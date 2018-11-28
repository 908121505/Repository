package com.calf.module.resource.entity;


/**
 * 资源配置表bean
 * @author zhaozheyi
 * @date Thu Oct 25 16:06:18 CST 2018
 **/
public class ResourceConfig {

	/**资源配置编号**/
	private Integer resourceConfigId;

	/**资源位配置序号**/
	private Integer configNum;

	/**当前策略（1=自然推荐，2=运营推荐）**/
	private Integer strategy;

	/**资源池编号**/
	private Long resourcePoolId;

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


	public void setResourceConfigId(Integer resourceConfigId){
		this.resourceConfigId = resourceConfigId;
	}

	public Integer getResourceConfigId(){
		return this.resourceConfigId;
	}

	public void setConfigNum(Integer configNum){
		this.configNum = configNum;
	}

	public Integer getConfigNum(){
		return this.configNum;
	}

	public void setStrategy(Integer strategy){
		this.strategy = strategy;
	}

	public Integer getStrategy(){
		return this.strategy;
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

	public Long getResourcePoolId() {
		return resourcePoolId;
	}

	public void setResourcePoolId(Long resourcePoolId) {
		this.resourcePoolId = resourcePoolId;
	}

}
