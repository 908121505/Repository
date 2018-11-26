package com.honglu.quickcall.user.facade.entity;


/**
 * 客户相关照片表bean
 * @author luoyanchong
 * @date Sun Oct 21 11:10:37 CST 2018
 **/
public class CustomerAppearance {

	/**主键id(15位时间+4位随机数)**/
	private Long id;

	/**客户ID**/
	private Long customerId;

	/**待审核照片**/
	private String auditAppearance;

	/**照片（正在使用的）**/
	private String appearance;

	/**0=形象照,1=头像照,2=声鉴卡**/
	private Integer type;

	/**审核状态 0=待审核,1=已通过,2=已拒绝**/
	private Integer auditStatus;

	/**排序（暂时没用）**/
	private Integer sort;

	/**状态（0=不可用，1=可用）**/
	private Integer status;

	/**创建时间**/
	private String createTime;

	/**更新时间**/
	private String modifyTime;

	/**创建人**/
	private String createMan;

	/**更新人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public CustomerAppearance() {
		super();
	}
	public CustomerAppearance(Long id,Long customerId,String auditAppearance,String appearance,Integer type,Integer auditStatus,Integer sort,Integer status,String createTime,String modifyTime,String createMan,String modifyMan,String remark) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.auditAppearance = auditAppearance;
		this.appearance = appearance;
		this.type = type;
		this.auditStatus = auditStatus;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}

	public Long getCustomerId(){
		return this.customerId;
	}

	public void setAuditAppearance(String auditAppearance){
		this.auditAppearance = auditAppearance;
	}

	public String getAuditAppearance(){
		return this.auditAppearance;
	}

	public void setAppearance(String appearance){
		this.appearance = appearance;
	}

	public String getAppearance(){
		return this.appearance;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return this.type;
	}

	public void setAuditStatus(Integer auditStatus){
		this.auditStatus = auditStatus;
	}

	public Integer getAuditStatus(){
		return this.auditStatus;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}

	public Integer getSort(){
		return this.sort;
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

}
