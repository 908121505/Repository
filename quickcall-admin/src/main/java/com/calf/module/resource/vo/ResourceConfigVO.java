package com.calf.module.resource.vo;


/**
 * 资源配置输出
 * @author zhaozheyi
 *
 */
public class ResourceConfigVO {
	/**资源配置编号**/
	private Integer resourceConfigId;

	/**资源位配置序号**/
	private Integer configNum;

	/**当前策略（1=自然推荐，2=运营推荐）**/
	private Integer strategy;
	
	private String skillName;
	
	private String resourceConfigSkillList;
	/**资源池编号**/
	private Long resourcePoolId;
	
	/**资源池名称**/
	private String resourceName;
	
	/**声优总数量**/
	private Integer totalCusNum;
	
	/**已接单数量**/
	private Integer receiptCusNum;
	
	/**剩余数量**/
	private Integer surplusCusNum;
	
	/**排除掉的声优**/
	private String exCusList;

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


	public Integer getTotalCusNum() {
		return totalCusNum;
	}

	public void setTotalCusNum(Integer totalCusNum) {
		this.totalCusNum = totalCusNum;
	}

	public Integer getReceiptCusNum() {
		return receiptCusNum;
	}

	public void setReceiptCusNum(Integer receiptCusNum) {
		this.receiptCusNum = receiptCusNum;
	}

	public Integer getSurplusCusNum() {
		return surplusCusNum;
	}

	public void setSurplusCusNum(Integer surplusCusNum) {
		this.surplusCusNum = surplusCusNum;
	}

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


	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Long getResourcePoolId() {
		return resourcePoolId;
	}

	public void setResourcePoolId(Long resourcePoolId) {
		this.resourcePoolId = resourcePoolId;
	}

	public String getResourceConfigSkillList() {
		return resourceConfigSkillList;
	}

	public void setResourceConfigSkillList(String resourceConfigSkillList) {
		this.resourceConfigSkillList = resourceConfigSkillList;
	}

	public String getExCusList() {
		return exCusList;
	}

	public void setExCusList(String exCusList) {
		this.exCusList = exCusList;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}


}
